/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pegasus.DBConnection.MysqlDbConnection;
import pegasus.Model.Backup;

/**
 *
 * @author Chamil123
 */
public class BackupController {

    public static int InsertBackup(Backup backup) throws SQLException, ClassNotFoundException {
        String quary = "Insert into backup (backup_date,backup_string) Values(?,?)";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, backup.getBackup_date());
        stm.setObject(2, backup.getBackup_string());

        return stm.executeUpdate();
    }

    public static int serchBackupByDate(String string) throws SQLException, ClassNotFoundException {
        int valueInner = 0;
        String quary = "SELECT backup_date from backup WHERE backup_date='" + string + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            valueInner = 1;
        }

        rst = null;
        return valueInner;
    }

}
