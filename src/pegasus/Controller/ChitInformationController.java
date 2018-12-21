/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.Controller;

import java.sql.SQLException;

/**
 *
 * @author Chamil123
 */
public class ChitInformationController {

    static int remainigPackets;
    static int DailyAllChitCount;
    static int completedPackets;
    static int deleteNotification = 0;

    public static void setStaticTotalChits(int sumerylineListList) throws SQLException, ClassNotFoundException {
        DailyAllChitCount = sumerylineListList;
    }

    public static int getAllDailyChits() throws SQLException, ClassNotFoundException {
        return DailyAllChitCount;
    }

    public static void setStaticRemainingPackets(int remainingPackets) throws SQLException, ClassNotFoundException {
        remainigPackets = remainingPackets;

    }

    public static int getAllRemainigPackets() throws SQLException, ClassNotFoundException {
        return remainigPackets;
    }

    public static void completedPackets(int completedPacket) throws SQLException, ClassNotFoundException {
        completedPackets = completedPacket;
    }

    public static int getcompletedPackets() throws SQLException, ClassNotFoundException {
        return completedPackets;
    }

    public static void deleteTemperNotification(int i) {
        deleteNotification = i;

    }

    public static int getNotificationValue() {
        return deleteNotification;

    }
}
