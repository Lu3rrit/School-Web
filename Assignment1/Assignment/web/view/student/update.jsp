<%-- 
    Document   : update
    Created on : Feb 21, 2022, 8:34:02 PM
    Author     : ASUS
--%>

<%@page import="model.entity.Student"%>
<%@page import="model.entity.ClassStudent"%>
<%@page import="java.util.ArrayList"%>
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
            ArrayList<ClassStudent> classlist = (ArrayList<ClassStudent>) request.getAttribute("classes");
            Student st = (Student) request.getAttribute("student");
        %>
    </head>
    <body>
        <div class="container" style="margin-top: 10px;">
            <div class="row"
                 style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
                <div class="col-sm-12">

                    <h2 class="myclass">chỉnh sửa thông tin</h2>
                    <form action="../student/update" method="post" >
                        <div class="form-group">

                            <h4><%=st.getStudentID()%></h4>
                            <input type="hidden" name="id" value="<%=st.getStudentID()%>" /> <br/>
                        </div>
                        <div class="form-group">
                            <label> tên </label> 
                            <input type="text" 
                                required pattern=".{1,}"   class="form-control" name="firstname" placeholder="Enter firstname" value="<%= st.getFirstname()%>">
                        </div>
                        <div class="form-group">
                            <label> họ </label> 
                            <input type="text" 
                                required pattern=".{1,}"   class="form-control" name="lastname" placeholder="Enter lastname" value="<%= st.getLastname() %>">
                        </div>
                        <div class="form-group">
                            giới tính: 
                            <input type="radio"
                                           <%if (st.isGender()) { %>
                                           checked="checked"
                                           <%}%>
                                           name="gender" value="boy"/>nam
                            <input type="radio" 
                                   <%=(!st.isGender()) ? "checked=\"checked\"" : ""%> 
                                   name="gender" value="girl"/>nữ<br/>
                        </div>
                        <div class="form-group">
                            <label> ngày sinh </label> 
                            <input type="date" 
                                   class="form-control" name="dob" value="<%= st.getDob()%>">
                        </div>
                        <div class="form-group">
                            <label> lớp  </label> 
                            <select name="classid">

                                <%for (ClassStudent cl : classlist) {%>
                                <option <% if(cl.getClassID().equals( st.getClassID().getClassID())) { %> selected="selected" <%}%>
                                    value="<%= cl.getClassID()%>"><%= cl.getClassID()%></option>   
                                <%}
                                %>
                            </select>
                        </div>
                            <input type="hidden" name="oldclass" value="<%= st.getClassID().getClassID() %>" >
                        <div class="form-group">
                            <label> địa chỉ </label> 
                            <input type="text" 
                                required pattern=".{1,}"   class="form-control" name="adress" value="<%= st.getAddress() %>" >
                        </div>
                        <div class="form-group">
                            <label>Photo</label> <br/>

                            <input type="file" 
                                   class="form-control" name="photo" placeholder="Enter photo" value="<%= st.getPhoto() %>">
                        </div>

                        <button type="submit" class="btn btn-primary">Save</button>
                        <button type="reset" class="btn btn-primary">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>