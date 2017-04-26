<%-- 
    Document   : form
    Created on : Jan 31, 2017, 8:09:05 PM
    Author     : kiett
--%>

<%@page import="Entites.Departs"%>
<%@page import="DAO.DepartDAO"%>
<%@page import="DAO.StaffDAO"%>
<%@page import="Entites.Staffs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<base href="${pageContext.servletContext.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Staffs</title>
<script language="JavaScript" src="https://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script language="JavaScript" src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script language="JavaScript" src="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.css">
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>

<!--       <link href="${pageContext.servletContext.contextPath}/resources/CSS/style_datatable.css" rel="stylesheet" type="text/css"/>-->
<script src="${pageContext.servletContext.contextPath}/resources/JS/style1.js" type="text/javascript"></script> 
<script src="${pageContext.servletContext.contextPath}/resources/JS/ManageStaff_effectDropDown.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath}/resources/JS/Staff_Form.js" type="text/javascript"></script>


<button class="btn btn-primary" id="btnAddNew" data-toggle="tooltip" data-placement="left" ><span class="glyphicon glyphicon-plus-sign" id="ImageBtnAdd"></span> Add New...</button></p>
<form:form role="form" action="${pageContext.servletContext.contextPath}/Staffs-mgr/" id="formAddNew" enctype="multipart/form-data" method="POST">
<div class="col-xs-12 col-md-8">
    <legend>Information</legend>
    <div class="form-group">
        <label>Name:</label>
        <input type="text" class="form-control" name="txtName" placeholder="Enter name" >
    </div>
    <%
        DepartDAO dd = new DepartDAO();
        Departs[] result = dd.getDepart();
    %>
    <div class="form-group">
        <label>Depart:</label>
        <select class="form-control" name="txtDepart">
            <option>Choose...</option>
            <c:set var="ListDepart" value="<%=result%>"/>
            <c:forEach var="row" items="${ListDepart}">
                <option value="${row.depart_id}">${row.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <div>
            <label>Gender:</label>
        </div>
        <div class="col-md-1">
            <div class="radio">
                <label>
                    <input type="radio" name="rdGender" value="False">
                    Male
                </label>
            </div>
        </div>
        <div class="col-md-11">
            <div class="radio">
                <label>
                    <input type="radio" name="rdGender" value="True">
                    Female
                </label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label >Birthday:</label>
        <input type="date" class="form-control" name="txtDate" placeholder="Enter Date">
    </div>

    <div class="form-group">
        <label >Email:</label>
        <input type="email" class="form-control" name="txtEmail" placeholder="Enter Email">
    </div>

    <div class="form-group">
        <label >Phone</label>
        <input type="text"  class="form-control" name="txtPhone" placeholder="Enter Phone">
    </div>

    <div class="form-group">
        <label >Salary:</label>
        <input type="text"  class="form-control" name="txtSalary" placeholder="Enter Salary">
    </div>

    <div class="form-group">
        <label >Note:</label>
        <textarea type="text" class="form-control" name="txtNote" placeholder="Enter Note" rows="3" path="note"></textarea>
    </div>


    <button type="submit" name="btnInsert" class="btn btn-default">Add New...</button>

</div>

<div class="col-xs-6 col-md-4">
    <legend>Image</legend>
    <img src="${pageContext.servletContext.contextPath}/resources/Images/none.png" class="img-responsive" alt="Responsive image"/>
    <div class="form-group">
        <label for="exampleInputFile">Images input</label>
        <input name="txtPhoto" type="file" id="exampleInputFile">
    </div>
</div>


<hr>
</form:form>
<table width="100%" class="table-hover" id="datatable">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Birthday</th>
            <th>Email</th>
            <th>Phone</th>
            <th>View</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tfoot>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Birthday</th>
            <th>Email</th>
            <th>Phone</th>
            <th>View</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    </tfoot>
    <%
        StaffDAO sd = new StaffDAO();
        Staffs[] result1 = sd.searchStaff("");
    %>
    <tbody>
        <c:set var="Result1" value="<%=result1%>"/>
        <c:forEach var="rows" items="${Result1}">
            <tr onmousemove="transValue(this);">

                <td>${rows.id_staff}
                    <input type="hidden" value="${rows.salary}">
                    <input type="hidden" value="${rows.depart}">
                    <input type="hidden" value="${rows.note}">
                    <input type="hidden" value="${rows.photo}"></td>
                <td>${rows.name}</td>
                <td>
                    <c:choose>
                        <c:when test="${rows.gender == true}">Female</c:when>
                        <c:when test="${rows.gender == false}">Male</c:when>
                        <c:otherwise>Unknown</c:otherwise>
                    </c:choose> 
                </td>
                <td>${rows.birthday}</td>
                <td>${rows.email}</td>
                <td>${rows.phone}</td>
                <td><p data-placement="top" data-toggle="tooltip" title="View" ><button class="btn btn-primary btn-xs" data-title="View" data-toggle="modal" data-target="#view" ><span class="glyphicon glyphicon-search"></span></button></p></td>
                <td><p data-placement="top" data-toggle="tooltip" title="Edit" ><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" ><span class="glyphicon glyphicon-pencil"></span></button></p></td>
                <td><p data-placement="top" data-toggle="tooltip" title="Delete" ><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>
            </tr>

        </c:forEach>
    </tbody>

</table>
<form:form action="${pageContext.servletContext.contextPath}/Staffs-mgr/update" enctype="multipart/form-data" method="POST" >

    <div class="modal fade bs-example-modal-lg" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true" width="50%">

        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title custom_align" id="Heading">Edit Detail</h4>
                </div>
                <div class="modal-body">
                    <div class="col-xs-12 col-md-8">
                        <legend>Information</legend>
                        <div class="form-group">
                            <label class="text-success">ID: </label>
                            <input class="form-control" id="PopupId" name="PopupId" readonly="true" type="text" placeholder="Username" value="PS04197">
                        </div>

                        <div class="form-group">
                            <label class="text-success">Name: </label>
                            <input class="form-control " id="PopupName" name="PopupName" type="text" placeholder="Enter Name" value="Trần Anh Kiệt" >
                            <div class="text-danger" id="alertPopupName"></div>
                        </div>
                        <%
                            DepartDAO dd2 = new DepartDAO();
                            Departs[] result2 = dd2.getDepart();
                        %>
                        <div class="form-group">
                            <label>Depart:</label>
                            <select class="form-control" id="PopupDepart" name="PopupDepart">
                                <option>Choose...</option>
                                <c:set var="ListDepart2" value="<%=result2%>"/>
                                <c:forEach var="row" items="${ListDepart2}">
                                    <option value="${row.depart_id}">${row.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <div>
                                <label class="text-success">Gender:</label>
                            </div>
                            <div class="col-md-2">
                                <div class="radio">
                                    <label>
                                        <input type="radio" id="PopupGenderMale" name="PopupGender" value="true">
                                        Male
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-10">
                                <div class="radio">
                                    <label>
                                        <input type="radio" id="PopupGenderFemale" name="PopupGender" value="false">
                                        Female
                                    </label>
                                </div>
                            </div>
                           
                        </div>

                        <div class="form-group">
                            <label class="text-success">Birthday: </label>
                            <input class="form-control" id="PopupDate" name="PopupDate" type="date" placeholder="Enter Date" value="" >
                            <div class="text-danger" id="alertPopupDate"></div>
                        </div>

                        <div class="form-group" >
                            <label class="text-success">Email: </label>
                            <input class="form-control" id="PopupEmail" name="PopupEmail" type="email" placeholder="Enter Email" value="" >
                            <div class="text-danger" id="alertPopupEmail"></div>
                        </div>

                        <div class="form-group" >
                            <label class="text-success">Phone: </label>
                            <input class="form-control" id="PopupPhone" name="PopupPhone" type="text" placeholder="Enter Phone" value="" >
                            <div class="text-danger" id="alertPopupEmail"></div>
                        </div>

                        <div class="form-group" >
                            <label class="text-success">Salary: </label>
                            <input class="form-control" id="PopupSalary" name="PopupSalary" type="text" placeholder="Enter Salary" value="" >
                            <div class="text-danger" id="alertPopupEmail"></div>
                        </div>

                        <div class="form-group" >
                            <label class="text-success">Note: </label>
                            <textarea class="form-control" id="PopupNote" name="PopupNote" type="text" placeholder="Enter Note" value="" ></textarea>
                            <div class="text-danger" id="alertPopupEmail"></div>
                        </div>

                    </div>

                    <div class="col-xs-6 col-md-4">
                        <legend>Image</legend>
                        <img src="${pageContext.servletContext.contextPath}/resources/Images/none.png" class="img-responsive" alt="Responsive image" id="PopupImage"/>
                        <div class="form-group">
                            <label for="exampleInputFile">Images input</label>
                            <input type="hidden" id="PopupNameImg" name="PopupNameImg">
                            <input type="file" class="text-info" id="PopupPhoto" name="PopupPhoto">
                        </div>
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


<form:form action="${pageContext.servletContext.contextPath}/Staffs-mgr/delete" method="POST">
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
                    <input type="hidden" id="PopupIdDel" name="PopupIdDel" value="">
                    <button type="submit" name="btnOK" class="btn btn-success" ><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
                </div>
            </div>
            <!-- /.modal-content --> 
        </div>
        <!-- /.modal-dialog --> 
    </div>  
</form:form>

<div class="modal fade bs-example-modal-lg" id="view" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true" width="50%">

    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title custom_align" id="Heading">View Details</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="col-xs-6 col-sm-5">

                        <br>

                        <label class="text-primary">Depart: </label>
                        <p id="PopupViewDepart">Accounting</p>


                        <label class="text-primary">Salary: </label>
                        <p id="PopupViewSalary">$5000</p>


                        <label class="text-primary">About: </label>
                        <div class="clearfix"></div>
                        <p class="open_info" id="PopupViewNote">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>

                    </div>

                    <div class="col-xs-6 col-sm-4">
                        <input type="hidden" value="${pageContext.servletContext.contextPath}" id="PopupUrl">
                        <img src="${pageContext.servletContext.contextPath}/resources/Images/none.png" class="img-responsive" alt="Responsive image" id="PopupViewImage"/>

                    </div>
                </div>
            </div>

        </div>
        <!-- /.modal-content --> 
    </div>
    <!-- /.modal-dialog --> 

</div>        



