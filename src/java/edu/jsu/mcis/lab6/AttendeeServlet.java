
package edu.jsu.mcis.lab6;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jsu.mcis.lab6.dao.*;
import java.io.PrintWriter;
import javax.servlet.ServletContext;

public class AttendeeServlet extends HttpServlet {

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/json;charset=UTF-8");
        
        try(PrintWriter out = response.getWriter()){
            
            AttendeeDAO dao = daoFactory.getAttendeeDAO();
            
            Integer p_id = Integer.parseInt(request.getParameter("id"));
            if (p_id == null || "".equals(p_id)) {
                
                System.err.println("Attendee GET request...");
            }
            else{
                System.err.println("Getting attendee...");
                out.println(dao.findAttendee(p_id));
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/json; charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            
            int attendeeid = Integer.parseInt(request.getParameter("attendeeid"));
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String displayname = request.getParameter("displayname");
            int sessionid = Integer.parseInt(request.getParameter("sessionid"));
            
            AttendeeDAO a_dao = daoFactory.getAttendeeDAO();
            RegistrationDAO r_dao = daoFactory.getRegistrationDAO();
            
            Attendee a_Obj = new Attendee(attendeeid, firstname, lastname, displayname);
            out.println(a_dao.createAttendee(a_Obj));
            out.println(r_dao.create(sessionid, attendeeid));
             
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        // INSERT YOUR CODE HERE
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/json; charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            String new_firstname = request.getParameter("newfirstname");
            String new_lastname = request.getParameter("newlastname");
            String new_displayname = request.getParameter("newdisplayname");
            
            AttendeeDAO dao = daoFactory.getAttendeeDAO();
            
            out.println(dao.updateAttendee(id, new_firstname, new_lastname, new_displayname));
            
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Attendee Servlet";
    }

}
