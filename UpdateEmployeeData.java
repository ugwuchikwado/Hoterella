
package com.servlet;

import com.bean.HotelBean;
import com.bean.HotelHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AUSTINS
 */
public class UpdateEmployeeData extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {           
           String firstName = request.getParameter("firstName");
           String lastName = request.getParameter("lastName");
           String email = request.getParameter("email");
           String phonenumber = request.getParameter("phonenumber");
           String address = request.getParameter("address");
           Date dob = Date.valueOf(request.getParameter("dob"));
           String nextOfKin = request.getParameter("nextOfKin");
           String nextOfKinPhone = request.getParameter("nextOfKinPhone");
           String department = request.getParameter("department");
            int id = Integer.parseInt(request.getParameter("id"));
            
            HotelBean bean = new HotelBean(firstName, lastName, email, phonenumber, address, dob, nextOfKin, nextOfKinPhone, department, id);
             HotelHelper helper = new HotelHelper();
             int returnCode = helper.updateEmployeeData(bean);
             
             if (returnCode == 1) {
                out.println("Employee data has been Succefully updated");
            } else {
                 out.println("Update failed");
            }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
