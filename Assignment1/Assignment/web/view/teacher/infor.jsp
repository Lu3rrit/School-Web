<%-- 
    Document   : infor
    Created on : Feb 20, 2022, 1:14:24 PM
    Author     : ASUS
--%>

<%@page import="model.account.TeacherAccount"%>
<%@page import="model.account.ParentAccount"%>
<%@page import="model.entity.Teacher"%>
<%@page import="model.entity.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href="../view/student/infor.css" rel="stylesheet" type="text/css"/>
        <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>

         <script>
            function deleteteacher(id)
            {
                var result = confirm("Are you sure?");
                if (result)
                {
                    window.location.href = '../teacher/delete?id=' + id;
                }
            }

        </script>
        <%
           Teacher t = (Teacher) request.getAttribute("t");
           String admin = (String) request.getSession().getAttribute("admin");
        %>


    </head>
    <body>
        <div class="container">
             <nav id="nav" class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">            
                        <img id="iconhome" src="../image/42496-school-icon (1).png" alt=""/> </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item item">
                                <a class="nav-link active" aria-current="page" href="../home">tin chính</a>
                            </li>

                            <li class="nav-item item">
                                <a class="nav-link active" aria-current="page" href="../student/search">danh sach</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Điểm
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="../mark/list?classid=1A">1A</a></li>
                                    <li><a class="dropdown-item" href="../mark/list?classid=1B">1B</a></li>
                                    <li><a class="dropdown-item" href="../mark/list?classid=2A">2A</a></li>
                                    <li><a class="dropdown-item" href="../mark/list?classid=2B">2B</a></li>
                                    <li><a class="dropdown-item" href="../mark/list?classid=3A">3A</a></li>
                                    <li><a class="dropdown-item" href="../mark/list?classid=3B">3B</a></li>
                                    <li><a class="dropdown-item" href="../mark/list?classid=4A">4A</a></li>
                                    <li><a class="dropdown-item" href="../mark/list?classid=4B">4B</a></li>
                                    <li><a class="dropdown-item" href="../mark/list?classid=5A">5A</a></li>
                                    <li><a class="dropdown-item" href="../mark/list?classid=5B">5B</a></li>

                                </ul>
                            </li>


                            <% if (admin.equals("1")) {%>
                            <li class="nav-item item">
                                <a class="nav-link active" aria-current="page" href="../teacher/list">ds giáo viên</a>
                            </li>
                             <form id="search" class="d-flex nav-item item" action="../student/searchname" method="GET" >
                                <input class="form-control me-2" type="text" name="name" placeholder="tra cứu học sinh" aria-label="Search">
                                <button class="btn btn-primary" type="submit" value="add" name="add">search</button>

                            </form>

                            <%}%>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class='bx bxs-user'> </i><%= admin.equals("1") ? "  giáo viên" : "  phụ huynh"%></a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="../login/changepass">đổi mật khẩu</a></li>

                                    <li> <% if (admin.equals("0")) {
                                        ParentAccount pacc = (ParentAccount) request.getSession().getAttribute("account");%> <a class="dropdown-item" href="../student/infor?studentid=<%= pacc.getStudentID().getStudentID() %>">thông tin</a> <%} 
                                        else if (admin.equals("1")) {
                                    TeacherAccount tacc = (TeacherAccount) request.getSession().getAttribute("account");%><a class="dropdown-item" href="../teacher/infor?id=<%= tacc.getTeacherid().getTeacherID() %>">thông tin</a><%}%></li>
                                    <li><a class="dropdown-item" href="../logout">đăng xuất</a></li>

                                </ul>
                            </li>
                        </ul>

                    </div>
                </div>
            </nav>
            <div class="information">
                <div class="row"
                     style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
                    <div class="col-sm-3">
                         </div>
                    <div class="col-sm-6">

                        <h2 class="myclass"> <%= t.getLastname()%> <%= t.getFirstname()%></h2>

                        <div class="form-group row">
                            <div class="col-sm-12">
                                <p>ID: <%= t.getTeacherID() %></p> 
                            </div>
                            

                        </div>
                        <div class="form-group row">
                            <div class="col-sm-12">
                                <label> giới tính: </label> 
                                <p><% if (t.isGender()) { %>nam<%} else { %>nữ<%}%></p>
                            </div>
                            <div class="col-sm-6">
                               
                            </div>
                        </div>

                        <a class="btn btn-danger" href="#" onclick="deleteteacher('<%= t.getTeacherID() %>')">xóa</a>
                        <a class="btn btn-warning" href="../teacher/update?id=<%= t.getTeacherID()  %>" >chỉnh sửa</a>
                        
                        

                    </div>
                        <div class="col-sm-3">
                         </div>
                </div>
            </div>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>