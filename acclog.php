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
        $rnum = $stmt_result->num_rows;
        
        if ($rnum==0) {
            header('Location: /SoftEng2/logIn.html#ErrorLog');
        } else {
            $data = $stmt_result->fetch_assoc();
            if (password_verify($password, $data['password'])) {
                header('Location: /SoftEng2/index.html#Welcome');
            } else {
                //header('Location: /SoftEng2/logIn.html');
                header('Location: /SoftEng2/logIn.html#ErrorLog');
                //echo ($password == $data['password']);
            }
        }
        // $stmt->close();
        $stmt_result->close();
        $conn->close();
    }
} else {
    // this block of code doesn't trigger anymore
    echo "All field are required";
    die();
}
?>