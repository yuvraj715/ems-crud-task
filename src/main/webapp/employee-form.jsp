<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Form</title>
</head>
<body>
<h2><c:choose><c:when test="${employee != null}">Edit Employee</c:when><c:otherwise>Add New Employee</c:otherwise></c:choose></h2>
<form action="<c:out value='${employee != null ? "update" : "insert"}'/>" method="post">
    <input type="hidden" name="id" value="<c:out value='${employee.id}'/>" />
    Name: <input type="text" name="name" value="<c:out value='${employee.name}'/>" required/><br/><br/>
    Email: <input type="email" name="email" value="<c:out value='${employee.email}'/>" required/><br/><br/>
    Department: <input type="text" name="department" value="<c:out value='${employee.department}'/>" /><br/><br/>
    Salary: <input type="number" step="0.01" name="salary" value="<c:out value='${employee.salary}'/>" /><br/><br/>
    <input type="submit" value="Save"/>
</form>
<br/>
<a href="list">Back to List</a>
</body>
</html>
