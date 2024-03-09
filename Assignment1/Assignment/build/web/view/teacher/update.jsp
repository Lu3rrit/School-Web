<%-- 
    Document   : update
    Created on : Mar 11, 2022, 9:59:39 PM
    Author     : ASUS
--%>

<%@page import="model.entity.Teacher"%>
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
        Teacher t = (Teacher) request.getAttribute("t");
        %>
        

    </head>
    <body>
        <div class="container" style="margin-top: 10px;">
            <div class="row"
                 style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
                <div class="col-sm-12">

                    <h2 class="myclass">chỉnh sửa</h2>
                    <form action="../teacher/update" method="post" >
                        <div class="form-group">
                            <label>ID: <%= t.getTeacherID() %></label> 
                            <input type="hidden" 
                                   class="form-control" name="studentid" value="<%= t.getTeacherID() %>" placeholder="Enter id">
                        </div>
                        <div class="form-group">
                            <label> họ và tên đệm </label> 
                            <input type="text" 
                                   class="form-control" name="lastname" value="<%= t.getLastname() %>" placeholder="Enter lastname">
                        </div>
                        <div class="form-group">
                            <label> tên </label> 
                            <input type="text" 
                                   class="form-control" name="firstname" value="<%= t.getFirstname() %>" placeholder="Enter firstname">
                        </div>

                        <div class="form-group">
                            giới tính: 
                            <input  type="radio" name="gender"  <%=(t.isGender()) ? "checked=\"checked\"" : ""%>   value="boy">nam
                            <input  type="radio" name="gender"  <%=(!t.isGender()) ? "checked=\"checked\"" : ""%>   value="girl">nữ
                        </div>

                        <button type="submit" class="btn btn-primary">Save</button>
                        <button type="reset" class="btn btn-primary">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
