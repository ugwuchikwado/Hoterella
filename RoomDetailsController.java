package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AUSTINS
 */
public class RoomDetailsController extends HttpServlet {

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

            String arrival = request.getParameter("arrival");
            String numberofdays = request.getParameter("numberofnights");
            String departure = request.getParameter("departure");
            String children = request.getParameter("children");
            String adults = request.getParameter("adults");

            String various[] = arrival.split("/");
            int arMonth = Integer.parseInt(various[0]);
            int arDay = Integer.parseInt(various[1]);
            int arYear = Integer.parseInt(various[2]);

            LocalDate lDate = LocalDate.of(arYear, arMonth, arDay);
            Date arrivalDate = Date.valueOf(lDate);

            Format formatter = new SimpleDateFormat("EEE, dd MMM yyyy");
            String date1 = formatter.format(arrivalDate);
            
            String variousd[] = departure.split("/");
            int dMonth = Integer.parseInt(variousd[0]);
            int dDay = Integer.parseInt(variousd[1]);
            int dYear = Integer.parseInt(variousd[2]);

            LocalDate ldDate = LocalDate.of(dYear, dMonth, dDay);
            Date departureDate = Date.valueOf(ldDate);

           
            String date2 = formatter.format(departureDate);

            HttpSession session = request.getSession();
            session.setAttribute("arrival", date1);
            session.setAttribute("numberofdays", numberofdays);
            session.setAttribute("departure", date2);
            session.setAttribute("children", children);
            session.setAttribute("adults", adults);

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
