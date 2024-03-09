<%-- 
    Document   : insert
    Created on : Feb 12, 2022, 11:02:03 PM
    Author     : ASUS
--%>

<%@page import="model.account.TeacherAccount"%>
<%@page import="model.account.ParentAccount"%>
<%@page import="model.entity.Article"%>
<%@page import="model.entity.Student"%>
<%@page import="model.entity.Grade"%>
<%@page import="model.entity.ClassStudent"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">


        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>

        <link href="view/home/home.css" rel="stylesheet" type="text/css"/>
        <%
            ArrayList<Article> arts = (ArrayList<Article>) request.getAttribute("arts");
            int pageindex = (Integer) request.getAttribute("pageindex");
            int totalpage = (Integer) request.getAttribute("totalpage");
            String admin = (String) request.getSession().getAttribute("admin");
            if (admin == null) {
                admin = "-1";
            }

        %>
        <script src="view/home/home.js" type="text/javascript"></script>

    </head>
    <body>
        <div class="container">
            <nav id="nav" class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">            
                        <img id="iconhome" src="image/42496-school-icon (1).png" alt=""/> </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item item">
                                <a class="nav-link active" aria-current="page" href="home">News</a>
                            </li>

                            <li class="nav-item item">
                                <a class="nav-link active" aria-current="page" href="student/search">Students</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Mark
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="mark/list?classid=1A">1A</a></li>
                                    <li><a class="dropdown-item" href="mark/list?classid=1B">1B</a></li>
                                    <li><a class="dropdown-item" href="mark/list?classid=2A">2A</a></li>
                                    <li><a class="dropdown-item" href="mark/list?classid=2B">2B</a></li>
                                    <li><a class="dropdown-item" href="mark/list?classid=3A">3A</a></li>
                                    <li><a class="dropdown-item" href="mark/list?classid=3B">3B</a></li>
                                    <li><a class="dropdown-item" href="mark/list?classid=4A">4A</a></li>
                                    <li><a class="dropdown-item" href="mark/list?classid=4B">4B</a></li>
                                    <li><a class="dropdown-item" href="mark/list?classid=5A">5A</a></li>
                                    <li><a class="dropdown-item" href="mark/list?classid=5B">5B</a></li>

                                </ul>
                            </li>


                            <% if (admin.equals("1")) {%>
                            <li class="nav-item item">
                                <a class="nav-link active" aria-current="page" href="teacher/list">Teachers</a>
                            </li>
                            <form id="search" class="d-flex nav-item item" action="student/searchname" method="GET" >
                                <input class="form-control me-2" type="text" name="name" placeholder="tra cứu học sinh" aria-label="Search">
                                <button class="btn btn-primary" type="submit" value="add" name="add">search</button>

                            </form>


                            <%}%>
                            <%
                                if (admin.equals("-1")) {%>
                            <li class="nav-item item">
                                <a class="nav-link active" aria-current="page" href="login">Log in</a>
                            </li>
                            <%} else {%>



                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <% if (admin.equals("1")) {%>
                                    <i class='bx bxs-user'></i>Teacher account<%} else if (admin.equals("0")) {%>
                                    <i class='bx bxs-user'></i>Parent account<%}%></a> 
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="login/changepass">Change password</a></li>

                                    <li> <% if (admin.equals("0")) {
                                            ParentAccount pacc = (ParentAccount) request.getSession().getAttribute("account");%> 
                                        <a class="dropdown-item" href="student/infor?studentid=<%= pacc.getStudentID().getStudentID()%>">Information</a> <%} else if (admin.equals("1")) {
                                            TeacherAccount tacc = (TeacherAccount) request.getSession().getAttribute("account");%>
                                        <a  class="dropdown-item" href="teacher/infor?id=<%= tacc.getTeacherid().getTeacherID()%>">Information</a><%}%>

                                    <li><a class="dropdown-item" href="logout">Log out</a></li>

                                </ul>
                            </li>
                            <%}%>
                        </ul>

                    </div>
                </div>
            </nav>
            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="image/R (1).jpg" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="image/R.jpg" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="image/SFL8296.jpg" class="d-block w-100" alt="...">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

            <% if (admin.equals("1")) {%>
            <div class="row">
                <div class="col-sm-3">
                    <button onclick="window.location.href = 'article/insert'"  class="btn btn-primary">đăng tin</button>
                </div>
            </div>
            <%}%>


            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Articles</th>
                        <th scope="col"></th>

                    </tr>
                </thead>
                <tbody>

                    <% for (Article a : arts) {%>
                    <tr>
                        <td class="title"> <a href="article/detail?aid=<%= a.getId()%>"><%= a.getTitle()%></a></td>
                        <td class="date-art"> <i><%= a.getDate()%></i></td>

                    </tr>
                    <%   }
                    %>


                </tbody>
            </table>

            <div id="pagger">

            </div>
            <div class="foo">
                <p>trường tiểu học ABC</p>
            </div>
        </div>


    </div>
    <script>
        phantrang("pagger", <%= pageindex%>, <%= totalpage%>, 2);
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>
