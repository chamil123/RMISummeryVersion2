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
import pegasus.Model.RemainingPacket;

/**
 *
 * @author Chamil-PC
 */
public class RemainingPacketController {

    public static int serchExcistingByDate(String day) throws SQLException, ClassNotFoundException {
        int valueInner = 0;
        String sql = "SELECT remainigpacket_date FROM remainingpacket WHERE remainigpacket_date='" + day + "'";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            valueInner = 1;
        }

        rst = null;
        return valueInner;
    }

    public static int addPackets(RemainingPacket remainingPacket) throws SQLException, ClassNotFoundException {
        String quary = "Insert into remainingpacket (remainigpacket_centerid,remainigpacket_name,remainigpacket_date,remainigpacket_account) Values(?,?,?,?)";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        stm.setObject(1, remainingPacket.getRemainigpacket_centerid());
        stm.setObject(2, remainingPacket.getRemainigpacket_name());
        stm.setObject(3, remainingPacket.getRemainigpacket_date());
        stm.setObject(4, remainingPacket.getRemainigpacket_account());

        return stm.executeUpdate();
    }

    public static boolean deletePacketById(String id) throws SQLException, ClassNotFoundException {
        String quary = "delete  from remainingpacket WHERE remainigpacket_centerid='" + id + "' ORDER BY remainigpacket_id";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        int res = stm.executeUpdate();

        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static List<RemainingPacket> searchAllPackets() throws SQLException, ClassNotFoundException {
        String quary = "Select * From remainingpacket";
        Connection conn = MysqlDbConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(quary);
        ResultSet res = stm.executeQuery();

        List<RemainingPacket> list = new ArrayList();
        while (res.next()) {
            list.add(new RemainingPacket(res.getInt("remainigpacket_id"), res.getString("remainigpacket_centerid"), res.getString("remainigpacket_name"), res.getString("remainigpacket_date"), res.getString("remainigpacket_account")));
        }
        res = null;
        return list;
    }

}
