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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.account.ParentAccount;

/**
 *
 * @author SAP-LAP-FPT
 */
public abstract class BaseAuthController extends HttpServlet {

    private boolean isAuthenticated(HttpServletRequest request) {
        String admin = (String) request.getSession().getAttribute("admin");

        if (admin == null) {
            return false;
        } else if (admin.equals("0")) {
            String url = request.getServletPath();
            ParentAccount acc = (ParentAccount) request.getSession().getAttribute("account");
            AccountDB db = new AccountDB();
            int permission = db.getPermission(acc.getUsername(), url);
            return permission > 0;
        } else {
            return true;
        }

    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthenticated(request)) {
            //business
            processGet(request, response);
        } else {
            response.sendRedirect("../login");
        }
    }

    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthenticated(request)) {
            //business
            processPost(request, response);
        } else {
            response.sendRedirect("../login");
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
