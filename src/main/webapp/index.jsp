<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">

	<title>Workshare Assignment</title>

	<script src="lib/jquery-1.11.1.min.js"></script>
	<script src="lib/jquery.blockUI.js"></script>
	<script src="lib/handlebars-v1.3.0.js"></script>
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

		#report .label {
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
		
		.category {
			text-transform: capitalize;
		}
	</style>
</head>

<body>
	<input id="username" type="text" placeholder="Username" />
	<input id="password" type="password" placeholder="Password" />
	<input type="button" href="#" onclick="onShow()" value="Show..." />
	
	<div id="report" >
	</div><!-- report -->

	<script id="report-template" type="text/x-handlebars-template">
		<div id="items" >
			{{#each items}}
			<div class="item" >
				<span class="label">
					<span class="count">{{count}}</span> 
					<span class="category">{{category}}</span>
				</span> <span class="numeric" >{{weight}}</span><br />
			</div>
			{{/each}}
		</div><!-- items -->
		<span class="label">Total</span> <span id="totalWeight" class="numeric" >{{totalWeight}}</span><br />
		<span class="label">Add. gravity</span> <span id="totalGravityDisplacement" class="numeric" >{{totalGravityDisplacement}}</span>
	</script>

	<script>
		$(document).ajaxStop($.unblockUI);
	
		var templateSource = $("#report-template").html();
		var template = Handlebars.compile(templateSource);
		
		function onShow() {
			$.blockUI();
			
			var username = $("#username").val();
			var password = $("#password").val();
			
			console.log("retrieving report for username " + username + "...");
			fileweights.report(username, password, function(data) {
				console.log("... retrieved: " + JSON.stringify(data));
				
				fileweights.updateUI('#report', template, data);
			});
		}
	</script>
</body>
</html>
