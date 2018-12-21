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
import pegasus.Model.General_Summery;

/**
 *
 * @author Chamil123
 */
public class General_summeryController {

    public static int addGeneral(General_Summery generalSummery) throws SQLException, ClassNotFoundException {
        String quary = "Insert into generalsummery (generalSummery_date,generalSummery_name,generalSummery_fc,generalSummery_cash,generalSummery_pd,generalSummery_noComAmmount,generalSummery_lessComAmmount,generalSummery_lcnc,generalSummery_paperCash,generalSummery_compay,generalSummery_expenses,generalSummery_paymentTot,generalSummery_loan,generalSummery_excess,generalSummery_payment,account_id) Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, generalSummery.getGeneralSummery_date());
        stm.setObject(2, generalSummery.getGeneralSummery_name());
        stm.setObject(3, generalSummery.getGeneralSummery_fc());
        stm.setObject(4, generalSummery.getGeneralSummery_cash());
        stm.setObject(5, generalSummery.getGeneralSummery_pd());
        stm.setObject(6, generalSummery.getGeneralSummery_noComAmmount());
        stm.setObject(7, generalSummery.getGeneralSummery_lessComAmmount());
        stm.setObject(8, generalSummery.getGeneralSummery_lcnc());
        stm.setObject(9, generalSummery.getGeneralSummery_paperCash());
        stm.setObject(10, generalSummery.getGeneralSummery_compay());
        stm.setObject(11, generalSummery.getGeneralSummery_expenses());
        stm.setObject(12, generalSummery.getGeneralSummery_paymentTot());
        stm.setObject(13, generalSummery.getGeneralSummery_loan());
        stm.setObject(14, generalSummery.getGeneralSummery_excess());
        stm.setObject(15, generalSummery.getGeneralSummery_payment());
        stm.setObject(16, generalSummery.getAccount_id());
//
//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        return stm.executeUpdate();
    }

    public static int getMaxID() throws SQLException, ClassNotFoundException {
        int max = 0;
        String sql = "SELECT MAX(generalSummery_id)FROM generalsummery";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        max = 1;
        while (rst.next()) {
            max = rst.getInt("MAX(generalSummery_id)");
        }

//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        rst = null;
        return max;
    }

    public static int searchsummeryByDateAndId(String date, String id) throws SQLException, ClassNotFoundException {
        int valueInner = 0;
        String sql = "SELECT generalSummery_date,account_id FROM generalsummery WHERE generalSummery_date='" + date + "' AND account_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            valueInner = 1;
        }

//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        return valueInner;
    }

    public static int updateGeneral(General_Summery generalSummery) throws SQLException, ClassNotFoundException {

        String quary = "Update generalsummery set generalSummery_date=?,generalSummery_name=?,generalSummery_fc=?,generalSummery_cash=?,generalSummery_pd=?,generalSummery_noComAmmount=?,generalSummery_lessComAmmount=?,generalSummery_lcnc=?,generalSummery_paperCash=?,generalSummery_compay=?,generalSummery_expenses=?,generalSummery_paymentTot=?,generalSummery_loan=?,generalSummery_excess=?,generalSummery_payment=?,account_id=? where generalSummery_id='" + generalSummery.getGeneralSummery_id() + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, generalSummery.getGeneralSummery_date());
        stm.setObject(2, generalSummery.getGeneralSummery_name());
        stm.setObject(3, generalSummery.getGeneralSummery_fc());
        stm.setObject(4, generalSummery.getGeneralSummery_cash());
        stm.setObject(5, generalSummery.getGeneralSummery_pd());
        stm.setObject(6, generalSummery.getGeneralSummery_noComAmmount());
        stm.setObject(7, generalSummery.getGeneralSummery_lessComAmmount());
        stm.setObject(8, generalSummery.getGeneralSummery_lcnc());
        stm.setObject(9, generalSummery.getGeneralSummery_paperCash());
        stm.setObject(10, generalSummery.getGeneralSummery_compay());
        stm.setObject(11, generalSummery.getGeneralSummery_expenses());
        stm.setObject(12, generalSummery.getGeneralSummery_paymentTot());
        stm.setObject(13, generalSummery.getGeneralSummery_loan());
        stm.setObject(14, generalSummery.getGeneralSummery_excess());
        stm.setObject(15, generalSummery.getGeneralSummery_payment());
        stm.setObject(16, generalSummery.getAccount_id());

//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        return stm.executeUpdate();
    }

    public static General_Summery searchGeneralsummeryById(String stringdate1, Integer account_id) throws SQLException, ClassNotFoundException {
        String quary = "SELECT*FROM generalsummery WHERE account_id='" + account_id + "' AND generalSummery_date='" + stringdate1 + "' ";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();
        General_Summery generalsummery = null;
        if (rst.next()) {

            generalsummery = new General_Summery(rst.getInt("generalSummery_id"), rst.getString("generalSummery_date"), rst.getString("generalSummery_name"), rst.getDouble("generalSummery_fc"), rst.getDouble("generalSummery_cash"), rst.getDouble("generalSummery_pd"), rst.getDouble("generalSummery_noComAmmount"), rst.getDouble("generalSummery_lessComAmmount"), rst.getDouble("generalSummery_lcnc"), rst.getDouble("generalSummery_paperCash"), rst.getDouble("generalSummery_compay"), rst.getDouble("generalSummery_expenses"), rst.getDouble("generalSummery_paymentTot"), rst.getDouble("generalSummery_loan"), rst.getDouble("generalSummery_excess"), rst.getDouble("generalSummery_payment"), rst.getInt("account_id"));
        }

//        Runtime.getRuntime().runFinalization();
//Runtime.getRuntime().gc();
//System.gc();
        rst = null;
        return generalsummery;
    }

    public static boolean deleteAllgemSum() throws SQLException, ClassNotFoundException {
        String quary = "delete  from generalsummery";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        int res = stm.executeUpdate();

        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int UpdateAllGenSummeryDate(String stringdate1, String stringdate2) throws SQLException, ClassNotFoundException {
        String quary = "Update generalsummery set generalSummery_date=? where generalSummery_date='" + stringdate1 + "'";

        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stms = conn.prepareStatement(quary);
        stms.setObject(1, stringdate2);

        return stms.executeUpdate();
    }

}
