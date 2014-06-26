describe('FileWeights', function() {
	
	var fileweights = new FileWeights();

	beforeEach(function() {
		// NOTE: i want use app HTML for fixtures
		jasmine.getFixtures().fixturesPath = 'src/main/webapp';
		
		loadFixtures("index.jsp");
	});
		
	it('collects from server and update report section', function() {
		var username = "mario.rossi@gmail.com";
		var password = "mypwd";

		spyOn(jQuery, "ajax");
		
		var callback = function callback(data) { };
		fileweights.report(username, password, callback);
		
		expect(jQuery.ajax).toHaveBeenCalledWith(FileWeights.newReportRequest(username, password, callback));
	});
	
	it('updateUI creates one item for each category', function() {
		FileWeights.updateUI(
			'#report',
			template,
			{"items":[ {"category":"documents","count":2,"weight":2,"idealWeight":1},{"category":"others","count":1,"weight":4,"idealWeight":3} ]}
		);
		
		expect($('.item').length).toBe(2);
		expect($('.item:first .count').text()).toBe("2");
		expect($('.item:first .category').text()).toBe("documents");
		expect($('.item:first .numeric').text()).toBe("2");
	});

	it('updateUI shows total weight and total gravity displacement too', function() {
		FileWeights.updateUI(
			'#report',
			template,
			{"items":[ {"category":"documents","count":2,"weight":2,"idealWeight":1},{"category":"others","count":1,"weight":4,"idealWeight":3} ]}
		);
		
		expect($('#totalWeight').text()).toBe("6.00");
		expect($('#totalGravityDisplacement').text()).toBe("2.00");
	});

});