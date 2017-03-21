package tms;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

public class databaseOperations implements Closeable {
    
    private final String connString = "jdbc:sqlserver://hallvbjo-Konsulent1.uials.no;databaseName=Konsulent1;username=halvbjo;password=hallvbjo";
    private Connection connection;
    
    public databaseOperations(){
        
    }
    
    public boolean connect()
    {
        boolean success;
        try {
            connection = DriverManager.getConnection(connString);
            createStatements();
            success = true;
        } catch (SQLException SQLEx) {
            success = false;
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
        // TODO
    }

}
