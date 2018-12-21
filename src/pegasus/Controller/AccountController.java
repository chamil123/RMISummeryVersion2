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
import pegasus.Model.Account;

/**
 *
 * @author Chamil123
 */
public class AccountController {

    public static int addAccount(Account account) throws SQLException, ClassNotFoundException {

        String quary = "Insert into account (account_name,account_com,account_paperCash,account_loan,account_noCom,account_lessCom,account_exp) Values(?,?,?,?,?,?,?)";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, account.getAccount_name());
        stm.setObject(2, account.getAccount_com());
        stm.setObject(3, account.getAccount_paperCash());
        stm.setObject(4, account.getAccount_loan());
        stm.setObject(5, account.getAccount_noCom());
        stm.setObject(6, account.getAccount_lessCom());
        stm.setObject(7, account.getAccount_exp());

//        Runtime.getRuntime().runFinalization();
//        Runtime.getRuntime().gc();
//        System.gc();
        return stm.executeUpdate();

    }

    public static List<Account> getAllAccount() throws SQLException, ClassNotFoundException {

        String quary = "Select * From account";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet res = stm.executeQuery();

        List<Account> list = new ArrayList();
        while (res.next()) {
            list.add(new Account(res.getInt("account_id"), res.getString("account_name"), res.getInt("account_com"), res.getDouble("account_paperCash"), res.getDouble("account_loan"), res.getInt("account_noCom"), res.getInt("account_lessCom"), res.getDouble("account_exp")));
        }
//        Runtime.getRuntime().runFinalization();
//        Runtime.getRuntime().gc();
//        System.gc();
        res = null;
        return list;
    }

    public static List<Account> searchAccountByName(String name) throws SQLException, ClassNotFoundException {

        String quary = "Select * From account WHERE account_name='" + name + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet res = stm.executeQuery();

        List<Account> list = new ArrayList();
        while (res.next()) {
            list.add(new Account(res.getInt("account_id"), res.getString("account_name"), res.getInt("account_com"), res.getDouble("account_paperCash"), res.getDouble("account_loan"), res.getInt("account_noCom"), res.getInt("account_lessCom"), res.getDouble("account_exp")));
        }
//        Runtime.getRuntime().runFinalization();
//        Runtime.getRuntime().gc();
//        System.gc();
        res = null;
        return list;

    }

    public static Account searcAccountByName(String name) throws SQLException, ClassNotFoundException {

        String quary = "Select * From account WHERE account_name='" + name + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();
        Account account = null;
        if (rst.next()) {

            account = new Account(rst.getInt("account_id"), rst.getString("account_name"), rst.getInt("account_com"), rst.getDouble("account_paperCash"), rst.getDouble("account_loan"), rst.getInt("account_noCom"), rst.getInt("account_lessCom"), rst.getDouble("account_exp"));
        }
//        Runtime.getRuntime().runFinalization();
//        Runtime.getRuntime().gc();
//        System.gc();
        rst = null;
        return account;

    }

    public static String searchAccountById(String id) throws SQLException, ClassNotFoundException {

        String quary = "Select account_id,account_name From account WHERE account_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();
        String account = null;
        if (rst.next()) {

            account = rst.getString("account_name");
            //  account = new Account(rst.getInt("account_id"), rst.getString("account_name"), rst.getInt("account_com"), rst.getDouble("account_paperCash"), rst.getDouble("account_loan"), rst.getInt("account_noCom"), rst.getInt("account_lessCom"), rst.getDouble("account_exp"));
        }

        rst = null;
        return account;

    }

    public static String searchAccountBynameByCenterId(String id) throws SQLException, ClassNotFoundException {
        String quary = "Select account.account_id,account.account_name,center.center_id,center.account_id  From center INNER JOIN account ON center.account_id=account.account_id WHERE center.center_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();
        String account = null;
        if (rst.next()) {

            account = rst.getString("account_name");
            //  account = new Account(rst.getInt("account_id"), rst.getString("account_name"), rst.getInt("account_com"), rst.getDouble("account_paperCash"), rst.getDouble("account_loan"), rst.getInt("account_noCom"), rst.getInt("account_lessCom"), rst.getDouble("account_exp"));
        }

        rst = null;
        return account;
    }

}
