<?php
    // $user = 1;
    // $pass = 111;
    
    $user = $_GET["user"];
    $pass = $_GET["pass"];

    $servername = "localhost";
    $username = "root";
    $password = "123456";
    $dbname = "test";

    $conn = new mysqli($servername,$username,$password,$dbname);
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }
    $sql = "SELECT count(*) from test1 where user='$user' and pass='$pass'";
    $result = mysqli_query($conn, $sql);
    $row = mysqli_fetch_row($result);
    
    if($row[0] == 0)
    {
        echo "0";
        exit;
    }
    else
    {
        echo "1";
    }
?>