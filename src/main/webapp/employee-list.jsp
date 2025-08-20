<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
</head>
<body>
<h2>Employee List</h2>
<a href="new">Add New Employee</a>
<table border="1" cellpadding="6" cellspacing="0">
    <tr>
        <th>ID</th><th>Name</th><th>Email</th><th>Department</th><th>Salary</th><th>Actions</th>
    </tr>
    <c:forEach var="emp" items="${listEmployees}">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.name}</td>
            <td>${emp.email}</td>
            <td>${emp.department}</td>
            <td>${emp.salary}</td>
            <td>
                <a href="edit?id=${emp.id}">Edit</a>
                &nbsp;|&nbsp;
                <a href="delete?id=${emp.id}" onclick="return confirm('Delete?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="../">Home</a>
</body>
</html>
