<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Establish the database connection
    require("conn.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

    // Query to fetch all patient details
    $sql = "SELECT username, name, age, dp FROM sign";
    $result = $con->query($sql);

    if ($result->num_rows > 0) {
        // Fetch the data and encode profile images as base64
        $patientList = array();
        while ($row = $result->fetch_assoc()) {
            $profileImage = $row['dp'];
            if (!empty($profileImage) && file_exists($profileImage)) {
                // Convert the image to base64
                $base64Image = base64_encode(file_get_contents($profileImage));
                $row['dp'] = $base64Image;
            } else {
                // Set a default image or handle the absence of the image
                $row['dp'] = ''; // You can set a default image here if needed
            }
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
?>
