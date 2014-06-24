function Workshare() { }

Workshare.prototype.files = function(callback) {
	$.ajax({
		url: "http://my.workshare.com/api/open-v1.0/files.json", 
		success: callback,
		error: function(jqXHR, textStatus, errorThrown) {
			alert(textStatus + " " + errorThrown);
		},
		statusCode: {
			401: function() {
				alert("Unauthorized!");
			}
		}
    });
	//return {"pagination":{"current_page":1,"total_pages":1,"total_entries":3}, "files":[{"id":9618642,"name":"Workshare for iPhone and iPad","extension":"pdf","version":1,"size":1858008,"created_at":"2014-06-23T14:43:22Z","updated_at":"2014-06-23T14:43:22Z","creator":{"name":"Roberto Simoni","email":"rsimoni.job+test@gmail.com","uuid":"27070e9c-0473-49f5-a7d1-80bd7a04252e"},"url":"https://my.workshare.com/decks/9618642","access_token":"ue7T6DnwsgAnFmfF","first_page_url":"https://my.workshare.com/images/icons/medium/pdf.png","folder_id":695712},{"id":9618641,"name":"Get to Work in 5 Simple Steps","extension":"pdf","version":1,"size":2850957,"created_at":"2014-06-23T14:43:21Z","updated_at":"2014-06-23T14:43:21Z","creator":{"name":"Roberto Simoni","email":"rsimoni.job+test@gmail.com","uuid":"27070e9c-0473-49f5-a7d1-80bd7a04252e"},"url":"https://my.workshare.com/decks/9618641","access_token":"Ee39YLWVHYRY9jKK","first_page_url":"https://my.workshare.com/images/icons/medium/pdf.png","folder_id":695712},{"id":9618640,"name":"Workshare for mobile","extension":"mp4","version":1,"size":59245396,"created_at":"2014-06-23T14:43:20Z","updated_at":"2014-06-23T14:43:21Z","creator":{"name":"Roberto Simoni","email":"rsimoni.job+test@gmail.com","uuid":"27070e9c-0473-49f5-a7d1-80bd7a04252e"},"url":"https://my.workshare.com/decks/9618640","access_token":"2QKjSRcGKfgkV4Lm","first_page_url":"https://my.workshare.com/images/icons/medium/unknown.png","folder_id":695712}]};
};
