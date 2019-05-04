package com.servlet;

import com.bean.HotelHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AUSTINS
 */
public class GetRoomQuantity extends HttpServlet {

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

            HotelHelper helper = new HotelHelper();
            String query = "SELECT roomtype FROM room WHERE occupied = 0";

            List<String> executive = new ArrayList<>();
            List<String> superior = new ArrayList<>();
            List<String> standard = new ArrayList<>();
            List<String> presidential = new ArrayList<>();
            List<String> hotelD_Rio = new ArrayList<>();

            try {
                PreparedStatement pstm = helper.getConnection().prepareStatement(query);
                ResultSet rst = pstm.executeQuery();
                while (rst.next()) {
                    String roomtype = rst.getString("roomtype");
                    if (roomtype.equalsIgnoreCase("Executive Suit")) {
                        executive.add(roomtype);
                    } else if (roomtype.equalsIgnoreCase("Presidential Suit")) {
                        presidential.add(roomtype);
                    }else if (roomtype.equalsIgnoreCase("Hotel De Rio Grand")) {
                        hotelD_Rio.add(roomtype);
                    }else if (roomtype.equalsIgnoreCase("Standard Suit")) {
                        standard.add(roomtype);
                    }else{
                        superior.add(roomtype);
                    }
                }
            } catch (SQLException e) {
            }
            
            int exec_no = executive.size();
            int sup_no = superior.size();
            int stan_no = standard.size();
            int pres_no = presidential.size();
            int hotel_no = hotelD_Rio.size();
            
            out.println(exec_no + "/" + sup_no + "/" + stan_no + "/" + pres_no + "/" +hotel_no);
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
