const count = document.querySelector(".box-container");

document.getElementById("EpassBtn").onclick = function() {
	openEpassApproval()
};
document.getElementById("travelPassBtn").onclick = function() {
	openTravelPassApproval()
};

function openEpassApproval() {
	const count = document.querySelector(".box-container");
	const grid1 = document.getElementById("myGrid");
	const approvalButton = document.getElementById("epassApproval");
	const grid2 = document.getElementById("myGrid1");
	const approvalButton2 = document.getElementById("travelApproval");

	grid1.style.display = "block";
	grid2.style.display = "none";
	approvalButton.style.display = "block";
	approvalButton2.style.display = "none";
	count.style.display = "none";

}
function openTravelPassApproval() {
	const count = document.querySelector(".box-container");
	const grid1 = document.getElementById("myGrid");
	const approvalButton = document.getElementById("epassApproval");
	const grid2 = document.getElementById("myGrid1");
	const approvalButton2 = document.getElementById("travelApproval");

	grid1.style.display = "none";
	grid2.style.display = "block";
	approvalButton.style.display = "none";
	approvalButton2.style.display = "block";
	count.style.display = "none";

}