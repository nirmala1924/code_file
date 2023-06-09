import mysql.connector
db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="password",
    database="mydatabase"
)

cursor = db.cursor()

username = input("Enter your username: ")
password = input("Enter your password: ")

sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'"

cursor.execute(sql)

result = cursor.fetchall()

if len(result) > 0:
    print("Login successful!")
else:
    print("Login failed.")