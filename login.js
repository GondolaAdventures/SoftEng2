function checkPass() { // for password checkbox
    var x = document.getElementById("inputPass");
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
  }