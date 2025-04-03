<?php


if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Decode the JSON data
    $json = file_get_contents('php://input');
    $data = json_decode($json, true);

    if (isset($_POST["username"]) && isset($_POST["password"])) {
        $username = $_POST["username"];
        $password = $_POST["password"];
        $name = $_POST["name"];
        $email = $_POST["email"];
        $contact = $_POST["contact"];
        $age = $_POST["age"];
        $gender = $_POST["gender"];

        // Establish the database connection
        require("conn.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

        // Query to insert data into the database
        $sql = "INSERT INTO sign (username, password,name,email,contact,age,gender) VALUES ('$username', '$password','$name','$email','$contact','$age','$gender')";

        if (mysqli_query($con, $sql)) {
            $response['status'] = 'success';
            $response['message'] = 'Data inserted successfully';
        } else {
            $response['status'] = 'failure';
            $response['error'] = $con->error;
        }

        // Close the database connection
        $con->close();
    } else {
        $response['status'] = 'failure';
        $response['message'] = 'Username or password not provided';
    }

    echo json_encode($response);
}
?>
