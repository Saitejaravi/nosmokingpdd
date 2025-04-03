<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Decode the JSON data
    $json = file_get_contents('php://input');
    $data = json_decode($json, true);

    if (isset($_POST["username"]) &&  isset($_POST["mon"])&&isset($_POST["rs"])) {
        $username = $_POST["username"];
        $mon = $_POST["mon"];
        $rs = $_POST["rs"];


        // Establish the database connection
        require("conn.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

        // Query to update points
        $sql = "UPDATE sign SET mon = '$mon', rs = '$rs' WHERE username = '$username'";
        $result = $con->query($sql);

        if ($result === TRUE) {
            // Update successful
            $response['status'] = 'success';
            $response['message'] = 'Points updated successfully';
        } else {
            // Update failed
            $response['status'] = 'failure';
            $response['message'] = 'Failed to update points: ' . $con->error;
        }

        // Close the database connection
        $con->close();
    } else {
        $response['status'] = 'failure';
        $response['message'] = 'Username or points not provided';
    }

    echo json_encode($response);
}
?>
