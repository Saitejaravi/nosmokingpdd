<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Decode the JSON data
    $json = file_get_contents('php://input');
    $data = json_decode($json, true);

    if (isset($_POST["username"]) && isset($_POST["points"]) && isset($_POST["mon"])&& isset($_POST["log_dt"])) {
        $username = $_POST["username"];
        $points = $_POST["points"];
        $mon = $_POST["mon"];
        $log_dt = $_POST["log_dt"];

        // Establish the database connection
        require("conn.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

        // Query to update points
        $sql = "UPDATE sign SET points='$points', days=days+1, mon_sav=mon_sav+'$mon',dq_dt='$log_dt' WHERE username='$username';";
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
