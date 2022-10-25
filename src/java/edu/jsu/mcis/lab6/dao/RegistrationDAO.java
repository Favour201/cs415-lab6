package edu.jsu.mcis.lab6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.simple.*;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    private final String QUERY_SELECT_BY_ID = "SELECT * FROM "
            + "((registration JOIN attendee ON registration.attendeeid = attendee.id) "
            + "JOIN `session` ON registration.sessionid = `session`.id) "
            + "WHERE `session`.id = ? AND attendee.id = ?";
    private final String QUERY_CREATE = "INSERT INTO registration (attendeeid, sessionid)" + "VALUES (?,?)";
    private final String QUERY_UPDATE = "UPDATE registration SET  sessionid = ?" + "WHERE attendeeid = ? AND sessionid = ?";
    private final String QUERY_DELETE_REG = "DELETE FROM registration WHERE (attendeeid = ? AND sessionid = ?";
    private final String QUERY_DELETE_ATTENDEE = "DELETE from attendee WHERE id = ?";
     private final String QUERY_BY_REGISTRATION_NUMBER = "SELECT CONCAT(\"R\", LPAD(attendeeid, 6, 0) AS num FROM registration WHERE attendeeid = ?";
    
    RegistrationDAO(DAOFactory dao) {
        this.daoFactory = dao;
    }
    
    public String find(int sessionid, int attendeeid) {

        JSONObject json = new JSONObject();
        json.put("success", false);

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps_2 = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(QUERY_SELECT_BY_ID);
            ps.setInt(1, sessionid);
            ps.setInt(2, attendeeid);
            
            boolean hasresults = ps.execute();

            if (hasresults) {

                rs = ps.getResultSet();
                
                if (rs.next()) {
                    
                    json.put("success", hasresults);
                    
                    json.put("attendeeid", rs.getInt("attendeeid"));
                    json.put("sessionid", rs.getInt("sessionid"));
                    json.put("firstname", rs.getString("firstname"));
                    json.put("lastname", rs.getString("lastname"));
                    json.put("displayname", rs.getString("displayname"));
                    json.put("session", rs.getString("description"));
                                        
                }

            }
            
            ps_2 = conn.prepareStatement(QUERY_BY_REGISTRATION_NUMBER);
            ps_2.setInt(1, attendeeid);
            ps_2.setInt(2, sessionid);
            
            boolean hasresults_2 = ps_2.execute();
            
            if(hasresults_2) {
                rs = ps_2.getResultSet();
                if(rs.next()){
                    json.put("registrationcode", rs.getString("num"));
                }
            }
        }
        catch (Exception e) {
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
    
    //To create registration for a new attendee
    
    public String create (int sessionid, int attendeeid) {
        JSONObject json = new JSONObject();
        
        json.put("success", false);
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = daoFactory.getConnection();
        
        try{
            
            ps = conn.prepareStatement(QUERY_CREATE);
            ps.setInt(1, attendeeid);
            ps.setInt(2, sessionid);
            
            int updateCount = ps.executeUpdate();
            
            if (updateCount > 0 ) {
                json.put("success", true);
                json.put("rowsAffected", updateCount);
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
    
    //To update an existing registration
    public String update(String sessionid, String attendeeid, String newsessionid) {
        JSONObject json = new JSONObject();
        
        json.put("success", false);
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = daoFactory.getConnection();
        
        int sessionid_int = Integer.parseInt(sessionid);
        int attendeeid_int = Integer.parseInt(attendeeid);
        int newSessionid_int = Integer.parseInt(newsessionid);
        
        try {
            
            ps = conn.prepareStatement(QUERY_UPDATE);
            ps.setInt(1, sessionid_int);
            ps.setInt(2, sessionid_int);
            ps.setInt(3, sessionid_int);
            
            int updateCount = ps.executeUpdate();
            
            if (updateCount > 0) {
                json.put("success", true);
                json.put("rowsAffected", updateCount);
            }
        }
        catch(Exception e){e.printStackTrace();}
        
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
    
    //To cancel or delete a registration
    public String delete (int attendeeid, int sessionid) {
    
        JSONObject json = new JSONObject();
        
        json.put("success", false);
        
        PreparedStatement ps = null;
        PreparedStatement ps_2 = null;

        ResultSet rs = null;
        Connection conn = daoFactory.getConnection();
        boolean result = false;
        
        try {
        
            ps = conn.prepareStatement(QUERY_DELETE_REG);
            
            ps.setInt(1, attendeeid);
            ps.setInt(2, sessionid);
            
            int updateCount = ps.executeUpdate();
            
            if (updateCount > 0 ) {
                result = true;
                json.put("success", true);
                json.put("rowsAffected", updateCount);
            }
            
            ps_2 = conn.prepareStatement(QUERY_DELETE_ATTENDEE);
            ps_2.setInt(1, attendeeid);
            ps_2.execute();
            
            json.put("success", result);
        }
          
        catch (Exception e){e.printStackTrace();}
        
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
    
}
