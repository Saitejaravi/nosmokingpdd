<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Establish the database connection
    require("conn.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

    // Query to fetch all patient details
    $sql = "ALTER TABLE sign ADD COLUMN vidcdgst VARCHAR(300);";
    $result = $con->query($sql);

    if ($result->num_rows > 0) {
        // Fetch the data
        $patientList = array();

        while ($row = $result->fetch_assoc()) {
            $patientList[] = $row;
        }

        // Convert the array to a JSON string
        $response = json_encode($patientList);
        echo $response;
    } else {
        // No patients found
        $response['status'] = 'failure';
        $response['message'] = 'No patients found';
        echo json_encode($response);
    }

    // Close the database connection
    $con->close();
}
