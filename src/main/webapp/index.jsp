<html>
<head>
	<script src="lib/jquery-1.11.1.min.js" ></script>
	<script src="js/Workshare.js" ></script>
</head>
<body>
    <h1>rsimoni Workshare Assignment</h1>
    <p>Files: <span id="labelFiles" >-</span>
    </p>
    <script>
    	var workshare = new Workshare();
    	workshare.files(function(data) {
    		$("#labelFiles").val(answer.files.length);
    	});
    </script>
</body>
</html>
