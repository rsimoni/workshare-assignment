function FileWeights() {
}

FileWeights.newReportRequest = function (username, password, callback) {
	return {
		method: "GET", 
		url: "/api/fileweights",
		headers: {
			"username": username,
			"password": password
		},
		success: callback
	};
};

FileWeights.prototype.report = function(username, password, callback) {
	jQuery.ajax(FileWeights.newReportRequest(username, password, callback));
};
