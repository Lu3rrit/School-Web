/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Login;

import dal.AccountDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.account.ParentAccount;
import model.account.TeacherAccount;

/**
 *
 * @author ASUS
 */
public class changepass extends BaseAuthController {

    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("../view/login/changep.jsp").forward(request, response);
    }
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String cfnewpass = request.getParameter("cfnewpass");
        AccountDB db  =  new AccountDB();
        String admin = (String) request.getSession().getAttribute("admin");
        if(admin.equals("0")){
            ParentAccount pacc = (ParentAccount)request.getSession().getAttribute("account");
            ParentAccount acc = db.getPA(pacc.getUsername(), oldpass);
            if(acc != null){
                if(newpass.equals(cfnewpass)){
                    request.setAttribute("message", "thành công");
                    db.ChangepassParent(acc.getUsername(), newpass);
                processRequest(request, response);
                }else{
                     request.setAttribute("message", "thất bại!xác nhận mật khẩu sai");
                processRequest(request, response);
                }
            }else{
              request.setAttribute("message", "thất bại! mật khẩu sai");
                processRequest(request, response);  
            }
        }else if(admin.equals("1")){
            TeacherAccount pacc = (TeacherAccount)request.getSession().getAttribute("account");
            TeacherAccount acc = db.getTA(pacc.getUssername(), oldpass);
            if(acc != null){
                if(newpass.equals(cfnewpass)){
                    request.setAttribute("message", "thành công");
                    db.ChangepassTeacher(acc.getUssername(), newpass);
                processRequest(request, response);
                }else{
                     request.setAttribute("message", "thất bại!xác nhận mật khẩu sai");
                processRequest(request, response);
                }
            }else{
              request.setAttribute("message", "thất bại! mật khẩu sai");
                processRequest(request, response);  
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
