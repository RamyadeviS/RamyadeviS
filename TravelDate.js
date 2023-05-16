var dateInput = document.getElementById("TravelDate");
var today = new Date();
var minDate = today.toISOString().split('T')[0];
dateInput.setAttribute("min", minDate);
var oneMonthLater = new Date(today.getFullYear(),
	today.getMonth() + 8, today.getDate() + 31);
var maxDate = oneMonthLater.toISOString().split('T')[0];
dateInput.setAttribute("max", maxDate);
