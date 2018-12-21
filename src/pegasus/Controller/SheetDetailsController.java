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
import pegasus.Model.Sheet_Details;

/**
 *
 * @author Chamil123
 */
public class SheetDetailsController {

    public static int addSheetDetails(Sheet_Details sheetdetails) throws SQLException, ClassNotFoundException {
        String quary = "Insert into sheetdetails (sheetdetails_fullCollection,sheetdetails_cash,sheetdetails_excess,sheetdetails_pd,senter_id,sheetdetails_date) Values(?,?,?,?,?,?)";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, sheetdetails.getSheetdetails_fullCollection());
        stm.setObject(2, sheetdetails.getSheetdetails_cash());
        stm.setObject(3, sheetdetails.getSheetdetails_excess());
        stm.setObject(4, sheetdetails.getSheetdetails_pd());
        stm.setObject(5, sheetdetails.getSenter_id());
        stm.setObject(6, sheetdetails.getSheetdetails_date());

//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        return stm.executeUpdate();
    }

    public static int updateSheetDetails(Sheet_Details sheetdetails) throws SQLException, ClassNotFoundException {

        String quary = "UPDATE sheetdetails set sheetdetails_fullCollection=?,sheetdetails_cash=?,sheetdetails_excess=?,sheetdetails_pd=?,sheetdetails_date=? WHERE senter_id='" + sheetdetails.getSenter_id() + "' AND sheetdetails_date='" + sheetdetails.getSheetdetails_date() + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, sheetdetails.getSheetdetails_fullCollection());
        stm.setObject(2, sheetdetails.getSheetdetails_cash());
        stm.setObject(3, sheetdetails.getSheetdetails_excess());
        stm.setObject(4, sheetdetails.getSheetdetails_pd());

        stm.setObject(5, sheetdetails.getSheetdetails_date());

//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        return stm.executeUpdate();
    }

    public static Sheet_Details searchByCenterId(String id, String date) throws SQLException, ClassNotFoundException {
        String quary = "Select * From sheetdetails WHERE senter_id='" + id + "' AND sheetdetails_date='" + date + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();
        Sheet_Details sheetdetails = null;
        if (rst.next()) {

            sheetdetails = new Sheet_Details(rst.getInt("sheetdetails_id"), rst.getDouble("sheetdetails_fullCollection"), rst.getDouble("sheetdetails_cash"), rst.getDouble("sheetdetails_excess"), rst.getDouble("sheetdetails_pd"), rst.getInt("senter_id"), rst.getString("sheetdetails_date"));
        }

//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        rst = null;
        return sheetdetails;
    }

    public static int searchDetailsByDateAndId(String stringdate1, String id) throws SQLException, ClassNotFoundException {
        int valueInner = 0;
        String sql = "SELECT*FROM sheetdetails WHERE senter_id='" + id + "' AND sheetdetails_date='" + stringdate1 + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            valueInner = 1;
        }

//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        rst = null;
        return valueInner;
    }

    public static int deleteSheetByDateAndId(String date, String id) throws SQLException, ClassNotFoundException {
        String quary = "delete  from sheetdetails where senter_id='" + id + "' AND sheetdetails_date='" + date + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        int res = stm.executeUpdate();

//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        if (res > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static boolean deleteAllSheetDetails() throws SQLException, ClassNotFoundException {
        String quary = "delete  from sheetdetails";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        int res = stm.executeUpdate();

        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

}
