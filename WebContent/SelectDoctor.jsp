<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <name>Hospital Management</name>
</head>
<body>
    <center>
        <h1>Request a  Doctor</h1>
            <a href="select">List All Doctors</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Doctors</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Designation</th>
                <th>Dept</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="doctor" items="${selectDoctor}">
                <tr>
                    <td><c:out value="${doctor.id}" /></td>
                    <td><c:out value="${doctor.name}" /></td>
                    <td><c:out value="${doctor.designation}" /></td>
                    <td><c:out value="${doctor.dept}" /></td>
                    <td>
                      
                        <a href="map?id=<c:out value='${doctor.id}' />">Select</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>