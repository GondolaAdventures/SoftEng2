<?php
$email = $_POST['email'];
$password = $_POST['password'];
$password_crypt = password_hash($password, PASSWORD_BCRYPT);

if (!empty($email) || !empty($password)) {
    $host = "localhost";
    $dbUsername = "root";
    $dbPassword = "";
    $dbname = "labibliodb";

    //create connection
    $conn = new mysqli($host, $dbUsername, $dbPassword, $dbname);

    if (mysqli_connect_error()) {
        die('Connect Error('.mysqli_connect_errno().')'.mysqli_connect_error());
    } else {
        $SELECT = "SELECT email From accounts Where email = ? Limit 1";
        $INSERT = "INSERT Into accounts (email, password) values(?, ?)";

        //Prepare statement
        $stmt = $conn->prepare($SELECT);
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->bind_result($email);
        $stmt->store_result();
        $rnum = $stmt->num_rows;
        
        if ($rnum==0) {
            $stmt->close();

            $stmt = $conn->prepare($INSERT);
            $stmt->bind_param("ss", $email, $password_crypt);
            $stmt->execute();
            //echo "New record inserted successfully";
            header('Location: /SoftEng2/logIn.html');
        } else {
            //header('Location: /SoftEng2/SignUp.html');
            header('Location: /SoftEng2/SignUp.html#ErrorSign');
        }
        $stmt->close();
        $conn->close();
    }
} else {
    echo "All field are required";
    die();
}
?>