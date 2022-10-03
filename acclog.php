<?php
$email = $_POST['email'];
$password = $_POST['password'];

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
        //$SELECT = "SELECT email From accounts Where email = ? Limit 1";
        $SELECT2 = "SELECT From accounts (email, password)";

        //Prepare statement
        // $stmt = $conn->prepare($SELECT);
        // $stmt->bind_param("s", $email);
        // $stmt->execute();
        // $stmt->bind_result($email);
        // $stmt->store_result();
        // $rnum = $stmt->num_rows;

        $stmt2 = $conn->prepare($SELECT2);
        $stmt2->bind_param("ss", $email, $password);
        $stmt2->execute();
        $stmt2->bind_result($email, $password);
        $stmt2->store_result();
        $rnum2 = $stmt2->num_rows;
        
        if ($rnum2==0) {
            header('Location: /SoftEng2/logIn.html');
        } else {
            header('Location: /SoftEng2/index.html');
        }
        // $stmt->close();
        $stmt2->close();
        $conn->close();
    }
} else {
    echo "All field are required";
    die();
}
?>