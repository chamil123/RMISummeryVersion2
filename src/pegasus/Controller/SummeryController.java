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
import pegasus.Model.Summery;

/**
 *
 * @author Chamil123
 */
public class SummeryController {

    public static int addSummery(Summery summery) throws SQLException, ClassNotFoundException {

        String quary = "INSERT into summery (summery_date,summery_name,summery_fc,summery_cash,summery_pd,summery_noComAmount,summery_lessComAmount,summery_lcnc,summery_paperCash,summery_compay,summery_expenses,summery_slippaytot,summery_loan,summery_excess,summery_salary,summery_payment,summery_due,center_id) Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, summery.getSummery_date());
        stm.setObject(2, summery.getSummery_name());
        stm.setObject(3, summery.getSummery_fc());
        stm.setObject(4, summery.getSummery_cash());
        stm.setObject(5, summery.getSummery_pd());
        stm.setObject(6, summery.getSummery_noComAmount());
        stm.setObject(7, summery.getSummery_lessComAmount());
        stm.setObject(8, summery.getSummery_lcnc());
        stm.setObject(9, summery.getSummery_paperCash());
        stm.setObject(10, summery.getSummery_compay());
        stm.setObject(11, summery.getSummery_expenses());
        stm.setObject(12, summery.getSummery_slippaytot());
        stm.setObject(13, summery.getSummery_loan());
        stm.setObject(14, summery.getSummery_excess());
        stm.setObject(15, summery.getSummery_salary());
        stm.setObject(16, summery.getSummery_payment());
        stm.setObject(17, summery.getSummery_due());
        stm.setObject(18, summery.getCenter_id());

        return stm.executeUpdate();

    }

    public static int getMaxID() throws SQLException, ClassNotFoundException {
        int max = 0;
        String sql = "SELECT MAX(summery_id)FROM summery";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        max = 1;
        while (rst.next()) {
            max = rst.getInt("MAX(summery_id)");
            max++;
        }

        rst = null;
        return max;
    }

    public static int searchsummeryByDateAndId(String date, String id) throws SQLException, ClassNotFoundException {
        int valueInner = 0;
        String sql = "SELECT summery_date,center_id FROM summery WHERE summery_date='" + date + "' AND center_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            valueInner = 1;
        }

