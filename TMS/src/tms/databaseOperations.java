package tms;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

public class databaseOperations implements Closeable {
    
    
    private final String connString = "jdbc:sqlserver://158.38.101.103;databaseName=Konsulent1;username=Admin;password=Admin";
        //String connString = "jdbc:sqlserver://158.38.101.103;databaseName=Konsulent1;user=hallvbjo;password=hallvbjo;";
    //private final String connString = "jdbc:sqlserver://hallvbjo-Konsulent1.uials.no;databaseName=Konsulent1;username=hallvbjo;password=hallvbjo";
    //String connString = "jdbc:sqlserver://158.38.101.69;databaseName=SeriousCall;user=admin;password=admin;";
    private Connection connection;
    private PreparedStatement sampleStatement;
    
    public databaseOperations(){
        //connect();
    }
    
    public boolean connect()
    {
        boolean success;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connString);
            //createStatements();
            success = true;
        } catch (SQLException SQLEx) {
            success = false;
            SQLEx.printStackTrace();
        } catch (ClassNotFoundException CNFE){
            success = false;
            CNFE.printStackTrace();
        }
        return success;
    }

    @Override
    public void close() throws IOException
    {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
    
    /**
     * All SQL-statemants that should be sent to the database go in here.
     * @throws SQLException 
     */
    private void createStatements() throws SQLException
    {
        sampleStatement = connection.prepareStatement("SELECT * FROM UserLogin");
        // TODO
    }
    
    /**
     * Gets the ResultSet object returned after executing a PreparedStatement object. 
     * @param preparedStatement 
     * @return 
     */
    public ResultSet getResultSet(String preparedStatement){
        ResultSet results = null;
        try{
            PreparedStatement statement = connection.prepareStatement(preparedStatement);
            results = statement.executeQuery();
        } catch (SQLException SQLEx) {
            System.out.println(SQLEx.getMessage());
            SQLEx.printStackTrace();
        }
        return results;
    }
    
    /*
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
*/

}
