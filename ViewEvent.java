
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
public class ViewEvent extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param query
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
                Date date = rst.getDate("event_date");
                String eventStart = rst.getString("event_start_time");
                String eventStop = rst.getString("event_stop_time");
                String hallNo = rst.getString("hall_number");
                double amount = rst.getDouble("payable_amount");
                String payment_status = rst.getString("payment_status");
                String checkedby = rst.getString("checkedby");
                String paymentmethod = rst.getString("payment_method");

                Format formatter = new SimpleDateFormat("EEE, dd MMM yyyy");
                String eventDate = formatter.format(date);
                
                int start = Integer.parseInt(eventStart.substring(0, 2));
                         String startTime = "";
                         String stopTime = "";
                         if (start>12) {
                                 start = start-12;
                                  startTime = String.valueOf(start)+eventStart.substring(2) +"PM";
                             } else if(start<12){
                                    startTime = String.valueOf(start)+eventStart.substring(2) +"AM";
                             }else{
                                 startTime = String.valueOf(start)+eventStart.substring(2) +"PM";
                             }
                        
                         int stop = Integer.parseInt(eventStop.substring(0, 2));
                         if (stop>12) {
                                 stop = stop-12;
                                 stopTime = String.valueOf(stop)+eventStop.substring(2) +"PM";
                             } else if(stop<12){
                                    stopTime = String.valueOf(stop)+eventStop.substring(2) +"AM";
                             }else{
                                 stopTime = String.valueOf(stop)+eventStop.substring(2) +"PM";
                             }

                Obj.put("ID", ID);
                Obj.put("fname", firstname);
                Obj.put("lname", lastname);
                Obj.put("email", email);
                Obj.put("phone", phone);
                Obj.put("eventDate", eventDate);
                Obj.put("startTime", startTime);
                Obj.put("stopTime", stopTime);
                Obj.put("amount", amount);
                Obj.put("hallNo", hallNo);
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             String action = request.getParameter("action");
            //out.println(action);
            if (action.equals("viewallevent")) {
                String query = "SELECT * FROM event_archive ORDER By id ASC";
                viewResult(query, request, response);
            }else if(action.equals("vieweventbyid")){                
                response.setContentType("text/html;charset=UTF-8");  
                
                 String id = request.getParameter("id");
                String query = "SELECT * FROM event_archive WHERE id = '" + id + "'";
                
                try {
                     HotelHelper helper = new HotelHelper();
                    PreparedStatement pst = helper.getConnection().prepareStatement(query);
                    ResultSet rst = pst.executeQuery();
                    
                    if (rst.next()) {
                         String ID = rst.getString("id");
                String firstname = rst.getString("fname");
                String lastname = rst.getString("lname");
                String email = rst.getString("email");
                String phone = rst.getString("phonenumber");
                Date date = rst.getDate("event_date");
                String eventStart = rst.getString("event_start_time");
                String eventStop = rst.getString("event_stop_time");
                String hallNo = rst.getString("hall_number");
                double amount = rst.getDouble("payable_amount");
                String payment_status = rst.getString("payment_status");
                String checkedby = rst.getString("checkedby");
                String paymentmethod = rst.getString("payment_method");

                Format formatter = new SimpleDateFormat("EEE, dd MMM yyyy");
                String eventDate = formatter.format(date);
                
                int start = Integer.parseInt(eventStart.substring(0, 2));
                         String startTime = "";
                         String stopTime = "";
                         if (start>12) {
                                 start = start-12;
                                  startTime = String.valueOf(start)+eventStart.substring(2) +"PM";
                             } else if(start<12){
                                    startTime = String.valueOf(start)+eventStart.substring(2) +"AM";
                             }else{
                                 startTime = String.valueOf(start)+eventStart.substring(2) +"PM";
                             }
                        
                         int stop = Integer.parseInt(eventStop.substring(0, 2));
                         if (stop>12) {
                                 stop = stop-12;
                                 stopTime = String.valueOf(stop)+eventStop.substring(2) +"PM";
                             } else if(stop<12){
                                    stopTime = String.valueOf(stop)+eventStop.substring(2) +"AM";
                             }else{
                                 stopTime = String.valueOf(stop)+eventStop.substring(2) +"PM";
                             }
                         
                         out.println(ID +"|" +firstname +"|"+lastname+"|"+email+"|"+phone+"|"+eventDate+"|"+startTime
                         +"|"+stopTime+"|"+amount+"|"+hallNo+"|"+payment_status+"|"+paymentmethod+"|"+checkedby);
                    }else{
                        
                    }
                } catch (NumberFormatException | SQLException e) {
                }
            }else if(action.equals("vieweventbymonth")){
                 int month = Integer.parseInt(request.getParameter("month"));
                String query = "SELECT * FROM event_archive WHERE MONTH(event_date) = " + month;
                viewResult(query, request, response);
            }else{
                int year = Integer.parseInt(request.getParameter("year"));
                String query = "SELECT * FROM event_archive WHERE YEAR(event_date) = " + year;
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
