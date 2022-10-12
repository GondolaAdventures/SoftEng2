<?php
$email = $_POST['email'];
$password = $_POST['password']; 

if (!empty($email) || !empty($password)) {
    $host = "localhost";
    $dbUsername = "root";
    $dbPassword = "";
    $dbname = "labibliodb";

    $conn = new mysqli($host, $dbUsername, $dbPassword, $dbname);

    if (mysqli_connect_error()) {
        die('Connect Error('.mysqli_connect_errno().')'.mysqli_connect_error());
    } else {
        $SELECT = "SELECT * from accounts WHERE email = ?";

        $stmt = $conn->prepare($SELECT);
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt_result = $stmt->get_result();
        $rnum = $stmt_result->num_rows;
        
        // Account association required.

        $stmt_result->close();
        $conn->close();
    }
} else {
    echo "All field are required";
    die();
}
?>