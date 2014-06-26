<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">

	<title>Workshare test</title>

	<script src="lib/jquery-1.7.2.min.js"></script>
	<script src="FileWeights.js"></script>

	<!--[if lt IE 9]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	
	<style type="text/css" >
		html {
			font-size: 76%;
		}
	
		body, input {
			font-family: "Trebuchet MS", Helvetica, Arial;
			font-size: 1em;
		}

		#report {
			margin-top: 1em;
		}
		
		#items {
			 margin-bottom: 1em;
		}

		#report label {
			display: inline-block;
			width: 10em;

			text-align: right;
			font-weight: bold;
		}

		.numeric {
			display: inline-block;
			width: 3em;
			
			text-align: right;
		}
	</style>
</head>

<body>
	<input id="username" type="text" placeholder="Username" />
	<input id="password" type="password" placeholder="Password" />
	<input type="button" href="#" onclick="showFileWeigths()" value="Show..." />
	
	<div id="report" >
		<div id="items" >
			<div id="item" >
				<label for="template" >2 Videos</label>
				<span id="template" class="numeric" >-</span><br />
				<label for="template" >1 Songs</label>
				<span id="template" class="numeric" >-</span><br />
			</div><!-- item -->
		</div><!-- items -->
		<label for="totalWeight" >Total</label>
		<span id="totalWeight" class="numeric" >-</span><br />
		<label for="totalGravityDisplacement" >Add. gravity</label>
		<span id="totalGravityDisplacement" class="numeric" >-</span>
	</div><!-- report -->

	<script>
		var fileweights = new FileWeights();
	
		function showFileWeigths() {
			var username = $("#username").val();
			var password = $("#password").val();
			
			console.log("retrieving report for username " + username + "...");
			fileweights.report(username, password, function(data) {
				console.log("... retrieved: " + JSON.stringify(data));
			});
		}
	</script>
</body>
</html>
