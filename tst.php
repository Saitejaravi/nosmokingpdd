<?php

// Check if the image path is provided in the GET request
if (isset($_GET['image_path'])) {
    $imagePath = $_GET['image_path'];

    // Check if the file exists
    if (file_exists($imagePath)) {
        // Read the image file
        $imageData = file_get_contents($imagePath);

        // Convert image data to base64
        $base64Image = base64_encode($imageData);

        // Prepare response array
        $response = array(
            'status' => 'success',
            'message' => 'Image converted to base64 successfully',
            'base64Image' => $base64Image
        );

        // Convert response array to JSON format
        echo json_encode($response);
    } else {
        // If the file does not exist, return error response
        $response = array(
            'status' => 'error',
            'message' => 'File does not exist'
        );

        // Convert response array to JSON format
        echo json_encode($response);
    }
} else {
    // If image path is not provided in the GET request, return error response
    $response = array(
        'status' => 'error',
        'message' => 'Image path not provided'
    );

    // Convert response array to JSON format
    echo json_encode($response);
}

?>
