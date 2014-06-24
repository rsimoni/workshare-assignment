<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">

	<title>Workshare test</title>

	<script src="https://code.jquery.com/jquery-1.11.1.js"></script>
<!--
  <link rel="stylesheet" href="css/styles.css?v=1.0">
-->

  <!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>

<body>
	<input type="button" href="#" onclick="login()" value="Login" />

	<script>
		function login() {
			console.log("Login...");
			var parameters = {
				"user_session[email]": "rsimoni.job+test@gmail.com",
				"user_session[password]": "ch1natown",
				"device[app_uid]": "13922396-557d"
			};
			$.ajax({
				url: "http://my.workshare.com/api/open-v1.0/user_sessions.json", 
				method: "POST",
				data: parameters,
				crossDomain: true,
				//beforeSend: setHeader,
				success: function(data) {
					console.log(data);
				}
			});
		}
		/*
		function setHeader(xhr) {
			xhr.setRequestHeader("Origin", "http://rsimoni-workshare-assignment.appspot.com");
		}
		*/
	</script>
</body>
</html>
