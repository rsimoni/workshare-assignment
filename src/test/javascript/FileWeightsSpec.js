describe('FileWeights', function() {
	
	var fileweights = new FileWeights();
	
	it('collects from server and update report section', function() {
		var username = "mario.rossi@gmail.com";
		var password = "mypwd";

		spyOn(jQuery, "ajax");
		
		var callback = function callback(data) { };
		fileweights.report(username, password, callback);
		
		expect(jQuery.ajax).toHaveBeenCalledWith(FileWeights.newReportRequest(username, password, callback));
	});
	
});