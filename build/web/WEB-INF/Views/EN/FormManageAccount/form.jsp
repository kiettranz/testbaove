<%-- 
    Document   : form
    Created on : Jan 20, 2017, 7:20:09 PM
    Author     : kiett
--%>
<%@page import="Entites.Users"%>
<%@page import="DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.servletContext.contextPath}/resources/JS/Validate.js" type="text/javascript"></script>
            <script language="JavaScript" src="https://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
            <script language="JavaScript" src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js" type="text/javascript"></script>
            <script language="JavaScript" src="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.js" type="text/javascript"></script>
            <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.css">
            <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/JS/style1.js" type="text/javascript"></script> 


        <%
            UserDAO ud = new UserDAO();
            Users[] result = ud.searchUser("");
        %>
        <c:set var="Result" value="<%=result%>"/>
        
            <form:form action="${pageContext.servletContext.contextPath}/Account-mgr/" modelAttribute="Users1" class="form" width="100%" height="100%">
                <h2 class="text-center text-primary">SML's Accounts</h2>

                <div class="form-group">
                    <div class="">
                        <label class="text-success">Username: </label>
                        <form:input id="txtUsername" type="text" name="txtUsername" class="form-control" path="username" placeholder="Enter Username"/>
                        <div class="text-danger">${alertUsername}</div>
                    </div>
                </div>

                <div class="form-group">         
                    <div class="">
                        <label class="text-success">Password: </label>
                        <form:input id="txtPassword" name="txtPassword" class="form-control" placeholder="Enter Password" path="password"/>
                        <div class="text-danger">${alertPassword}</div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-group" style="border: 10px;">
                        <label class="text-info">Information: </label>
                        <br>
                        <div class="">
                            <label class="text-success">Fullname: </label>
                            <form:input id="txtFullname" name="txtFullname" class="form-control" placeholder="Enter Fullname" path="fullname"/>
                            <div class="text-danger">${alertFullname}</div>
                        </div>

                        <!--Nguy cơ bị đổi start-->
                        <div class="form-group">
                            <label class="text-success">Role: </label>
                            <form:hidden value="False" path="del" name="txtDel"/>
                            <form:select id="txtRole" name="txtRole" class="form-control" path="role" items="${listRole}" itemValue="role" itemLabel="role"/>
                            <div class="text-danger">${alertRole}</div>
                        </div>
                        <!--Nguy cơ bị đổi end -->

                    </div>
                </div>
                <button name="btnInsert" class="btn btn-group-justified btn-primary" width="100%">Add new</button>
                <div class="form-group"></div>
            </form:form>
            <hr>

            <table width="100%" class="table-hover" id="datatable">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Fullname</th>
                        <th>Role</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Fullname</th>
                        <th>Role</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </tfoot>
                <c:if test="${not empty Result}">
                    <tbody>
                        <c:forEach var="rows" items="${Result}">
                            <tr onclick="transValueToTextBox(this)" onmouseover="transValueToPopup(this);" >
                                <td>${rows.username}</td>
                                <td>${rows.password}</td>
                                <td>${rows.fullname}</td>
                                <td>${rows.role}</td>
                                <td><p data-placement="top" data-toggle="tooltip" title="Edit" ><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" ><span class="glyphicon glyphicon-pencil"></span></button></p></td>
                                <td><p data-placement="top" data-toggle="tooltip" title="Delete" ><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </c:if>
            </table>
            <form:form action="${pageContext.servletContext.contextPath}/Account-mgr/" modelAttribute="Users1" onsubmit="return validateForm()" >

                <div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true" >

                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h4 class="modal-title custom_align" id="Heading">Edit Your Detail</h4>
                            </div>
                            <div class="modal-body" onmousemove="transValueSelect();">

                                <div class="form-group">
                                    <label class="text-success">Username: </label>
                                    <input class="form-control "  id="PopupUsername" name="PopupUsername" readonly="true" type="text" placeholder="Username" value=""  >
                                </div>

                                <div class="form-group">
                                    <label class="text-success">Password: </label>
                                    <input class="form-control " id="PopupPassword" onchange="validatePassword()" name="PopupPassword" type="text" placeholder="Password" value="" >
                                    <div class="text-danger" id="alertPopupPassword"></div>
                                </div>

                                <div class="form-group">
                                    <label class="text-success">Fullname: </label>
                                    <input class="form-control" onchange="validateFullname()" id="PopupFullname" name="PopupFullname" type="text" placeholder="Fullname" value="" >
                                    <div class="text-danger" id="alertPopupFullname"></div>
                                </div>

                                <div class="form-group" >
                                    <label class="text-success">Role: </label>
                                    <form:select id="PopupRole" class="form-control" items="${listRole}" itemValue="role" itemLabel="role" path="role"/>
                                    <input type="hidden" id="PopupRole1" name="PopupRole1" value="">
                                </div>
                            </div>

                            <div class="modal-footer ">
                                <button type="submit" name="btnUpdate" class="btn btn-warning btn-lg" style="width: 100%;"><span class="glyphicon glyphicon-ok-sign"></span> Update</button>
                            </div>
                        </div>
                        <!-- /.modal-content --> 
                    </div>
                    <!-- /.modal-dialog --> 

                </div>

            </form:form>


            <form:form action="${pageContext.servletContext.contextPath}/Account-mgr/" modelAttribute="Users1">
                <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
                            </div>
                            <div class="modal-body">

                                <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>

                            </div>
                            <div class="modal-footer ">
                                <input type="hidden" id="PopupUsernameDel" name="PopupUsernameDel" value="">
                                <button type="submit" name="btnOK" class="btn btn-success" ><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
                            </div>
                        </div>
                        <!-- /.modal-content --> 
                    </div>
                    <!-- /.modal-dialog --> 
                </div>  
            </form:form>
        
   