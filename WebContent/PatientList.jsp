<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <name>Hospital Management</name>
</head>
<body>
    <center>
        <h1>Details of Patients</h1>
        <h2>
            <a href="add">Register here</a>
            &nbsp;&nbsp;&nbsp;
            <a href="show">List All Patients</a>
            <a href="select">Request a doctor</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Patients</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>age</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="patient" items="${showPatient}">
                <tr>
                    <td><c:out value="${patient.id}" /></td>
                    <td><c:out value="${patient.name}" /></td>
                    <td><c:out value="${patient.age}" /></td>
                    <td>
                        <a href="modify?id=<c:out value='${patient.id}' />">Modify</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="remove?id=<c:out value='${patient.id}' />">Remove</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>