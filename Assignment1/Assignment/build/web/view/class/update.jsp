<%-- 
    Document   : update
    Created on : Mar 11, 2022, 9:59:39 PM
    Author     : ASUS
--%>

<%@page import="model.entity.Student"%>
<%@page import="model.entity.ClassStudent"%>
<%@page import="java.util.ArrayList"%>
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

        <%
            ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
//            int size = (Integer) request.getAttribute("size");
            ClassStudent cl = (ClassStudent) request.getAttribute("cl");
            
        %>
        <title>JSP Page</title>

    </head>
    <body>
        <div class="container" style="margin-top: 10px;">
            <div class="row"
                 style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
                <div class="col-sm-12">

                    <h2 class="myclass">chỉnh sửa</h2>
                    <form action="../class/update" method="post" >
                        <div class="form-group">
                            <h2><%= cl.getClassID() %></h2> 
                            <input type="hidden" name="cid" value="<%= cl.getClassID() %>" >
                        </div>
                        <div class="form-group">
                            <label> giáo viên </label> 
                            <input type="text" 
                                   class="form-control" name="tid" value="<%= cl.getTeacherID().getTeacherID() %>" placeholder="teacher">
                        </div>
               

                        <button type="submit" class="btn btn-primary">Save</button>
                        <button type="reset" class="btn btn-primary">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
