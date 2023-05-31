<?php
// Establish a database connection using PDO
$host = 'your_host';
$dbname = 'your_database';
$username = 'your_username';
$password = 'your_password';

try {
    $pdo = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Database connection failed: " . $e->getMessage());
}

// Process user login
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];

    // Prepare the SQL statement using parameterized query
    $query = "SELECT * FROM users WHERE username = ? AND password = ?";
    $stmt = $pdo->prepare($query);

    // Bind the user input to the prepared statement
    $stmt->bindParam(1, $username);
    $stmt->bindParam(2, $password);

    // Execute the query
    $stmt->execute();

    // Fetch the results
    $results = $stmt->fetchAll(PDO::FETCH_ASSOC);

    // Process the results
    if (count($results) > 0) {
        // Successful login
        echo "Login successful!";
        // Perform further actions as needed
    } else {
        // Invalid credentials
        echo "Invalid username or password!";
        // Perform appropriate error handling or redirect
    }
}
?>

<!-- HTML form for user login -->
<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
</head>
<body>
    <h1>User Login</h1>
    <form method="POST" action="">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <input type="submit" value="Login">
    </form>
</body>
</html>
