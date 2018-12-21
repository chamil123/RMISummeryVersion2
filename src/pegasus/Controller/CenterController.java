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
import pegasus.Model.Center;

/**
 *
 * @author Chamil123
 */
public class CenterController {

    public static int addCenter(Center center) throws SQLException, ClassNotFoundException {

        String quary = "Insert into center (center_name,center_com,center_paperCash,center_loan,center_noCom,center_lessCom,center_exp,center_accountType,center_status,account_id) Values(?,?,?,?,?,?,?,?,?,?)";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, center.getCenter_name().toUpperCase());
        stm.setObject(2, center.getCenter_com());
        stm.setObject(3, center.getCenter_paperCash());
        stm.setObject(4, center.getCenter_loan());
        stm.setObject(5, center.getCenter_noCom());
        stm.setObject(6, center.getCenter_lessCom());
        stm.setObject(7, center.getCenter_exp());
        stm.setObject(8, center.getCenter_accountType());
        stm.setObject(9, center.getCenter_status());
        stm.setObject(10, center.getAccount_id());

        return stm.executeUpdate();

    }

    public static List<Center> searchCenterByName(String name) throws ClassNotFoundException, SQLException {
        String quary = "Select * From center where center_name LIKE'" + name + "%'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();

        List<Center> list = new ArrayList();
        while (rst.next()) {
            list.add(new Center(rst.getInt("center_id"), rst.getString("center_name"), rst.getInt("center_com"), rst.getDouble("center_paperCash"), rst.getDouble("center_loan"), rst.getInt("center_noCom"), rst.getInt("center_lessCom"), rst.getDouble("center_exp"), rst.getString("center_accountType"), rst.getString("center_status"), rst.getInt("account_id")));
        }

        rst = null;
        return list;

    }

    public static Center searcCenterByName(String name) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Center searchCenterById(String id) throws ClassNotFoundException, SQLException {
        String quary = "SELECT*FROM center WHERE center_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();
        Center center = null;
        if (rst.next()) {
            center = new Center(rst.getInt("center_id"), rst.getString("center_name"), rst.getInt("center_com"), rst.getDouble("center_paperCash"), rst.getDouble("center_loan"), rst.getInt("center_noCom"), rst.getInt("center_lessCom"), rst.getDouble("center_exp"), rst.getString("center_accountType"), rst.getString("center_status"), rst.getInt("account_id"));
        }

        rst = null;
        return center;

    }

    public static int searchTotalCenters(String format) throws ClassNotFoundException, SQLException {
        String quary = "select COUNT(center_com) AS count from center";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet res = stm.executeQuery();
        int count = 0;
        if (res.next()) {
            count = res.getInt("count");
        }

        res = null;
        return count;
    }

    public static List<Center> remainingCentersByDate(String date) throws ClassNotFoundException, SQLException {
        String quary = "Select * From center LEFT  JOIN summery ON summery.center_id=center.center_id WHERE  summery.summery_date='" + date + "' ";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();
        List<Center> list = new ArrayList();
        while (rst.next()) {
            list.add(new Center(rst.getInt("center_id"), rst.getString("center_name"), rst.getInt("center_com"), rst.getDouble("center_paperCash"), rst.getDouble("center_loan"), rst.getInt("center_noCom"), rst.getInt("center_lessCom"), rst.getDouble("center_exp"), rst.getString("center_accountType"), rst.getString("center_status"), rst.getInt("account_id")));
        }

        rst = null;
        return list;
    }

    public static List<Center> searchAllCenters() throws ClassNotFoundException, SQLException {
        String quary = "Select * From center ORDER BY account_id";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();

        List<Center> list = new ArrayList();
        while (rst.next()) {
            list.add(new Center(rst.getInt("center_id"), rst.getString("center_name"), rst.getInt("center_com"), rst.getDouble("center_paperCash"), rst.getDouble("center_loan"), rst.getInt("center_noCom"), rst.getInt("center_lessCom"), rst.getDouble("center_exp"), rst.getString("center_accountType"), rst.getString("center_status"), rst.getInt("account_id")));
        }

        rst = null;
        return list;
    }

    public static List<Center> searchAllCenterByName(String id) throws ClassNotFoundException, SQLException {
        String quary = "Select * From center where account_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();

        List<Center> list = new ArrayList();
        while (rst.next()) {

            list.add(new Center(rst.getInt("center_id"), rst.getString("center_name"), rst.getInt("center_com"), rst.getDouble("center_paperCash"), rst.getDouble("center_loan"), rst.getInt("center_noCom"), rst.getInt("center_lessCom"), rst.getDouble("center_exp"), rst.getString("center_accountType"), rst.getString("center_status"), rst.getInt("account_id")));
        }

        rst = null;
        return list;
    }

    public static int UpdateCenter(Center center) throws ClassNotFoundException, SQLException {

        String quary = "UPDATE center set center_name=?,center_com=?,center_paperCash=?,center_loan=?,center_noCom=?,center_lessCom=?,center_exp=?,center_accountType=?,center_status=?,account_id=? WHERE center_id='" + center.getCenter_id() + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, center.getCenter_name());
        stm.setObject(2, center.getCenter_com());
        stm.setObject(3, center.getCenter_paperCash());
        stm.setObject(4, center.getCenter_loan());
        stm.setObject(5, center.getCenter_noCom());
        stm.setObject(6, center.getCenter_lessCom());
        stm.setObject(7, center.getCenter_exp());
        stm.setObject(8, center.getCenter_accountType());
        stm.setObject(9, center.getCenter_status());
        stm.setObject(10, center.getAccount_id());

        return stm.executeUpdate();
    }

    public static int deleteCenter(String id) throws ClassNotFoundException, SQLException {
        String quary = "delete  from center where center_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        int res = stm.executeUpdate();

        if (res > 0) {
            return 1;
        } else {
            return 0;
        }

    }

    public static int getMaxId() throws ClassNotFoundException, SQLException {
        String quary = "SELECT MAX(center_id)FROM center";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet res = stm.executeQuery();
        int max = 1;
        while (res.next()) {
            max = res.getInt("MAX(center_id)");
            max++;
        }
        return max;
    }

    public static String getCenterTypeById(String id) throws ClassNotFoundException, SQLException {
        String quary = "select center_id,center_accountType from center WHERE center_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet res = stm.executeQuery();
        String type = "";
        if (res.next()) {
            type = res.getString("center_accountType");
        }

        res = null;
        return type;
    }

}
