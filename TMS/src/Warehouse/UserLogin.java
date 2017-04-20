package Warehouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Alexander Eilert Berg
 */
public class UserLogin
{
    //Username,Password
    private HashMap<String,String> loginData;
    private String password = null;
    private String username = null;
    
    public UserLogin(String username, String password)
    {
        this.password = password;
        this.username = username;
    }
    

    
 public boolean checkPasswordAndUsername()
    {
        

        try
        {
            Connection connection = getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM dbo.UserLogin");
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while (i <= rs.getFetchSize())
            {
                rs.next();
                if (this.password.equals(rs.getString("UserPassword")) && this.username.equals(rs.getString("Username")))
                {
                    System.out.println("ACCESS GRANTED");
                    connection.close();
                    return true;
                }
                i++;
            }
            System.out.println("ACCESS DENIED");
            connection.close();
            return false;
        } catch (Exception e)
        {
            return false;
        }
    }
    


    
     public Connection getConnection()
    {
        Connection connection = null;
        try
        {
            String connectionURL = "jdbc:sqlserver://158.38.101.103;"
                    + "databaseName=Konsulent1;user=admin123;password=admin123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionURL);
            if(connection.isValid(0)) System.out.println("Connection estabished");

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return connection;
    }
    
}
