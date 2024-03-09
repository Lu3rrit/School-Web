<%-- 
    Document   : detail
    Created on : Feb 23, 2022, 5:59:36 PM
    Author     : ASUS
--%>

<%@page import="model.entity.Mark"%>
<%@page import="java.util.ArrayList"%>
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
        <link href="../view/mark/update.css" rel="stylesheet" type="text/css"/>
        <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
        <%
            Student st = (Student) request.getAttribute("student");
            ArrayList<Mark> marks = (ArrayList<Mark>) request.getAttribute("marks");

        %>

    </head>
    <body>


        <div class="content row">


            <div class="col-sm-12">
                <h2><%= st.getLastname()%> <%=st.getFirstname()%></h2>
                <h6>lớp <%= st.getClassID().getClassID()%></h6>
                <h5><%= st.getStudentID()%></h5>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <td></td>
                            <td colspan="3">học kì 1</td>
                            <td colspan="3"> học kì 2 </td>

                        </tr>
                        <tr>
                            <th scope="col">môn học</th>
                            <th scope="col">kiểm tra</th>
                            <th scope="col">giữa kì 1</th>
                            <th scope="col">cuối kì 1</th>
                            <th scope="col">kiểm tra</th>
                            <th scope="col">giữa kì 2</th>
                            <th scope="col">cuối kì 2</th>


                        </tr>
                    </thead>

                    <tbody>



<!--                    <form action="../mark/update" method="POST">-->
                        <form action="/mark/update" method="POST">
                         <input type="hidden" name="stid" value="<%= st.getStudentID()%>">
                        <% for (int i = 0; i < marks.size(); i++) {%>
                        <tr>
                       
                        <input type="hidden" name="sjid" value="<%= marks.get(i).getSubjectid().getSubjectID()%>">
                        <th scope="row"><%= marks.get(i).getSubjectid().getSubjectName()%></th>
                        <td><input type="number"  min="0" max="10" name="sm1<%=marks.get(i).getSubjectid().getSubjectID()  %>" value="<% if (marks.get(i).getSmalltest1() == -1) {%><%} else {%><%= marks.get(i).getSmalltest1()%><%}%>"> </td>
                        <td><input type="number"  min="0" max="10" name="bi1<%=marks.get(i).getSubjectid().getSubjectID()  %>" value="<% if (marks.get(i).getBigtest1() == -1) {%><%} else {%><%= marks.get(i).getBigtest1()%><%}%>"> </td>
                        <td><input type="number"  min="0" max="10" name="fi1<%=marks.get(i).getSubjectid().getSubjectID()  %>" value="<% if (marks.get(i).getFinalltest1() == -1) {%><%} else {%><%= marks.get(i).getFinalltest1()%><%}%>"> </td>
                        <td><input type="number"  min="0" max="10" name="sm2<%=marks.get(i).getSubjectid().getSubjectID()  %>" value="<% if (marks.get(i).getSmalltest2() == -1) {%><%} else {%><%= marks.get(i).getSmalltest2()%><%}%>"> </td>
                        <td><input type="number"  min="0" max="10" name="bi2<%=marks.get(i).getSubjectid().getSubjectID()  %>" value="<% if (marks.get(i).getBigtest2() == -1) {%><%} else {%><%= marks.get(i).getBigtest2()%><%}%>"> </td>
                        <td><input type="number"  min="0" max="10" name="fi2<%=marks.get(i).getSubjectid().getSubjectID()  %>" value="<% if (marks.get(i).getFinalltest2() == -1) {%><%} else {%><%= marks.get(i).getFinalltest2()%><%}%>"> </td>
                        </tr>


                        <%}
                        %>
                        <tr><td colspan="7" > <button class="btn btn-primary" type="submit" value="add" name="add">save</button><button type="reset" class="btn btn-primary">cancel</button></td> </tr>
                    </form>
                    </tbody>
                </table>
            </div>
            <a href="../mark/detail?studentid=<%= st.getStudentID()%>">quay về</a>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
