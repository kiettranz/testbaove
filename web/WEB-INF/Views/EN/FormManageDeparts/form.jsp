<%-- 
    Document   : form
    Created on : Jan 31, 2017, 7:48:28 PM
    Author     : kiett
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Departs</title>
        <script language="JavaScript" src="https://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script language="JavaScript" src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script language="JavaScript" src="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.css">
        <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/JS/ManageRecord.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/JS/style1.js" type="text/javascript"></script> 


            <h1 class="text-center text-primary">Detail Records</h1>
            <form:form action="${pageContext.servletContext.contextPath}/Report/search" class="form">
                <legend class="text-primary">SEARCH</legend>
                <div class="form-group">
                    <label>Start:</label>
                    <input type="date" name="beginDate" class="form-control" >
                </div>
                <div class="form-group">
                    <label>End:</label>
                    <input type="date" name="endDate" class="form-control" >
                </div>
                <button type="submit" class="btn btn-default">Search...</button>
            </form:form>
            <h4 class="text-center text-info">Staff Record</h4>
            <h4 class="text-center text-primary">${msg}</h4>
            <c:set var="Staff" value="${Staff}"/>
            <c:if test="${not empty Staff}">
                <table id="datatable" class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Department</th>
                            <th>Email</th>
                            <th>Bonus</th>
                            <th>Violate</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="rows" items="${Staff}">
                            <tr>
                                <td>${rows.id}</td>
                                <td>${rows.name}</td>
                                <td>${rows.department}</td>
                                <td>${rows.email}</td>
                                <td>${rows.bonus}</td>
                                <td>${rows.violate}</td>
                                <td>${rows.bonus + rows.violate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Department</th>
                            <th>Email</th>
                            <th>Bonus</th>
                            <th>Violate</th>
                            <th>Total</th>
                        </tr>
                    </tfoot>
                </table>
            </c:if>

            <h4 class="text-center text-info">Department Record</h4>
            <c:set var="Department" value="${Department}"/>
            <c:if test="${not empty Department}">
                <table id="datatable1" class="table table-hover">
                    <thead>
                        <tr>          
                            <th>Department</th>
                            <th>Bonus</th>
                            <th>Violate</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="rows" items="${Department}">
                            <tr>
                                <td>${rows.department}</td>
                                <td>${rows.bonus}</td>
                                <td>${rows.violate}</td>
                                <td>${rows.bonus + rows.violate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Department</th>
                            <th>Bonus</th>
                            <th>Violate</th>
                            <th>Total</th>
                        </tr>
                    </tfoot>
                </table>
            </c:if>

        

