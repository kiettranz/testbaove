<%
    String view = request.getParameter("view");
    if(view.startsWith("EN/")){
        pageContext.forward("admin-layout.jsp");
    }else if(view.startsWith("User/")){
        pageContext.forward("user-layout.jsp");
    }else {
        pageContext.forward("blank-layout.jsp");
    }
   
%>
