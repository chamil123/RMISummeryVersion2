/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Win 8
 */
public class MysqlDbConnection {

    private static MysqlDbConnection dbconnection;
    private Connection conn;

    private MysqlDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pegasus", "root", "");

    }

    public static MysqlDbConnection getDBConnection() throws ClassNotFoundException, SQLException {
        if (dbconnection == null) {
            dbconnection = new MysqlDbConnection();
        }
        return dbconnection;

    }

    public Connection getConnection() {
        return conn;
    }
}
