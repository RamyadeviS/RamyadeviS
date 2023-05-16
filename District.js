let fromDistrict = document.getElementById("#fromDistrict");
let toDistrict = document.getElementById("#toDistric");
var nameError = document.getElementById("nameError");
var text;
function checkFunction() {
	if (!toDistrict.equals(fromDistrict)) {
		return true;
	}
	text = "From District and To District Should not same";
	nameError.innerHTML = text;
	return false;
}
