<%-- 
    Document   : detail
    Created on : Feb 23, 2022, 5:59:36 PM
    Author     : ASUS
--%>

<%@page import="model.entity.Comment"%>
<%@page import="model.account.TeacherAccount"%>
<%@page import="model.account.ParentAccount"%>
<%@page import="model.entity.Mark"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.entity.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">


        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href="../view/mark/detail.css" rel="stylesheet" type="text/css"/>
        <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
        <%
            Student st = (Student) request.getAttribute("student");
            ArrayList<Mark> marks = (ArrayList<Mark>) request.getAttribute("marks");
            String hk1 = (String) request.getAttribute("hk1");
            String hk2 = (String) request.getAttribute("hk2");
            String admin = (String) request.getSession().getAttribute("admin");
            if (admin.equals("0")) {
                ParentAccount pacc = (ParentAccount) request.getSession().getAttribute("account");
            } else if (admin.equals("1")) {
                TeacherAccount tacc = (TeacherAccount) request.getSession().getAttribute("account");
            }

            ArrayList<Comment> cmts = (ArrayList<Comment>) request.getAttribute("cmts");
        %>

    </head>
    <body>

                                    
            <div class="content">
                <h2><%= st.getLastname()%> <%=st.getFirstname()%></h2>
                <h6>lớp <%= st.getClassID().getClassID()%></h6>
                <div class="hk1"> 
                    <h5>Học kì 1</h5>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">môn học</th>
                                <th scope="col">kiểm tra</th>
                                <th scope="col">thi giữa kì 1</th>
                                <th scope="col">thi cuối kì 1</th>

                            </tr>
                        </thead>
                        <tbody>

                            <% for (int i = 0; i < marks.size(); i++) {%>
                            <tr>
                                <th scope="row"><%= marks.get(i).getSubjectid().getSubjectName()%></td>
                                <td><% if (marks.get(i).getSmalltest1() == -1) {%>
                                    chưa có
                                    <%} else {%> <%= marks.get(i).getSmalltest1()%> <%}%> </td>
                                <td><% if (marks.get(i).getBigtest1() == -1) {%>
                                    chưa có
                                    <%} else {%> <%= marks.get(i).getBigtest1()%> <%}%> </td>
                                <td><% if (marks.get(i).getFinalltest1() == -1) {%>
                                    chưa có
                                    <%} else {%> <%= marks.get(i).getFinalltest1()%> <%}%> </td>
                            </tr>

                            <%}
                            %>
                            <tr>
                                <th scope="row"> xếp loại </th>
                                <td colspan="3">
                                    <%= hk1%>

                                </td>

                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="hk2">
                    <h5>Học kì 2</h5>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">môn học</th>
                                <th scope="col">kiểm tra</th>
                                <th scope="col">thi giữa kì 2</th>
                                <th scope="col">thi cuối kì 2</th>

                            </tr>
                        </thead>
                        <tbody>

                            <% for (int i = 0; i < marks.size(); i++) {%>
                            <tr>
                                <th scope="row"><%= marks.get(i).getSubjectid().getSubjectName()%></td>
                                <td><% if (marks.get(i).getSmalltest2() == -1) {%>
                                    chưa có
                                    <%} else {%> <%= marks.get(i).getSmalltest2()%> <%}%> </td>
                                <td><% if (marks.get(i).getBigtest2() == -1) {%>
                                    chưa có
                                    <%} else {%> <%= marks.get(i).getBigtest2()%> <%}%> </td>
                                <td><% if (marks.get(i).getFinalltest2() == -1) {%>
                                    chưa có
                                    <%} else {%> <%= marks.get(i).getFinalltest2()%> <%}%> </td>
                            </tr>

                            <%}
                            %>
                            <tr>
                                <th scope="row"> xếp loại </th>
                                <td colspan="3">
                                    <%= hk2%>

                                </td>

                            </tr>

                        </tbody>
                    </table>
                    <hr>
                    <% if (admin.equals("1")) {%><a class="btn btn-primary" href="update?stid=<%= st.getStudentID()%>"><i class='bx bx-edit-alt'>chỉnh sửa</i></a><%}%>

                    <div class="alert alert-primary" role="alert">
                        Tổng kêt: xếp loại <%= (hk1.equals(""))?"chưa hoàn thành": hk2 %>
                    </div>

                    <div id="comment">

                        <form action="../mark/detail" method="POST">
                            
                            <% if (admin.equals("0")) {
                                    ParentAccount pacc = (ParentAccount) request.getSession().getAttribute("account");%>
                                    <input type="hidden" name="username" value="<%= pacc.getUsername() %>" >
                            <%} else if (admin.equals("1")) {
                             TeacherAccount tacc = (TeacherAccount) request.getSession().getAttribute("account");%>
                            <input type="hidden" name="username" value="<%= tacc.getTeacherid().getFirstname() %>" ><%}%>
                            <input type="hidden" name="id" value="<%= st.getStudentID()%>">
                            <input type="hidden" name="per" value="<%= admin%>">
                            <input type="text" name="content" placeholder="nhận xét"><br>

                            <button class="btn btn-primary" type="submit">add </button>

                        </form>
                    </div>

                </div>

                <% for (Comment cmt : cmts) {%>
                <div  <%= cmt.isTeacher() ? "class=\"teacher-cmt\"" : "class=\"parent-cmt\""%>  >


                    <%
                    if(cmt.isTeacher()){%>
                        <h5> giáo viên <%= cmt.getNameuser() %></h5>
                    <%}else{%>
                        <h5>phụ huynh</h5>
                        <%}%>
                        
                    <i class="date"><%= cmt.getDate()%> </i>
                    <div class="row">
                        <div class="col-sm-10">
                            <p class="cmt"><%= cmt.getContent()%></p>
                        </div>
                        <%if ((admin.equals("0") && !cmt.isTeacher()) || (admin.equals("1") && cmt.isTeacher())) {%>
                        <div class="col-sm-2">
                            <a href="deletecmt?cid=<%= cmt.getCid()%>" ><i class='bx bx-trash'></i></a>
                        </div>
                        <%}
                        %>
                    </div>

                </div>
                <%}
                %>

            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
