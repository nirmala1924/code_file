-- Create the 'users' table
CREATE TABLE users (
  id INT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE,
  password VARCHAR(255) NOT NULL
);

 

-- Create the 'orders' table
CREATE TABLE orders (
  id INT PRIMARY KEY,
  user_id INT,
  product VARCHAR(100),
  quantity INT,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

 

-- Insert data into the 'users' table
INSERT INTO users (id, username, email, password)
VALUES (1, 'john_doe', 'john@example.com', 'password123'),
       (2, 'jane_smith', 'jane@example.com', 'p@ssw0rd!');

 

-- Insert data into the 'orders' table
INSERT INTO orders (id, user_id, product, quantity)
VALUES (1, 1, 'Product A', 2),
       (2, 2, 'Product B', 3),
       (3, 1, 'Product C', 1);

 

-- Select all users
SELECT * FROM users;

 

-- Select orders for a specific user
SELECT o.id, o.product, o.quantity
FROM orders o
JOIN users u ON u.id = o.user_id
WHERE u.username = 'john_doe';

 

-- Update user email
UPDATE users
SET email = 'john.doe@example.com'
WHERE id = 1;

 

-- Delete an order
DELETE FROM orders
WHERE id = 2;