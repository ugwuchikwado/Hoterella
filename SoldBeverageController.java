package com.servlet;

import com.bean.HotelHelper;
import com.bean.newBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
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
public class SoldBeverageController extends HttpServlet {

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

            String beverageId = request.getParameter("beverageId");
            String category = request.getParameter("beverageBrand");
            String name = request.getParameter("beverageName");
            int quantitySold = Integer.parseInt(request.getParameter("quantitySold"));
            double PriceSold = Double.parseDouble(request.getParameter("Price"));
            Date date = Date.valueOf(request.getParameter("dateSold"));
            String soldby = (String) request.getSession().getAttribute("name");
            String paymentMethod = request.getParameter("paymentMethod");
          
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
                StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                String query = "INSERT INTO soldbeverage (beverageId, category, name, quantitysold, priceslod, datesold, soldby, payment_method)"
                        + " VALUES ('"+beverageId+"', '"+category+"','"+name+"',"+quantitySold+","+PriceSold+",'"
                        +date+"', '"+soldby+"', '"+paymentMethod+"')";
                SQLQuery qLQuery = session.createSQLQuery(query);
                qLQuery.executeUpdate();
                session.flush();
                transaction.commit();
                session.close();
                sessionFactory.close();
                
                out.println("Product sold Successfully..");
            } catch (HibernateException e) {
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
