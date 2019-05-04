package com.servlet;

import com.bean.HotelHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Month;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author AUSTINS
 */
public class ViewReservation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void viewResult(String query, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            HotelHelper helper = new HotelHelper();
            PreparedStatement pst = helper.getConnection().prepareStatement(query);
            ResultSet rst = pst.executeQuery();
            JSONArray jSONArray = new JSONArray();
            while (rst.next()) {

                JSONObject Obj = new JSONObject();

                String ID = rst.getString("id");
                String firstname = rst.getString("fname");
                String lastname = rst.getString("lname");
                String email = rst.getString("email");
                String phone = rst.getString("phonenumber");
                Date arrival = rst.getDate("check_in_date");
                Date departure = rst.getDate("check_out_date");
                String medical = rst.getString("medical_condition");
                String roomNo = rst.getString("room_number");
                double amount = rst.getDouble("amount_payed");
                String payment_status = rst.getString("payment_status");
                String checkedby = rst.getString("checkedby");
                String paymentmethod = rst.getString("payment_method");

                Format formatter = new SimpleDateFormat("EEE, dd MMM yyyy");
                String arrivaldate = formatter.format(arrival);
                String departuredate = formatter.format(departure);

                Obj.put("ID", ID);
                Obj.put("fname", firstname);
                Obj.put("lname", lastname);
                Obj.put("email", email);
                Obj.put("phone", phone);
                Obj.put("arrival", arrivaldate);
                Obj.put("departure", departuredate);
                Obj.put("medical", medical);
                Obj.put("amount", amount);
                Obj.put("roomNo", roomNo);
                Obj.put("paymentstatus", payment_status);
                Obj.put("paymentmethod", paymentmethod);
                Obj.put("checkedby", checkedby);
                jSONArray.put(Obj);
            }

            response.getWriter().println(jSONArray.toString());

        } catch (IOException | SQLException | JSONException e) {
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            //out.println(action);

            if (action.equals("viewallreservation")) {
                String query = "SELECT * FROM reservation_archive ORDER By id ASC";
                viewResult(query, request, response);
            } else if (action.equals("viewreservationbyid")) {
                String id = request.getParameter("id");
                String query = "SELECT * FROM reservation_archive WHERE id = '" + id + "'";
                try {
                    HotelHelper helper = new HotelHelper();
                    PreparedStatement pst = helper.getConnection().prepareStatement(query);
                    ResultSet rst = pst.executeQuery();                   

                    if (rst.next()) {
                        response.setContentType("text/html;charset=UTF-8");                       

                        String ID = rst.getString("id");
                        String firstname = rst.getString("fname");
                        String lastname = rst.getString("lname");
                        String email = rst.getString("email");
                        String phone = rst.getString("phonenumber");
                        Date arrival = rst.getDate("check_in_date");
                        Date departure = rst.getDate("check_out_date");
                        String medical = rst.getString("medical_condition");
                        double amount = rst.getDouble("amount_payed");
                        String roomNo = rst.getString("room_number");                        
                        String payment_status = rst.getString("payment_status");
                        String checkedby = rst.getString("checkedby");
                        String paymentmethod = rst.getString("payment_method");

                        Format formatter = new SimpleDateFormat("EEE, dd MMM yyyy");
                        String arrivaldate = formatter.format(arrival);
                        String departuredate = formatter.format(departure);

                        out.println(ID + "|" + firstname + "|" + lastname + "|" + email + "|" + phone + "|" 
                                + arrivaldate + "|" + departuredate + "|" + medical + "|" + amount+ "|" + roomNo + "|"
                                + payment_status + "|" + paymentmethod + "|" + checkedby);


                    } else {
                    }
                } catch (SQLException e) {
                    out.println(e.getMessage());
                }
            } else if (action.equals("viewreservationbymonth")) {
                int month = Integer.parseInt(request.getParameter("month"));
                String query = "SELECT * FROM reservation_archive WHERE MONTH(check_in_date) = " + month;
                viewResult(query, request, response);
            } else if (action.equals("viewreservationbyyear")) {
                int year = Integer.parseInt(request.getParameter("year"));
                String query = "SELECT * FROM reservation_archive WHERE YEAR(check_in_date) = " + year;
                viewResult(query, request, response);
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
