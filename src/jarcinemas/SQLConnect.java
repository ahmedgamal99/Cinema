/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarcinemas;

import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author asifrasheed
 */
public class SQLConnect {
    final static String DBURL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    final static String DBUSER = "b00073877";
    final static String DBPASS = "b00073877";

    static Connection con;
    static Statement statement;
    static PreparedStatement prepStatement;
    
    public SQLConnect() throws ClassNotFoundException, SQLException{
        // Load Oracle JDBC Driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // Connect to Oracle Database
        con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        // make the result set scrolable forward/backward updatable
        statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        // populate valid mgr numbers 
    }
    
    public ResultSet getResultSet(String query) throws SQLException{
       return statement.executeQuery(query);
    }
    
    public boolean executeQuery(String query) throws SQLException{
       PreparedStatement pp = con.prepareStatement(query);
       if(pp.executeUpdate()>0) return true;
       return false;
    }
}
