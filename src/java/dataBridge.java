/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eddy_
 */
public class dataBridge extends HttpServlet {
    
    static String temperaturaAnt="a";
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
        /*try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet dataBridge</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet dataBridge at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
        DBhandler dbh = new DBhandler();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String date = sdf.format(new Date());
        Date date1 = new Date();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        
        int min = cal.get(Calendar.MINUTE);
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        
        String temp = (String) request.getParameter("temperature");
        PrintWriter out = response.getWriter();
        //out.println("<h1>Temperature Received " + temp + "</h1>");
        //out.println("<h1>Date :"+date+"</h1>");
        
        
        if(temp.equals("-1")){
            request.setAttribute("temp", temperaturaAnt);
            out.println(temperaturaAnt);
            temperaturaAnt="a";
            //RequestDispatcher dispatcher
            //    = getServletContext().getRequestDispatcher("/index.jsp");
            //dispatcher.forward(request, response);
            
        }else{
            temperaturaAnt=temp;
            dbh.loggTemp(Double.parseDouble(temp), min, hora, mes+1, dia, year);  
         
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
