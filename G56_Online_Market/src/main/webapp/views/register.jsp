<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21.09.2025
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <style>
        /* Center the form on the page */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* Style the form container */
        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        /* Style labels */
        label {
            font-size: 16px;
            color: #333;
            margin-bottom: 8px;
            display: block;
        }

        /* Style input fields */
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.3);
        }

        /* Style the submit button */
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* Style success and error messages */
        p[style*="color: blue"] {
            color: #007bff;
            font-size: 14px;
            margin: 10px 0;
            text-align: center;
        }

        p[style*="color: red"] {
            color: #dc3545;
            font-size: 14px;
            margin: 10px 0;
            text-align: center;
        }

        /* Add spacing between form elements */
        br {
            display: none; /* Remove extra <br> tags for cleaner spacing */
        }

        form > * {
            margin-bottom: 15px;
        }

        /* Responsive design for smaller screens */
        @media (max-width: 500px) {
            form {
                width: 90%;
                padding: 15px;
            }
        }
    </style>

    <c:if test="${not empty message}"><p style="color: blue">${message}</p></c:if>
    <c:if test="${not empty error}"><p style="color: red">${error}</p></c:if>
</head>
<body>
<form action="/register" method="post">
    <label for="username">Enter username</label>
    <input type="text" id="username" name="username">
    <label for="password">Enter password</label>
    <input type="text" id="password" name="password">
    <input type="submit" style="color: white" value="Register">
</form>
</body>
</html>