<%-- 
    Document   : changep
    Created on : Mar 13, 2022, 7:18:25 PM
    Author     : ASUS
--%>

<%@page import="model.account.TeacherAccount"%>
<%@page import="model.account.ParentAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


        <title>JSP Page</title>
        <%
            String admin = (String)request.getSession().getAttribute("admin");
            String mess = (String) request.getAttribute("message");
            if(mess==null){
                mess="";
            }
        %>
    </head>
    <body>
        <div class="container" style="margin-top: 10px;">
            <div class="row"
                 style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
                <div class="col-sm-12">
                    
                    <h2 class="myclass"> đổi mật khẩu </h2>
                    <form action="changepass" method="post" >
                        <div class="form-group">
                            <h4><% if (admin.equals("0")) {
                                    ParentAccount pacc = (ParentAccount) request.getSession().getAttribute("account");%><%= pacc.getUsername()%><%} else if(admin.equals("1")) {
                                         TeacherAccount tacc = (TeacherAccount) request.getSession().getAttribute("account");%><%= tacc.getUssername()%><%}%></h4> 

                        </div>
                        <div class="form-group">
                            <label> mật khẩu cũ </label> 
                            <input type="password" 
                                   class="form-control" name="oldpass" placeholder="old pass">
                        </div>
                        <div class="form-group">
                            <label> mật khẩu mới </label> 
                            <input type="password" 
                                required pattern=".{6,}"   class="form-control" name="newpass" placeholder="new pass">
                        </div>
                        <div class="form-group">
                            <label> nhập lại mật khẩu mới </label> 
                            <input type="password" 
                                    required pattern=".{6,}" title="Eight or more characters" class="form-control" name="cfnewpass" placeholder="new pass">
                        </div>
                                         



                        <button type="submit" class="btn btn-primary">Save</button>
                        <button type="reset" class="btn btn-primary">Cancel</button>
                        
                    </form>
                                         
                </div>
                <i><%= mess %></i>
                
            </div>
        </div>
    </body>
</html>