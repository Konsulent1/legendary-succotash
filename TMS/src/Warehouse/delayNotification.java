package Warehouse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.dcn.DatabaseChangeEvent;
import oracle.jdbc.dcn.DatabaseChangeListener;
import oracle.jdbc.dcn.DatabaseChangeRegistration;

public class delayNotification {
	  static final String USERNAME= "admin";
	  static final String PASSWORD= "admin";
	  static String URL;
	  
	  public static void main(String[] argv)
	  {
	    if(argv.length < 1)
	    {
	      System.out.println("Error: You need to provide the URL in the first argument.");
	     // System.out.println("  For example: > java -classpath .:ojdbc5.jar DBChangeNotification \"jdbc:oracle:thin:
	//@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=yourhost.yourdomain.com)(PORT=1521))(CONNECT_DATA=
	//(SERVICE_NAME=yourservicename)))\"");
	 
	      System.exit(1);
	    }
	    URL = argv[0];
	    DBChangeNotification demo = new DBChangeNotification();
	    try
	    {
	      demo.run();
	    }
	    catch(SQLException mainSQLException )
	    {
	      mainSQLException.printStackTrace();
	    }
	  }
	 
	  void run() throws SQLException
	  {
	    OracleConnection conn = connect();
	      
	    // first step: create a registration on the server:
	    Properties prop = new Properties();
	    
	    // if connected through the VPN, you need to provide the TCP address of the client.
	    // For example:
	    // prop.setProperty(OracleConnection.NTF_LOCAL_HOST,"14.14.13.12");
	 
	    prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS,"true");
	// 
	//Set the DCN_QUERY_CHANGE_NOTIFICATION option for query registration with finer granularity.
	 prop.setProperty(OracleConnection.DCN_QUERY_CHANGE_NOTIFICATION,"true");
	 
	    DatabaseChangeRegistration dcr = conn.registerDatabaseChangeNotification(prop);
	 
	    try
	    {
	      // add the listenerr:
	      DCNListener list = new DCNListener(this);
	      dcr.addListener(list);
	       
	      // second step: add objects in the registration:
	      Statement stmt = conn.createStatement();
	      // associate the statement with the registration:
	      ((OracleStatement)stmt).setDatabaseChangeRegistration(dcr);
	      ResultSet rs = stmt.executeQuery("SELECT * from DELAY'");
	      while (rs.next())
	      {}
	      String[] tableNames = dcr.getTables();
	      for(int i=0;i<tableNames.length;i++)
	        System.out.println(tableNames[i]+" is part of the registration.");
	      rs.close();
	      stmt.close();
	    }
	    catch(SQLException ex)
	    {
	      // if an exception occurs, we need to close the registration in order
	      // to interrupt the thread otherwise it will be hanging around.
	      if(conn != null)
	        conn.unregisterDatabaseChangeNotification(dcr);
	      throw ex;
	    }
	    finally
	    {
	      try
	      {
	        // Note that we close the connection!
	        conn.close();
	      }
	      catch(Exception innerex){ innerex.printStackTrace(); }
	    }
	    
	    synchronized( this ) 
	    {

	      catch(SQLException ex) { ex.printStackTrace(); }
	 
	      // wait until we get the event
	      try{ this.wait();} catch( InterruptedException ie ) {}
	    }
	  }
	  
	  /**
	   * Creates a connection the database.
	   */
	  OracleConnection connect() throws SQLException
	  {
	    OracleDriver dr = new OracleDriver();
	    Properties prop = new Properties();
	    prop.setProperty("user",DBChangeNotification.USERNAME);
	    prop.setProperty("password",DBChangeNotification.PASSWORD);
	    return (OracleConnection)dr.connect(DBChangeNotification.URL,prop);
	  }
	}
	/**
	 * DCN listener: it prints out the event details in stdout.
	 */
	class DCNListener implements DatabaseChangeListener
	{
	  DBChangeNotification demo;
	  DCNDemoListener(DBChangeNotification dem)
	  {
	    demo = dem;
	  }
	  public void onDatabaseChangeNotification(DatabaseChangeEvent e)
	  {
	    Thread t = Thread.currentThread();
	    System.out.println("DCNListener: got an event ("+this+" running on thread "+t+")");
	    System.out.println(e.toString());
	    synchronized( demo ){ demo.notify();}
	  }
	}
}
