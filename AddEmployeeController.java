package com.servlet;

import com.bean.HotelBean;
import com.bean.HotelHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AUSTINS
 */
public class AddEmployeeController extends HttpServlet {

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

            String namePattern = "[^\\s][A-Za-z\\s]+";
            String numberPattern = "[\\d]{11}";
            String emailPattrn = "^[a-z]+[a-z.0-9-]+@[a-z.-]+(\\.[A-Za-z]{2,3})$";

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phonenumber = request.getParameter("phonenumber");
            String address = request.getParameter("address");
            Date dob = Date.valueOf(request.getParameter("dob"));
            String gender = request.getParameter("gender");
            String nextOfKin = request.getParameter("nextOfKin");
            String nextOfKinPhone = request.getParameter("nextOfKinPhone");
            String department = request.getParameter("department");
            Date registerDate = Date.valueOf(request.getParameter("registerDate"));

            if (!Pattern.compile(namePattern).matcher(firstName).matches() || firstName.isEmpty()) {
                out.println("Invalid Name Format");
            } else {
                if (!Pattern.compile(namePattern).matcher(lastName).matches() || lastName.isEmpty()) {
                    out.println("Invalid Name Format");
                } else {
                    if (!Pattern.compile(emailPattrn).matcher(email).matches() || email.isEmpty()) {
                        out.println("Invalid Email");
                    } else {
                        if (!Pattern.compile(numberPattern).matcher(phonenumber).matches() || phonenumber.isEmpty()) {
                            out.println("Invalid Phone Number");
                        } else {
                            if (gender.isEmpty()) {
                                out.println("Gender Field cannot be Empty");
                            } else {
                                if (!Pattern.compile(namePattern).matcher(nextOfKin).matches() || nextOfKin.isEmpty()) {
                                    out.println("Invalid Name Format");
                                } else {
                                    if (!Pattern.compile(numberPattern).matcher(nextOfKinPhone).matches() || nextOfKinPhone.isEmpty()) {
                                        out.println("Invalid Phone Number Format");
                                    } else {
                                        if (department.equals("Department")) {
                                            out.println("Invalid Department");
                                        } else {
                                            HotelBean bean = new HotelBean(firstName, lastName, email, phonenumber, address, dob, gender, nextOfKin, nextOfKinPhone, department, registerDate);
                                            HotelHelper helper = new HotelHelper();
                                            helper.addEmployee(bean);

                                            out.println("Employee Record has been Successfully Saved");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
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
