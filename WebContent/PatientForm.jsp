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
             
        </h2>
    </center>
    <div align="center">
        <%--<c:if test="${patient != null}">
            <form action="update" method="post">
        </c:if>   --%>
        <c:if test="${patient == null}">
            <form action="enter" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${patient != null}">
                        Edit Patient
                    </c:if>
                    <c:if test="${patient == null}">
                        Add New Patient
                    </c:if>
                </h2>
            </caption>
                <c:if test="${patient != null}">
                    <input type="hidden" name="id" value="<c:out value='${patient.id}' />" />
                </c:if>           
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${patient.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Age: </th>
                <td>
                    <input type="text" name="age" size="45"
                            value="<c:out value='${patient.age}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="text" name="password" size="45"
                            value="<c:out value='${patient.pwd}' />"
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