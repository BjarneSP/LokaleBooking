package databaseLayer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class DBConnection {
	//Constants used to get access to the database
	
		private static final String  driver = "jdbc:sqlserver://kraka.ucn.dk:1433";
	    private static final String  databaseName = ";databaseName=UCN_dmaa0216_2Sem_2";
	    
	    private static String  userName = "; user=UCN_dmaa0216_2Sem_2";
	    private static String password = ";password=Password1!";
	   
	    private DatabaseMetaData dma;
	    private static Connection con;
	    
	    // an instance of the class is generated
	    private static DBConnection  instance = null;
	    // the constructor is private to ensure that only one object of this class is created
	    public DBConnection()
	    {
	    	String url = driver + databaseName + userName + password;
	        try{
	            //load of driver
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            System.out.println("Driver class loaded ok");
	          
	        }
	        catch(Exception e){
	            System.out.println("Cannot find the driver");
	            System.out.println(e.getMessage());
	        }
	        try{
	            //connection to the database
	            con = DriverManager.getConnection(url);
	            con.setAutoCommit(true);
	            dma = con.getMetaData(); // get meta data
	            System.out.println("Connection to " + dma.getURL());
	            System.out.println("Driver " + dma.getDriverName());
	            System.out.println("Database product name " + dma.getDatabaseProductName());
	        }//end try
	        catch(Exception e){
	            System.out.println("Problems with the connection to the database:");
	            System.out.println(e.getMessage());
	            System.out.println(url);
	        }//end catch
	    }//end  constructor
		   
	  //closeDb: closes the connection to the database
	    public static void closeConnection()
	    {
	       	try{
	            con.close();
	            instance= null;
	            System.out.println("The connection is closed");
	        }
	         catch (Exception e){
	            System.out.println("Error trying to close the database " +  e.getMessage());
	         }
	    }//end closeDB
			
	    //getDBcon: returns the singleton instance of the DB connection
	    public  Connection getDBcon()
	    {
	       return con;
	    }
	    //this method is used to get the instance of the connection
	    public static DBConnection getInstance()
	    {
	        if (instance == null)
	        {
	          instance = new DBConnection();
	        }
	        return instance;
	    }

	
}
