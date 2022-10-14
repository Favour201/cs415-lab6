package edu.jsu.mcis.lab6.dao;

import java.sql.*;
import org.json.simple.*;

public class TrainingSessionDAO {
    
    private final DAOFactory daoFactory;
    
    private final String QUERY_SELECT_WITHOUT_ID = "SELECT * FROM `session`";
    private final String QUERY_SELECT_BY_ID = "SELECT * FROM attendee, registration, `session` where sessionid = ? order by firstname";
    
    TrainingSessionDAO (DAOFactory dao) {
        this.daoFactory = dao;
    }
    
    public String findSession() {
        //int id; String description;
        JSONObject json = new JSONObject();
        json.put("success", false);
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(QUERY_SELECT_WITHOUT_ID);
            
            boolean hasresults = ps.execute();
            
            if (hasresults) {
                rs = ps.getResultSet();
                
                if (rs.next()) {
                
                    json.put("success", hasresults);
                    
                    json.put("id", rs.getInt("id"));
                    json.put("description", rs.getString("description"));
                }
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
    
    public String findSessionById(int sessionid) {
        JSONObject json = new JSONObject();
        json.put("success", false);
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(QUERY_SELECT_BY_ID);
            ps.setInt(1, sessionid);
            
            boolean hasresults = ps.execute();
            
            if (hasresults) {
                rs = ps.getResultSet();
                
                if (rs.next()) {
                
                    json.put("success", hasresults);
                    
                    json.put("firstname", rs.getString("firstname"));
                    json.put("lastname", rs.getString("lastname"));
                    json.put("displayname", rs.getString("displayname"));
                    json.put("attendeeid", rs.getInt("attendeeid"));
                    json.put("sessionid", rs.getInt("sessionid"));
                    json.put("description", rs.getString("description"));
                }
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
    
}