        rst = null;
        return valueInner;
    }

    public static Summery searchsummeryById(String date, String id) throws SQLException, ClassNotFoundException {

        String quary = "SELECT*FROM summery WHERE center_id='" + id + "' AND summery_date='" + date + "' ";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();
        Summery summery = null;
        if (rst.next()) {

            summery = new Summery(rst.getInt("summery_id"), rst.getString("summery_date"), rst.getString("summery_name"), rst.getDouble("summery_fc"), rst.getDouble("summery_cash"), rst.getDouble("summery_pd"), rst.getDouble("summery_noComAmount"), rst.getDouble("summery_lessComAmount"), rst.getDouble("summery_lcnc"), rst.getDouble("summery_paperCash"), rst.getDouble("summery_compay"), rst.getDouble("summery_expenses"), rst.getDouble("summery_slippaytot"), rst.getDouble("summery_loan"), rst.getDouble("summery_excess"), rst.getDouble("summery_salary"), rst.getDouble("summery_payment"), rst.getDouble("summery_due"), rst.getInt("center_id"));
        }

        rst = null;
        return summery;

    }

    public static int updatesummery(Summery summery) throws SQLException, ClassNotFoundException {
        String quary = "Update summery set summery_date=?,summery_name=?,summery_fc=?,summery_cash=?,summery_pd=?,summery_noComAmount=?,summery_lessComAmount=?,summery_lcnc=?,summery_paperCash=?,summery_compay=?,summery_expenses=?,summery_slippaytot=?,summery_loan=?,summery_excess=?,summery_salary=?,summery_payment=?,summery_due=?,center_id=? where summery_id='" + summery.getSummery_id() + "'";

        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stms = conn.prepareStatement(quary);
        stms.setObject(1, summery.getSummery_date());
        stms.setObject(2, summery.getSummery_name());
        stms.setObject(3, summery.getSummery_fc());
        stms.setObject(4, summery.getSummery_cash());
        stms.setObject(5, summery.getSummery_pd());
        stms.setObject(6, summery.getSummery_noComAmount());
        stms.setObject(7, summery.getSummery_lessComAmount());
        stms.setObject(8, summery.getSummery_lcnc());
        stms.setObject(9, summery.getSummery_paperCash());
        stms.setObject(10, summery.getSummery_compay());
        stms.setObject(11, summery.getSummery_expenses());
        stms.setObject(12, summery.getSummery_slippaytot());
        stms.setObject(13, summery.getSummery_loan());
        stms.setObject(14, summery.getSummery_excess());
        stms.setObject(15, summery.getSummery_salary());

        stms.setObject(16, summery.getSummery_payment());
        stms.setObject(17, summery.getSummery_due());

        stms.setObject(18, summery.getCenter_id());

        return stms.executeUpdate();
    }

    public static int completedSummery(String date) throws SQLException, ClassNotFoundException {
        int valueInner = 0;
        String sql = "SELECT  COUNT(summery_fc) as count  FROM summery WHERE summery_date='" + date + "'  ORDER BY summery_date ";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            valueInner = rst.getInt("count");
        }

        rst = null;
        return valueInner;
    }

    public static List<Summery> searchsummeryByDateAndAccoundID(String date, int accountId) throws SQLException, ClassNotFoundException {
        String quary = "Select * From summery INNER JOIN center ON center.center_id=summery.center_id INNER JOIN account ON account.account_id=center.account_id where account.account_id='" + accountId + "' AND summery.summery_date='" + date + "' AND center.center_accountType='General' ";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();

        List<Summery> list = new ArrayList();
        while (rst.next()) {
            list.add(new Summery(rst.getInt("summery_id"), rst.getString("summery_date"), rst.getString("summery_name"), rst.getDouble("summery_fc"), rst.getDouble("summery_cash"), rst.getDouble("summery_pd"), rst.getDouble("summery_noComAmount"), rst.getDouble("summery_lessComAmount"), rst.getDouble("summery_lcnc"), rst.getDouble("summery_paperCash"), rst.getDouble("summery_compay"), rst.getDouble("summery_expenses"), rst.getDouble("summery_slippaytot"), rst.getDouble("summery_loan"), rst.getDouble("summery_excess"), rst.getDouble("summery_salary"), rst.getDouble("summery_payment"), rst.getDouble("summery_due"), rst.getInt("center_id")));
        }

        rst = null;
        return list;
    }

    public static double searchPrivousDue(String date, String id) throws SQLException, ClassNotFoundException {
        double valueInner = 0;
        String sql = "SELECT summery_due FROM summery WHERE summery_date='" + date + "' AND center_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            valueInner = rst.getDouble("summery_due");
        }

        rst = null;
        return valueInner;
    }

    public static boolean deleteAllSummery() throws SQLException, ClassNotFoundException {
        String quary = "delete  from summery";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        int res = stm.executeUpdate();

        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteAllSummeryByDateAndId(String stringdate1, String id) throws SQLException, ClassNotFoundException {
        String quary = "delete  from summery WHERE summery_date='" + stringdate1 + "' AND center_id='" + id + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        int res = stm.executeUpdate();

        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int searchsummeryByDate(String date) throws SQLException, ClassNotFoundException {
        int valueInner = 0;
        String sql = "SELECT summery_date FROM summery WHERE summery_date='" + date + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            valueInner = 1;
        }

        rst = null;
        return valueInner;
    }

    public static int UpdateAllSummeryDate(String stringdate1, String stringdate2) throws SQLException, ClassNotFoundException {
        String quary = "Update summery set summery_date=? where summery_date='" + stringdate1 + "'";

        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stms = conn.prepareStatement(quary);
        stms.setObject(1, stringdate2);

        return stms.executeUpdate();
    }

    public static List<String> searchSumFullCollectionBydate() throws SQLException, ClassNotFoundException {
        //double valueInner = 0;
        String sql = "SELECT year(summery_date) as y,month(summery_date) as s ,day(summery_date) as m, SUM(summery_fc) as p  FROM summery  GROUP BY summery_date ORDER BY summery_date DESC LIMIT 7";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        List<String> list = new ArrayList<String>();

        while (rst.next()) {
            //   valueInner = rst.getDouble("p");
            list.add(rst.getDouble("p") + ":" + rst.getInt("y") + "-" + rst.getInt("s") + "-" + rst.getInt("m"));

        }

        return list;
    }

    public static List<String> searchSumChitPaymentBydate() throws SQLException, ClassNotFoundException {
        String sql = "SELECT year(summery_date) as y,month(summery_date) as s ,day(summery_date) as m, SUM(summery_payment) as p  FROM summery  GROUP BY summery_date ORDER BY summery_date DESC LIMIT 7";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        List<String> list = new ArrayList<String>();

        while (rst.next()) {
            //   valueInner = rst.getDouble("p");
            list.add(rst.getDouble("p") + ":" + rst.getInt("y") + "-" + rst.getInt("s") + "-" + rst.getInt("m"));

        }
        return list;
    }

    public static List<String> searchSumExpensesBydate() throws SQLException, ClassNotFoundException {
        String sql = "SELECT year(summery_date) as y,month(summery_date) as s ,day(summery_date) as m, SUM(summery_excess) as p  FROM summery  GROUP BY summery_date ORDER BY summery_date DESC LIMIT 7";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        List<String> list = new ArrayList<String>();

        while (rst.next()) {
            //   valueInner = rst.getDouble("p");
            list.add(rst.getDouble("p") + ":" + rst.getInt("y") + "-" + rst.getInt("s") + "-" + rst.getInt("m"));

        }
        return list;
    }

    public static double searchSumFullCollectionByCurrentdate(String date) throws SQLException, ClassNotFoundException {
        double valueInner = 0;
        String sql = "SELECT summery_date,SUM(summery_fc) as fc FROM summery WHERE summery_date='" + date + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            valueInner = rst.getDouble("fc");

        }

        rst = null;
        return valueInner;
    }

    public static double searchSumChitPayementsByCurrentdate(String currnetDate) throws SQLException, ClassNotFoundException {
        double payments = 0;
        String sql = "SELECT summery_date,SUM(summery_slippaytot) as pay FROM summery WHERE summery_date='" + currnetDate + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            payments = rst.getDouble("pay");

        }

        rst = null;
        return payments;
    }

    public static double searchSumExpensesByCurrentdate(String currnetDate) throws SQLException, ClassNotFoundException {
        double expenses = 0;
        String sql = "SELECT summery_date,SUM(summery_excess) as exp FROM summery WHERE summery_date='" + currnetDate + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            expenses = rst.getDouble("exp");

        }

        rst = null;
        return expenses;
    }

    public static double searchIncomeByCurrentdate(String currnetDate) throws SQLException, ClassNotFoundException {
        double expenses = 0;
        double cash = 0;
        double payment = 0;
        double income = 0;
        String sql = "SELECT summery_date,SUM(summery_excess) as exp,SUM(summery_slippaytot) as pay,SUM(summery_cash) as cash FROM summery WHERE summery_date='" + currnetDate + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            expenses = rst.getDouble("exp");
            cash = rst.getDouble("cash");
            payment = rst.getDouble("pay");

        }
        income = cash - (payment + expenses);
        rst = null;
        return income;
    }

    public static List<Summery> searchAllSummeryByCurrentDate(String date) throws SQLException, ClassNotFoundException {

        String quary = "Select * From summery WHERE summery_date='" + date + "' ";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();

        List<Summery> list = new ArrayList();
        while (rst.next()) {
            list.add(new Summery(rst.getInt("summery_id"), rst.getString("summery_date"), rst.getString("summery_name"), rst.getDouble("summery_fc"), rst.getDouble("summery_cash"), rst.getDouble("summery_pd"), rst.getDouble("summery_noComAmount"), rst.getDouble("summery_lessComAmount"), rst.getDouble("summery_lcnc"), rst.getDouble("summery_paperCash"), rst.getDouble("summery_compay"), rst.getDouble("summery_expenses"), rst.getDouble("summery_slippaytot"), rst.getDouble("summery_loan"), rst.getDouble("summery_excess"), rst.getDouble("summery_salary"), rst.getDouble("summery_payment"), rst.getDouble("summery_due"), rst.getInt("center_id")));
        }

        rst = null;
        return list;
    }

    public static List<Summery> searchAllSummeryBetweenDates(String fromDate, String toDate, String name) throws SQLException, ClassNotFoundException {
        String quary = "Select * From summery WHERE summery_date BETWEEN '" + fromDate + "' AND '" + toDate + "' AND summery_name='" + name + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();

        List<Summery> list = new ArrayList();
        while (rst.next()) {
            list.add(new Summery(rst.getInt("summery_id"), rst.getString("summery_date"), rst.getString("summery_name"), rst.getDouble("summery_fc"), rst.getDouble("summery_cash"), rst.getDouble("summery_pd"), rst.getDouble("summery_noComAmount"), rst.getDouble("summery_lessComAmount"), rst.getDouble("summery_lcnc"), rst.getDouble("summery_paperCash"), rst.getDouble("summery_compay"), rst.getDouble("summery_expenses"), rst.getDouble("summery_slippaytot"), rst.getDouble("summery_loan"), rst.getDouble("summery_excess"), rst.getDouble("summery_salary"), rst.getDouble("summery_payment"), rst.getDouble("summery_due"), rst.getInt("center_id")));
        }

        rst = null;
        return list;
    }

    public static List<Summery> searchAllSummeryBetweenDates(String fromDate, String toDate) throws SQLException, ClassNotFoundException {
        String quary = "Select * From summery WHERE summery_date BETWEEN '" + fromDate + "' AND '" + toDate + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();

        List<Summery> list = new ArrayList();
        while (rst.next()) {
            list.add(new Summery(rst.getInt("summery_id"), rst.getString("summery_date"), rst.getString("summery_name"), rst.getDouble("summery_fc"), rst.getDouble("summery_cash"), rst.getDouble("summery_pd"), rst.getDouble("summery_noComAmount"), rst.getDouble("summery_lessComAmount"), rst.getDouble("summery_lcnc"), rst.getDouble("summery_paperCash"), rst.getDouble("summery_compay"), rst.getDouble("summery_expenses"), rst.getDouble("summery_slippaytot"), rst.getDouble("summery_loan"), rst.getDouble("summery_excess"), rst.getDouble("summery_salary"), rst.getDouble("summery_payment"), rst.getDouble("summery_due"), rst.getInt("center_id")));
        }

        rst = null;
        return list;
    }

    public static Summery searchSummeryBetweenDatesForPandL(String fromDate, String toDate) throws SQLException, ClassNotFoundException {
        String quary = "Select summery_id,summery_date,summery_name,SUM(summery_fc) AS fc,SUM(summery_cash) AS cash,SUM(summery_pd) AS pd,summery_noComAmount,summery_lessComAmount,SUM(summery_lcnc) AS lcnc,SUM(summery_paperCash) AS pc,SUM(summery_compay) AS cpay,SUM(summery_expenses) AS exp,SUM(summery_slippaytot) AS paytot,SUM(summery_loan) AS loan,SUM(summery_excess) AS exe,SUM(summery_salary) AS sal,SUM(summery_payment) AS pay,SUM(summery_due) AS due,center_id From summery WHERE summery_date BETWEEN '" + fromDate + "' AND '" + toDate + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet rst = stm.executeQuery();
        Summery summery = null;
        //  List<Summery> list = new ArrayList();
        if (rst.next()) {
            summery = new Summery(rst.getInt("summery_id"), rst.getString("summery_date"), rst.getString("summery_name"), rst.getDouble("fc"), rst.getDouble("cash"), rst.getDouble("pd"), rst.getDouble("summery_noComAmount"), rst.getDouble("summery_lessComAmount"), rst.getDouble("lcnc"), rst.getDouble("pc"), rst.getDouble("cpay"), rst.getDouble("exp"), rst.getDouble("paytot"), rst.getDouble("loan"), rst.getDouble("exe"), rst.getDouble("sal"), rst.getDouble("pay"), rst.getDouble("due"), rst.getInt("center_id"));
        }

        rst = null;
        return summery;
    }
      public static List<Center> getRemainingPackets(String toDate) throws SQLException, ClassNotFoundException {
        String quary = "Select * From center LEFT JOIN summery ON center.center_id=summery.center_id WHERE summery.summery_date='"+toDate+"'";
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
    
}
