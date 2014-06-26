function FileWeights() {
}

FileWeights.prototype.report = function(username, password, callback) {
	jQuery.ajax(FileWeights.newReportRequest(username, password, callback));
};

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

FileWeights.updateUI = function (container, template, data) {
	var totalWeight = 0;
	var totalIdealWeight = 0;
	$.each(data.items, function () {
	    totalWeight += this.weight;
	    totalIdealWeight += this.idealWeight;
	});

	data.totalWeight = totalWeight.toFixed(2);
	data.totalGravityDisplacement = (totalWeight - totalIdealWeight).toFixed(2);
	
	$(container).html(template(data));
};
