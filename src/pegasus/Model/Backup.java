/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.Model;

/**
 *
 * @author Chamil123
 */
public class Backup {

    private Integer backup_id;
    private String backup_date;
    private String backup_string;

    public Backup(Integer backup_id, String backup_date, String backup_string) {
        this.backup_id = backup_id;
        this.backup_date = backup_date;
        this.backup_string = backup_string;
    }

    /**
     * @return the backup_id
     */
    public Integer getBackup_id() {
        return backup_id;
    }

    /**
     * @param backup_id the backup_id to set
     */
    public void setBackup_id(Integer backup_id) {
        this.backup_id = backup_id;
    }

    /**
     * @return the backup_date
     */
    public String getBackup_date() {
        return backup_date;
    }

    /**
     * @param backup_date the backup_date to set
     */
    public void setBackup_date(String backup_date) {
        this.backup_date = backup_date;
    }

    /**
     * @return the backup_string
     */
    public String getBackup_string() {
        return backup_string;
    }

    /**
     * @param backup_string the backup_string to set
     */
    public void setBackup_string(String backup_string) {
        this.backup_string = backup_string;
    }

}
