package edu.jsu.mcis.lab6.dao;

import java.sql.*;
import org.json.simple.*;

public class AttendeeDAO {
    
    private final DAOFactory daoFactory;
    private final String QUERY_SELECT_BY_ID = "SELECT * FROM attendee WHERE id = ?";
    
    AttendeeDAO(DAOFactory dao){
        this.daoFactory = dao;
    }
    
    public String findA(int id, String firstname, String lastname, String displayname) {
        JSONObject json = new JSONObject();
        json.put("success", false);
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(QUERY_SELECT_BY_ID);
            ps.setInt(1, id);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setString(4, displayname);
            
            boolean hasresults = ps.execute();
            
            if(hasresults) {
                json.put("success", hasresults);
                
                json.put("id", rs.getInt("id"));
                json.put("firstname", rs.getString("firstname"));
                json.put("lastname", rs.getString("lastname"));
                json.put("displayname", rs.getString("displayname"));
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
}
