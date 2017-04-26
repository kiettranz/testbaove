<%-- 
    Document   : form
    Created on : Feb 11, 2017, 7:13:57 PM
    Author     : kiett
--%>

<%@page import="Entites.Staffs"%>
<%@page import="DAO.StaffDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Records</title>
<script language="JavaScript" src="https://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script language="JavaScript" src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script language="JavaScript" src="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.css">
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/JS/ManageRecord.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath}/resources/JS/style1.js" type="text/javascript"></script> 

<h1 class="text-center text-primary">Record Manager</h1>
<p class="text-center text-warning"> ${msg}</p>
<p class="text-center text-warning"> ${msg1}</p>

    <table width="100%" class="table-hover" id="datatable">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Department</th>
                <th>Email</th>
                <th>Bonus</th>
                <th>Violate</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Department</th>
                <th>Email</th>
                <th>Bonus</th>
                <th>Violate</th>
            </tr>
        </tfoot>
        <%
            StaffDAO sd = new StaffDAO();
            Staffs[] result1 = sd.searchStaff("");
        %>
        <c:set var="Result" value="<%=result1%>"/>
        <c:if test="${not empty Result}">

            <tbody>
                <c:forEach var="rows" items="${Result}">
                    <tr onmouseover="transValueRecord(this);" >
                        <td>${rows.id_staff}</td>
                        <td>${rows.name}</td>
                        <td>${rows.depart}</td>
                        <td>${rows.email}</td>
                        <td><p data-placement="top" data-toggle="tooltip" title="Bonus" ><button class="btn btn-primary btn-xs" data-title="Bonus" data-toggle="modal" data-target="#Bonus" ><span class="glyphicon glyphicon-plus"></span></button></p></td>
                        <td><p data-placement="top" data-toggle="tooltip" title="Violate" ><button class="btn btn-danger btn-xs" data-title="Violate" data-toggle="modal" data-target="#Violate" ><span class="glyphicon glyphicon-minus"></span></button></p></td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:if>
    </table>


<form:form action="${pageContext.servletContext.contextPath}/Record/goodReport">
    <div class="modal fade" id="Bonus" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true" >

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title custom_align" id="Heading">Reason Why?</h4>
                </div>
                <div class="modal-body" onmousemove="">
                    <form class="form">
                        <div class="form-group">
                            <textarea class="form-control " rows="3" cols="5" id="goodReason" name="goodReason"></textarea>
                            <input type="hidden" value="" id="bonusId" name="bonusId"/>
                            <input type="hidden" value="" id="bonusEmail" name="bonusEmail"/>
                        </div>
                    </form>
                </div>

                <div class="modal-footer ">
                    <button type="submit" class="btn btn-warning btn-lg" style="width: 100%;" ><span class="glyphicon glyphicon-ok-sign"></span> Report</button>
                </div>
            </div>
            <!-- /.modal-content --> 
        </div>
        <!-- /.modal-dialog --> 
    </div>
</form:form>

<form:form action="${pageContext.servletContext.contextPath}/Record/badReport" class="form">
    <div class="modal fade" id="Violate" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true" >

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title custom_align" id="Heading">Reason Why?</h4>
                </div>
                <div class="modal-body" onmousemove="">
                    <form class="form">
                        <div class="form-group">
                            <textarea class="form-control" rows="3" cols="5" id="badReason" name="badReason"></textarea>
                            <input type="hidden" value="" id="violateId" name="violateId"/>
                            <input type="hidden" value="" id="violateEmail" name="violateEmail"/>
                        </div>
                    </form>
                </div>

                <div class="modal-footer ">
                    <button type="submit" class="btn btn-warning btn-lg" style="width: 100%;" ><span class="glyphicon glyphicon-ok-sign"></span> Report</button>
                </div>
            </div>
            <!-- /.modal-content --> 
        </div>
        <!-- /.modal-dialog --> 
    </div>
</form:form>


