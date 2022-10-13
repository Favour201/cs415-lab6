package edu.jsu.mcis.lab6.dao;

import java.sql.*;
import org.json.simple.*;

public class TrainingSessionDAO {
    
    private final DAOFactory daoFactory;
    
    private final String QUERY_SELECT_BY_ID = "";
    
    TrainingSessionDAO (DAOFactory dao) {
        this.daoFactory = dao;
    }
    
    public String findS(int id, String description) {
        JSONObject json = new JSONObject();
        json.put("success", false);
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conn.prepareStatement("");
            ps.setInt(1, id);
            ps.setString(2, description);
        }
        catch(Exception e){e.printStackTrace();}
        return "";   
    }
    
}
