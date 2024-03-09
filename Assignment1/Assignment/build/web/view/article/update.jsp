<%-- 
    Document   : newjsp
    Created on : Mar 8, 2022, 9:38:51 PM
    Author     : ASUS
--%>

<%@page import="model.entity.Article"%>
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
        Article art =  (Article)request.getAttribute("art");
        %>
    </head>
    <body>
        <div class="container" style="margin-top: 10px;">
            <div class="row"
                 style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
                <div class="col-sm-12">

                    <h2 class="myclass">chỉnh sửa tin</h2>
                    <form action="../article/update" method="post" >
                        <input type="hidden" name="aid" value="<%= art.getId() %>">
                        <div class="form-group">
                            <label>tiêu đề</label> 
                            <input type="text" 
                                   class="form-control" name="title" value="<%= art.getTitle() %>">
                        </div>
                        <div class="form-group">
                            <label> nội dung </label> 
                            <textarea name="content" class="text" cols="67" rows ="10"   ><%= art.getContent() %></textarea>
                        </div>

                        <div class="form-group">
                            <label>Photo</label> <br/>

                            <input type="file" 
                                   class="form-control" name="photo" placeholder="Enter photo" <%= art.getImage() %>>
                        </div>

                        <button type="submit" class="btn btn-primary">Save</button>
                        <button type="reset" class="btn btn-primary">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>