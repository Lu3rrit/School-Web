<%-- 
    Document   : login
    Created on : Feb 12, 2022, 9:15:45 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">

    <head>
        
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <%
            String mess = (String) request.getAttribute("message");
        if(mess==null){
                mess="";
            }
        %>
        <title>Document</title>
    </head>

    <body>
       
        <div class="container" style="margin-top: 10px;">
            <div class="row"
                 style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
                <div class="col-sm-12">

                    <h2 class="myclass">đăng nhập</h2>
                    <form action="login" method="post" >
                         <i><%= mess %></i>
                        <div class="form-group">
                            <label> tài khoản </label> 
                            <input type="text" placeholder="username" name="username"><br>
                        </div>
                        <div class="form-group">
                            <label> mật khẩu </label> 
                             <input type="password" placeholder="password" name="password"><br>
                        </div>

                        <button type="submit" class="btn btn-primary">đăng nhập</button>
                        
                    </form>
                </div>
            </div>
    </body>

</html>