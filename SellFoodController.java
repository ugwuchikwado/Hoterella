
package com.servlet;

import com.bean.HotelHelper;
import com.bean.newBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author AUSTINS
 */
public class SellFoodController extends HttpServlet {

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
            String name = request.getParameter("name");
            int number_of_plates = Integer.parseInt(request.getParameter("number_of_plates"));
            int unitprice = Integer.parseInt(request.getParameter("unitprice"));
            int totalprice = Integer.parseInt(request.getParameter("totalprice"));
            String datesold = request.getParameter("datesold");
            String soldby = (String)request.getSession().getAttribute("name");
            String paymentMethod = request.getParameter("paymentMethod");
            Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
                StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                String query = "INSERT INTO soldfood (name, numberofplates, unitprice, totalprice, datesold, soldby, payment_method)"
                        + " VALUES ('"+name+"', "+number_of_plates+","+unitprice+","+totalprice+",'"+datesold+"','"+soldby+"', '"+paymentMethod+"')";
                
                SQLQuery qLQuery = session.createSQLQuery(query);
                qLQuery.executeUpdate();
                session.flush();
                transaction.commit();
                session.close();
                sessionFactory.close();
                
                out.println("Food sold Successfully..");
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
