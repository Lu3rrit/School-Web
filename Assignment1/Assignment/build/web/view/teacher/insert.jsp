<%-- 
    Document   : insert
    Created on : Feb 19, 2022, 4:39:02 PM
    Author     : ASUS
--%>

<%@page import="model.entity.ClassStudent"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.awt.Desktop.Action"%>

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

    </head>
    <body>
        <div class="container" style="margin-top: 10px;">
            <div class="row"
                 style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
                <div class="col-sm-12">

                    <h2 class="myclass">thêm giáo viên</h2>
                    <form action="../teacher/insert" method="post" >
                        <div class="form-group">
                            <label>ID</label> 
                            <input type="text" 
                             required pattern="^[A-Z]{1}[0-9]{2}$"      class="form-control" name="teacherid" placeholder="Enter id">
                        </div>
                        <div class="form-group">
                            <label> họ và tên đệm </label> 
                            <input type="text" 
                                required pattern=".{1,}"    class="form-control" name="lastname" placeholder="Enter lastname">
                        </div>
                        <div class="form-group">
                            <label> tên </label> 
                            <input type="text" 
                                 required pattern=".{1,}"   class="form-control" name="firstname" placeholder="Enter firstname">
                        </div>

                        <div class="form-group">
                            giới tính: 
                            <input  type="radio" name="gender" checked="checked"  value="boy">nam
                            <input type="radio" name="gender" value="girl">nữ<br>
                        </div>
                        
                        

                       

                        <button type="submit" class="btn btn-primary">Save</button>
                        <button type="reset" class="btn btn-primary">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>