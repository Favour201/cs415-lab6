package edu.jsu.mcis.lab6.dao;

import java.sql.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class TrainingSessionDAO {
    
    private final DAOFactory daoFactory;
    
    private final String QUERY_SELECT_WITHOUT_ID = "SELECT * FROM `session`";
    private final String QUERY_SELECT_BY_ID = "SELECT attendeeid FROM registration WHERE sessionid = ?";
    
    TrainingSessionDAO (DAOFactory dao) {
        this.daoFactory = dao;
    }
    
    public String findSession() {
        JSONObject json = new JSONObject();
        JSONArray records = new JSONArray();
        
        json.put("success", false);
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(QUERY_SELECT_WITHOUT_ID);
            
            boolean hasresults = ps.execute();
            
            if (hasresults) {
                rs = ps.getResultSet();
                
                while (rs.next()) {
                
                    json.put("success", hasresults);
                    
                    json.put("id", rs.getInt("id"));
                    json.put("description", rs.getString("description"));
                    records.add(json);
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

        return JSONValue.toJSONString(records);
    }
    
    public String findSessionById(int sessionid) {
        JSONArray records = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        json.put("success", false);
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        AttendeeDAO a_dao = daoFactory.getAttendeeDAO();
        
        try {
            ps = conn.prepareStatement(QUERY_SELECT_BY_ID);
            ps.setInt(1, sessionid);
            
            boolean hasresults = ps.execute();
            
            if (hasresults) {
                rs = ps.getResultSet();
                
                if (rs.next()) {
                
                    json.put("success", hasresults);
                    
                    int attendeeID = rs.getInt("attendeeid");
                    String jsonString = a_dao.findAttendee(attendeeID);
                    json = (JSONObject)parser.parse(jsonString);
                    records.add(json);
                    
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

        return JSONValue.toJSONString(records);
    }
    
}
