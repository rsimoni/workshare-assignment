package workshareassignment.provided;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import net.minidev.json.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.*;
import org.apache.http.entity.mime.*;
import org.apache.http.entity.mime.content.*;
import org.apache.http.impl.client.*;
import org.apache.http.message.*;
import org.apache.http.protocol.*;
import org.apache.http.util.*;

/**
 * Represents <a href="http://workshare.com" >Workshare.com</a> api client provided by Workshare.
 * 
 * <p>
 * This code can be found <a href="https://github.com/workshare/openapi-sample-java">here</a>.
 * I have simply changed logging. 
 * </p>
 * 
 * @author <a href="https://github.com/workshare/openapi-sample-java">provided by Workshare example</a>
 */
public class Workshare {

    public static final String DEFAULT_BASE_URL = "https://my.workshare.com";

    private static final Logger log = Logger.getLogger(Workshare.class.getName());

    private final String baseUrl;
    private final HttpClient httpclient;
    private final HttpContext context;
    private final String appUid;

    public Workshare(String appuid) {
        this(DEFAULT_BASE_URL, appuid);
    }

    public Workshare(String baseurl, String appuid) {
        this.baseUrl = baseurl;
        this.appUid = appuid;

        this.httpclient = new DefaultHttpClient();
        this.context = new BasicHttpContext();
        final CookieStore cookieStore = new BasicCookieStore();
        context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
    }

    public void login(String username, String password) throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("user_session[email]", username));
        params.add(new BasicNameValuePair("user_session[password]", password));
        params.add(new BasicNameValuePair("device[app_uid]", appUid));

        final HttpPost request = new HttpPost(makeUrl("user_sessions.json"));
        request.setEntity(new UrlEncodedFormEntity(params));

        final HttpResponse response = httpclient.execute(request, context);
        ckeckResponse(consume(response), 201);
    }

    public void logout() throws IOException {

        final HttpGet request = new HttpGet(makeUrl("logout.json"));
        consume(httpclient.execute(request, context));
    }

    public JSONArray getFolders() throws IOException {

        final HttpGet request = new HttpGet(makeUrl("folders.json"));

        final HttpResponse res = httpclient.execute(request, context);
        try {
            final JSONObject result = (JSONObject) JSONValue.parse(res.getEntity().getContent());
            final JSONArray folders = (JSONArray) result.get("folders");
            return folders;
        } finally {
            consume(res);
        }
    }

    public JSONArray getFiles() throws IOException {

        final HttpGet request = new HttpGet(makeUrl("files.json"));
        
        final HttpResponse res = httpclient.execute(request, context);
        try {
            final JSONObject result = (JSONObject) JSONValue.parse(res.getEntity().getContent());
            final JSONArray files = (JSONArray) result.get("files");
            return files;
        } finally {
            consume(res);
        }
    }

    public File download(JSONObject file) throws IOException {

        File result = new File(System.getProperty("java.io.tmpdir"), (String)file.get("name"));

        String url = makeUrl("files/" + file.get("id")+"/download");
        HttpGet request = new HttpGet(url);
        
        HttpResponse res = httpclient.execute(request, context);
        try {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(result));
            try {
                InputStream in = res.getEntity().getContent();
                try {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                } finally {
                    in.close();
                }
            } finally {
                out.close();
            }
        } finally {
            consume(res);
        }

        return result;
    }

    public JSONObject upload(File source, Integer folderId) throws IOException {

        MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        multipart.addPart("file[name]", new StringBody(source.getName()));
        multipart.addPart("file[updated_with]", new StringBody("cli"));
        multipart.addPart("Filedata", new FileBody(source, "application/octet-stream"));
        if (folderId != null)
            multipart.addPart("file[folder_id]", new StringBody(folderId.toString()));

        HttpPost request = new HttpPost(makeUrl("files.json"));
        request.setEntity(multipart);
        
        HttpResponse res = httpclient.execute(request, context);
        try {
            ckeckResponse(res, 201);
            final JSONObject result = (JSONObject) JSONValue.parse(res.getEntity().getContent());
            return result;
        } finally {
            consume(res);
        }
    }


    private void ckeckResponse(HttpResponse response, final int expectedStatus) throws IOException {
        log.fine(response.toString());
        if (response.getStatusLine().getStatusCode() != expectedStatus)
            throw new IOException("Login failed: " + response.getStatusLine());
    }

    private String makeUrl(final String apipath) {
        return baseUrl + "/api/open-v1.0/" + apipath;
    }

    private HttpResponse consume(HttpResponse response) throws IOException {
        final HttpEntity entity = response.getEntity();
        if (entity != null)
            EntityUtils.consume(entity);

        return response;
    }

}