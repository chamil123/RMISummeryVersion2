/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pegasus.DBConnection.MysqlDbConnection;
import pegasus.Model.GeneralSummeryLine;

/**
 *
 * @author Chamil123
 */
public class GeneralSummeryLineController {

    public static int addGeneralSummery_line(GeneralSummeryLine generalsummeryLine) throws SQLException, ClassNotFoundException {
        String quary = "INSERT into generalsummery_line (generalSummery_line_centerName,generalSummery_line_fc,generalSummery_line_payment,generalSummery_line_date,generalSummery_id) Values(?,?,?,?,?)";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, generalsummeryLine.getGeneralSummery_line_centerName());
        stm.setObject(2, generalsummeryLine.getGeneralSummery_line_fc());
        stm.setObject(3, generalsummeryLine.getGeneralSummery_line_payment());
        stm.setObject(4, generalsummeryLine.getGeneralSummery_line_date());
        stm.setObject(5, generalsummeryLine.getGeneralSummery_id());

//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        return stm.executeUpdate();
    }

    public static boolean deleteGeneralSummeryLine(String stringdate1, String id) throws SQLException, ClassNotFoundException {

        String quary = "delete  from generalsummery_line where generalSummery_line_date='" + stringdate1 + "' AND generalSummery_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        int res = stm.executeUpdate();

//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteAllgemSumLine() throws SQLException, ClassNotFoundException {
        String quary = "delete  from generalsummery_line";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        int res = stm.executeUpdate();

        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int UpdateAllGenSummeryLineDate(String stringdate1, String stringdate2) throws SQLException, ClassNotFoundException {
        String quary = "Update generalsummery_line set generalSummery_line_date=? where generalSummery_line_date='" + stringdate1 + "'";

        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stms = conn.prepareStatement(quary);
        stms.setObject(1, stringdate2);

        return stms.executeUpdate();
    }

}
