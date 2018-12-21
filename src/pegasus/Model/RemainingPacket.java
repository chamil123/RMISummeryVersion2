/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.Model;

/**
 *
 * @author Chamil-PC
 */
public class RemainingPacket {

    private Integer remainigpacket_id;
    private String remainigpacket_centerid;
    private String remainigpacket_name;
    private String remainigpacket_date;
    private String remainigpacket_account;

    public RemainingPacket(Integer remainigpacket_id, String remainigpacket_centerid, String remainigpacket_name, String remainigpacket_date, String remainigpacket_account) {
        this.remainigpacket_id = remainigpacket_id;
        this.remainigpacket_centerid = remainigpacket_centerid;
        this.remainigpacket_name = remainigpacket_name;
        this.remainigpacket_date = remainigpacket_date;
        this.remainigpacket_account = remainigpacket_account;
    }

    /**
     * @return the remainigpacket_id
     */
    public Integer getRemainigpacket_id() {
        return remainigpacket_id;
    }

    /**
     * @param remainigpacket_id the remainigpacket_id to set
     */
    public void setRemainigpacket_id(Integer remainigpacket_id) {
        this.remainigpacket_id = remainigpacket_id;
    }

    /**
     * @return the remainigpacket_centerid
     */
    public String getRemainigpacket_centerid() {
        return remainigpacket_centerid;
    }

    /**
     * @param remainigpacket_centerid the remainigpacket_centerid to set
     */
    public void setRemainigpacket_centerid(String remainigpacket_centerid) {
        this.remainigpacket_centerid = remainigpacket_centerid;
    }

    /**
     * @return the remainigpacket_name
     */
    public String getRemainigpacket_name() {
        return remainigpacket_name;
    }

    /**
     * @param remainigpacket_name the remainigpacket_name to set
     */
    public void setRemainigpacket_name(String remainigpacket_name) {
        this.remainigpacket_name = remainigpacket_name;
    }

    /**
     * @return the remainigpacket_date
     */
    public String getRemainigpacket_date() {
        return remainigpacket_date;
    }

    /**
     * @param remainigpacket_date the remainigpacket_date to set
     */
    public void setRemainigpacket_date(String remainigpacket_date) {
        this.remainigpacket_date = remainigpacket_date;
    }

    /**
     * @return the remainigpacket_account
     */
    public String getRemainigpacket_account() {
        return remainigpacket_account;
    }

    /**
     * @param remainigpacket_account the remainigpacket_account to set
     */
    public void setRemainigpacket_account(String remainigpacket_account) {
        this.remainigpacket_account = remainigpacket_account;
    }

}
