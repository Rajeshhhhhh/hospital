<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <name>Hospital Management</name>
</head>
<body>
    <center>
        <h1>Details of Doctors</h1>
        <h2>
            <a href="new">Add a Doctor</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All Doctors</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${doctor != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${doctor == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${doctor != null}">
                        Edit Doctor
                    </c:if>
                    <c:if test="${doctor == null}">
                        Add New Doctor
                    </c:if>
                </h2>
            </caption>
                <c:if test="${doctor != null}">
                    <input type="hidden" name="id" value="<c:out value='${doctor.id}' />" />
                </c:if>           
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${doctor.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Designation: </th>
                <td>
                    <input type="text" name="designation" size="45"
                            value="<c:out value='${doctor.designation}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Dept: </th>
                <td>
                    <input type="text" name="dept" size="5"
                            value="<c:out value='${doctor.dept}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>