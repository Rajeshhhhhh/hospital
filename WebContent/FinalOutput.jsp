<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <name>Hospital Management</name>
</head>
<body>
    <center>
        <h1>Details of Your Booking</h1>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            
            <tr>
                <th>DoctorID</th>
                <th>Patient Name</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="docpat" items="${llistDocPat}">
                <tr>
                    <td><c:out value="${docpat.doc_name}" /></td>
                    <td><c:out value="${docpat.name}" /></tr>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>