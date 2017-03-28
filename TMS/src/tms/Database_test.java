/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMS;


import java.sql.*;


/**
 *
 * @author Kristian
 */
public class Database_test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database_test db = new Database_test();
    }
    
    
    public Database_test(){
        
        //System.out.println(getData());
        getData();
    }
    
    public void getData(){
        
        try{
            Connection connection = getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM Bruker");
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                
                System.out.println(rs.getString(2));
                //String test = null;
                //test = rs.getString(1);
                
            } 
            
        }catch(Exception e){
            
        }
        
        
        
        
        //return null;
    }
    
    public Connection getConnection(){
        Connection connection = null;
        try{
        String connectionURL = "jdbc:sqlserver://158.38.101.69;" +  
        		   "databaseName=SeriousCall;user=admin;password=admin;";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection = DriverManager.getConnection(connectionURL);
        
        }
        catch(Exception e){
        }
        
        return connection;
    }
    
}
