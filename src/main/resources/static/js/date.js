
var today = new Date();

var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); // Январь - 0
var yyyy = today.getFullYear();

var formattedDate = yyyy + '-' + mm + '-' + dd;

document.getElementById('date').value = formattedDate;