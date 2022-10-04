function checkPass() { // for password checkbox
    var x = document.getElementById("inputPass");
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
  }

 /* var modal = document.getElementById("ErrorSign");

  function popUpModal() {
    $('#ErrorSign').modal('show');
  }

 window.onload = function(){

  if ( document.getElementById('ErrorSign') ){

      alert('Box'); //replace with your own handler
  }

} */