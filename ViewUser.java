package com.servlet;

import com.bean.HotelHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class ViewUser extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HotelHelper helper = new HotelHelper();
            JSONArray jsona = new JSONArray();
            try {
                ResultSet rst = helper.viewUser();

                while (rst.next()) {
               
                    JSONObject obj = new JSONObject();

                    String id = rst.getString("userid");
                    String name = rst.getString("name");
                    String email = rst.getString("email");
                    String phonenumber = rst.getString("phonenumber");
                    String address = rst.getString("address");
                    String gender = rst.getString("gender");
                    String nextOfKin = rst.getString("nextofkin");
                    String nextOfKinPhone = rst.getString("nextofkinphone");
                    Date registerDate = rst.getDate("registerdate");

                    Format formatter = new SimpleDateFormat("EEE, dd MMM yyyy");
                    String date = formatter.format(registerDate);
   // out.println(id + name + email);
                    obj.put("ID", id);
                    obj.put("name", name);
                    obj.put("email", email);
                    obj.put("phone", phonenumber);
                    obj.put("address", address);
                    obj.put("gender", gender);
                    obj.put("nok", nextOfKin);
                    obj.put("nokp", nextOfKinPhone);
                    obj.put("date", date);
                    jsona.put(obj);
                }
                out.println(jsona.toString());

            } catch (SQLException | JSONException e) {
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
