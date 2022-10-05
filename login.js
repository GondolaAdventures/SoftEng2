/*if (document.referrer=="http://localhost/SoftEng2/acclog.php" || document.referrer=="http://localhost/SoftEng2/logIn.html") {
    alert("Account not found");
} */

function checkPass() { // for password checkbox
    var x = document.getElementById("inputPass");
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
  }