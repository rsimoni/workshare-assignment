// NOTE for workshare code reviewer:
//   this is what i would like to obtain
//   to obtain a green i 
describe("Workshare", function() {

	it("returns at least one files", function() {
		var workshare = new Workshare();
		var answer = workshare.files();
		expect(answer.files.length).toBeGreaterThan(0);
	});

});