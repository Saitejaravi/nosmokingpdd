<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Decode the JSON data
    $json = file_get_contents('php://input');
    $data = json_decode($json, true);

    if (isset($_POST["username"]) ) {
        $username = $_POST["username"];
        $name = $_POST["name"];
        $email = $_POST["email"];
        $contact = $_POST["contact"];
        $age = $_POST["age"];
        $gender = $_POST["gender"];
        $lang = $_POST["lang"];

        // Establish the database connection
        require("conn.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

        // Query to update data in the database
        $sql = "UPDATE sign 
                SET name='$name', email='$email', contact='$contact', age='$age', gender='$gender',lang = '$lang' 
                WHERE username='$username' ";

        if ($con->query($sql) === TRUE) {
            $response['status'] = 'success';
            $response['message'] = 'Data updated successfully';
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
