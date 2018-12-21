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
import java.util.ArrayList;
import java.util.List;
import pegasus.DBConnection.MysqlDbConnection;
import pegasus.Model.Summery_line;

/**
 *
 * @author Chamil123
 */
public class Summery_lineController {

    public static int addSummery_line(Summery_line summeryline) throws SQLException, ClassNotFoundException {
        String quary = "INSERT into summery_line (summery_line_chitNumber,summery_line_investment,summery_line_payment,summery_line_date,center_id) Values(?,?,?,?,?)";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, summeryline.getSummery_line_chitNumber());
        stm.setObject(2, summeryline.getSummery_line_investment());
        stm.setObject(3, summeryline.getSummery_line_payment());
        stm.setObject(4, summeryline.getSummery_line_date());
        stm.setObject(5, summeryline.getCenter_id());

//        Runtime.getRuntime().runFinalization();
//        Runtime.getRuntime().gc();
//        System.gc();
        return stm.executeUpdate();

    }

    public static List<Summery_line> getSummeryLinesByDateAndId(String stringdate1, String id) throws SQLException, ClassNotFoundException {
        String quary = "Select * From summery_line where summery_line_date='" + stringdate1 + "' AND center_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();

        List<Summery_line> list = new ArrayList();
        int count = 0;
        while (rst.next()) {

            list.add(new Summery_line(rst.getInt("summery_line_id"), rst.getString("summery_line_chitNumber"), rst.getDouble("summery_line_investment"), rst.getDouble("summery_line_payment"), rst.getString("summery_line_date"), rst.getInt("center_id")));
            count++;
        }

//        Runtime.getRuntime().runFinalization();
//        Runtime.getRuntime().gc();
//        System.gc()
        rst = null;;
        return list;

    }

    public static boolean deleteSummery_line(String date, int id) throws SQLException, ClassNotFoundException {

        String quary = "delete  from summery_line where summery_line_date='" + date + "' AND center_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        int res = stm.executeUpdate();

//        Runtime.getRuntime().runFinalization();
//        Runtime.getRuntime().gc();
//        System.gc();
        if (res > 0) {
            return true;
        } else {
            return false;
        }

    }

    public static List<String> searchSumBydate() throws SQLException, ClassNotFoundException {

        int valueInner = 0;
        String sql = "SELECT year(summery_line_date) as y,month(summery_line_date) as s ,day(summery_line_date) as m, COUNT(summery_line_investment) as p  FROM summery_line  GROUP BY summery_line_date ORDER BY summery_line_date DESC LIMIT 9";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        List<String> list = new ArrayList<String>();

        while (rst.next()) {
            valueInner = rst.getInt("p");
            list.add(rst.getInt("p") + ":" + rst.getInt("y") + "-" + rst.getInt("s") + "-" + rst.getInt("m"));

        }

        return list;
    }

    public static int searchTotalChitBydate(String date) throws SQLException, ClassNotFoundException {
        int valueInner = 0;
        String sql = "SELECT  COUNT(summery_line_investment) as p  FROM summery_line WHERE summery_line_date='" + date + "'  ORDER BY summery_line_date ";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            valueInner = rst.getInt("p");
        }

//        Runtime.getRuntime().runFinalization();
//        Runtime.getRuntime().gc();
//        System.gc();
        rst = null;
        return valueInner;
    }

    public static boolean deleteAllSummery_line() throws SQLException, ClassNotFoundException {

        String quary = "delete  from summery_line";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        int res = stm.executeUpdate();

        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int UpdateAllSummeryLineDate(String stringdate1, String stringdate2) throws SQLException, ClassNotFoundException {
        String quary = "Update summery_line set summery_line_date=? where summery_line_date='" + stringdate1 + "'";

        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stms = conn.prepareStatement(quary);
        stms.setObject(1, stringdate2);

        return stms.executeUpdate();
    }

}
