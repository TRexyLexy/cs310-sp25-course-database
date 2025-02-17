package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;



public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {

                // INSERT YOUR CODE HERE
                
                //Getting Metadata from ResultSet
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                
                //Iterate through each of the ResultSet
                while(rs.next()){
                    //using a json object to store the column data
                    JsonObject record = new JsonObject();
                    
                    //Iterate through the columns and add the data to jsonobject
                    for(int i = 1; i <= columnCount; i++){
                        String columnName = metaData.getColumnName(i);
                        Object columnValue = rs.getObject(i);
                        
                        //Adding the column data to the jsonobject
                        record.put(columnName, String.valueOf(columnValue));
                    }
                    //add the jsonObject
                    records.add(record);
                }
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
