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
        $SELECT = "SELECT * from accounts WHERE email = ?";

        //Prepare statement
        $stmt = $conn->prepare($SELECT);
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt_result = $stmt->get_result();
        //$stmt->store_result();
        $rnum = $stmt_result->num_rows;
        
        if ($rnum==0) {
            header('Location: /SoftEng2/logIn.html');
        } else {
            $data = $stmt_result->fetch_assoc();
            if (password_verify($password, $data['password'])) {
                header('Location: /SoftEng2/index.html');
            } else {
                header('Location: /SoftEng2/logIn.html');
            }
        }
        // $stmt->close();
        $stmt_result->close();
        $conn->close();
    }
} else {
    echo "All field are required";
    die();
}
?>