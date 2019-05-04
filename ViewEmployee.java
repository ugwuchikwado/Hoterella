
package com.servlet;

import com.bean.HotelBean;
import com.bean.HotelHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AUSTINS
 */
public class ViewEmployee extends HttpServlet {

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
            List<HotelBean> employeeList = new ArrayList<>();
            HotelHelper helper = new HotelHelper();
            ResultSet resultSet = helper.viewEmployee();
            try {
                 
            while (resultSet.next()) {                
                int id = resultSet.getInt("employeeid");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String phonenumber = resultSet.getString("phonenumber");
                String address = resultSet.getString("address");
                Date dob = resultSet.getDate("dob");
                String gender = resultSet.getString("gender");
                String nextOfKin = resultSet.getString("nextofkin");
                String nextOfKinPhone = resultSet.getString("nextofkinphone");
                String department = resultSet.getString("department");
                Date date = resultSet.getDate("approvaldate");                
                employeeList.add(new HotelBean(id, firstName, lastName, email, phonenumber, address, dob, gender, nextOfKin, nextOfKinPhone, department, date));
                                
            }
            HttpSession session = request.getSession();
            session.setAttribute("EMPLOYEE", employeeList);
            } catch (SQLException e) {
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
