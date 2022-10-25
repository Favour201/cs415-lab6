package edu.jsu.mcis.lab6.dao;

import java.sql.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class AttendeeDAO {
    
    private final DAOFactory daoFactory;
    private final String QUERY_SELECT_BY_ID = "SELECT firstname, lastname, displayname" + "FROM attendee WHERE id = ?";
    private final String QUERY_CREATE = "INSERT INTO attendee (firstname, lastname, displayname" + "VALUES (?,?)";
    private final String QUERY_UPDATE = "UPDATE attendee SET "
            + "firstname = ?, lastname = ?, displayname = ? WHERE id = ?;";
    private final String QUERY_BY_REGISTRATION_NUMBER = "SELECT CONCAT(\"R\", LPAD(attendeeid, 6, 0) AS num FROM registration WHERE attendeeid = ?";
    
    AttendeeDAO(DAOFactory dao){
        this.daoFactory = dao;
    }
    
    public String findAttendee(int attendeeid) {
        JSONObject json = new JSONObject();
        json.put("success", false);
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;    
        PreparedStatement ps_2 = null;

        
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(QUERY_SELECT_BY_ID);
            ps.setInt(1, attendeeid);
           
            
            boolean hasresults = ps.execute();
            
            if(hasresults) {
                rs = ps.getResultSet();
                    while(rs.next()){
                        json.put("success", hasresults);

                        json.put("firstname", rs.getString("firstname"));
                        json.put("lastname", rs.getString("lastname"));
                        json.put("displayname", rs.getString("displayname"));
                    }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }

        }
        json.put("registrationnumber", getRegisNumByAttendeeID(attendeeid));
        return JSONValue.toJSONString(json);
    }
    
    public String createAttendee(Attendee a) {
        JSONObject json = new JSONObject();
        json.put("success", false);
        int result = 0;

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try{
            
            ps = conn.prepareStatement(QUERY_CREATE);
            ps.setString(1, a.getFirstname());
            ps.setString(2, a.getLastName());
            ps.setString(3, a.getDisplayName());
            
            int updateCount = ps.executeUpdate();
            
            if (updateCount > 0 ) {
                json.put("success", true);
                rs = ps.getGeneratedKeys();
                json.put("rowsAffected", updateCount);
                
                while(rs.next()){
                    result = rs.getInt(1);
                    json.put("id", result);
                }
            }
        }
        catch(Exception e) {e.printStackTrace();}
        finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }

        }

        return JSONValue.toJSONString(json);
    }
    
    public String updateAttendee(int id, String new_firstname, String new_lastname, String new_displayname) {
        
        JSONObject json = new JSONObject();
        json.put("success", false);
        boolean result = false;
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            
            ps = conn.prepareStatement(QUERY_UPDATE);
            
            ps.setString(1, new_firstname);
            ps.setString(2, new_lastname);
            ps.setString(3, new_displayname);
            ps.setInt(4, id);
           
            int updateCount = ps.executeUpdate();
            
            if (updateCount > 0 ) {
                json.put("success", true);
                json.put("rowsAffected", updateCount);
            }
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }

        }
        
        return JSONValue.toJSONString(json);
        
    }
    
    //To return a 6 digit registration code based on the attendeesid
    
    private String getRegisNumByAttendeeID(int attendeeid) {
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String idResults = "";
        
        try {
            ps = conn.prepareStatement(QUERY_BY_REGISTRATION_NUMBER);
            ps.setInt(1, attendeeid);
            
            boolean hasresults = ps.execute();
            
            if (hasresults) {
                rs = ps.getResultSet();
                
                if (rs.next()) {
                    idResults = rs.getString("num");
                }
            }
        }
        catch(Exception e) {e.printStackTrace();}
        finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }

        }
        
        return idResults;
    }
}

