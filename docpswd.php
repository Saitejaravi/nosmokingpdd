<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Decode the JSON data
    $json = file_get_contents('php://input');
    $data = json_decode($json, true);

    if (isset($_POST["username"]) && isset($_POST["password"]) && isset($_POST["contact"])) {
        $username = $_POST["username"];
        $password = $_POST["password"];
        $contact = $_POST["contact"];

        // Establish the database connection
        require("conn.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

        // Query to update points
        $sql = "SELECT * FROM doct WHERE username = '$username' AND contact='$contact'";
        $result = $con->query($sql);

        if ($result->num_rows > 0) {
            // Login successful
            $sql = "UPDATE doct SET password='$password' WHERE username='$username'AND contact='$contact';";
            $result = $con->query($sql);

            if ($result === TRUE) {
                // Update successful
                $response['status'] = 'success';
                $response['message'] = 'Password updated successfully';
            } else {
                // Update failed
                $response['status'] = 'failure';
                $response['message'] = 'Failed to update password: ' . $con->error;
            }
        } else {
            // Login failed
            $response['status'] = 'invalid';
            $response['message'] = 'Invalid username or contact number';
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
