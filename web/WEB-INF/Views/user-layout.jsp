<%-- 
    Document   : user-layout
    Created on : Feb 28, 2017, 1:02:15 PM
    Author     : kiett
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Entites.PersonTop10"%>
<%@page import="DAO.StaffDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to SML</title>
        <link href="${pageContext.servletContext.contextPath}/resources/CSS/Top10.css" rel="stylesheet" type="text/css"/>
        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.servletContext.contextPath}/resources/CSS/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script language="JavaScript" src="https://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/css/main.css" />
        <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->

    </head>

    <body class="landing">
        <div id="page-wrapper">

            <!-- Header -->
            <header id="header" class="alt">
                <h1><a href="${pageContext.servletContext.contextPath}/User/index">SML</a> by HTML5 UP</h1>
                <nav id="nav">
                    <ul>
                        <li><a href="${pageContext.servletContext.contextPath}/User/index">Home</a></li>
                        <li>
                            <a href="#" class="icon fa-angle-down">Layouts</a>
                            <ul>
                                <li><a href="generic.html">Generic</a></li>
                                <li><a href="contact.html">Contact</a></li>
                                <li><a href="elements.html">Elements</a></li>
                                <li>
                                    <a href="#">Submenu</a>
                                    <ul>
                                        <li><a href="#">Option One</a></li>
                                        <li><a href="#">Option Two</a></li>
                                        <li><a href="#">Option Three</a></li>
                                        <li><a href="#">Option Four</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li><a href="${pageContext.servletContext.contextPath}/Login/showForm" class="button">Sign Up</a></li>
                    </ul>
                </nav>
            </header>

            <!-- Banner -->
            <section id="banner">
                <h2>Alpha SML</h2>
                <p>Another fine responsive site template freebie by HTML5 UP.</p>
                <ul class="actions">
                    <li><a href="${pageContext.servletContext.contextPath}/Login/showForm" class="button special">Sign Up</a></li>
                    <li><a href="#" class="button">Learn More</a></li>
                </ul>
            </section>

            <!-- Main -->
            <section id="main" class="container">

                <section class="box special">
                    <header class="major">
                        <h2>Introducing the ultimate mobile app
                            <br />
                            for doing stuff with your phone</h2>
                        <p>Blandit varius ut praesent nascetur eu penatibus nisi risus faucibus nunc ornare<br />
                            adipiscing nunc adipiscing. Condimentum turpis massa.</p>
                    </header>
                    <span class="image featured"><img src="${pageContext.servletContext.contextPath}/resources/Images/pic01.jpg" alt="" /></span>
                </section>

                <section class="box special features">
                    <%
                        StaffDAO sd = new StaffDAO();
                        PersonTop10[] result = sd.getPersons();
                    %>
                    <c:set var="Result" value="<%=result%>"/>
                    <h3 class="text-center text-primary">TOP 10 SML Company's Staffs</h3>
                    <div class="features-row">
                        <c:forEach var="row" items="${Result}">
                        <section >
                            <input type="hidden" id="urlPhoto" value="${row.photo}"/>
                            <img src="${pageContext.servletContext.contextPath}/resources/Photos/${row.photo}" class="img-responsive img-circle center-block" width="210px" height="210px" alt="Responsive image"/>
                            <h3 class="text-primary">${row.name}</h3>
                            <h4 class="text-success">${row.depart}</h4>
                            <p class="text-center">Bonus : ${row.bonus} <br>  
                               Violate : ${row.violate}</p>
                            
                        </section>
                        </c:forEach>
                    </div>
                </section>

                <div class="row">
                    <div class="6u 12u(narrower)">

                        <section class="box special">
                            <span class="image featured"><img src="${pageContext.servletContext.contextPath}/resources/Images/pic02.jpg" alt="" /></span>
                            <h3>Sed lorem adipiscing</h3>
                            <p>Integer volutpat ante et accumsan commophasellus sed aliquam feugiat lorem aliquet ut enim rutrum phasellus iaculis accumsan dolore magna aliquam veroeros.</p>
                            <ul class="actions">
                                <li><a href="#" class="button alt">Learn More</a></li>
                            </ul>
                        </section>

                    </div>
                    <div class="6u 12u(narrower)">

                        <section class="box special">
                            <span class="image featured"><img src="${pageContext.servletContext.contextPath}/resources/Images/pic03.jpg" alt="" /></span>
                            <h3>Accumsan integer</h3>
                            <p>Integer volutpat ante et accumsan commophasellus sed aliquam feugiat lorem aliquet ut enim rutrum phasellus iaculis accumsan dolore magna aliquam veroeros.</p>
                            <ul class="actions">
                                <li><a href="#" class="button alt">Learn More</a></li>
                            </ul>
                        </section>

                    </div>
                </div>

            </section>

            <!-- CTA -->
            <section id="cta">

                <h2>Sign up for beta access</h2>
                <p>Blandit varius ut praesent nascetur eu penatibus nisi risus faucibus nunc.</p>

                <form>
                    <div class="row uniform 50%">
                        <div class="8u 12u(mobilep)">
                            <input type="email" name="email" id="email" placeholder="Email Address" />
                        </div>
                        <div class="4u 12u(mobilep)">
                            <input type="submit" value="Sign Up" class="fit" />
                        </div>
                    </div>
                </form>

            </section>

            <!-- Footer -->
            <footer id="footer">
                <ul class="icons">
                    <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                    <li><a href="" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                    <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
                    <li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
                    <li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
                    <li><a href="#" class="icon fa-google-plus"><span class="label">Google+</span></a></li>
                </ul>
                <ul class="copyright">
                    <li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
                </ul>
            </footer>

        </div>

        <!-- Scripts -->
        <script src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery.dropotron.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery.scrollgress.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/assets/js/skel.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/assets/js/util.js"></script>
        <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
        <script src="${pageContext.servletContext.contextPath}/resources/assets/js/main.js"></script>

    </body>


</html>
