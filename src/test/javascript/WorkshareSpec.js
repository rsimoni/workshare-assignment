describe("Workshare", function() {

	it("returns at least one files", function() {
		var workshare = new Workshare();
		var answer = workshare.files();
		expect(answer.files.length).toBeGreaterThan(0);
	});
	
});