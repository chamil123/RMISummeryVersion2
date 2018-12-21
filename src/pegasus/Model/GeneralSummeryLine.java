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
public class GeneralSummeryLine {

    private Integer generalSummery_line_id;
    private String generalSummery_line_centerName;
    private double generalSummery_line_fc;
    private double generalSummery_line_payment;
    private String generalSummery_line_date;
    private int generalSummery_id;

    public GeneralSummeryLine(Integer generalSummery_line_id, String generalSummery_line_centerName, double generalSummery_line_fc, double generalSummery_line_payment, String generalSummery_line_date, int generalSummery_id) {
        this.generalSummery_line_id = generalSummery_line_id;
        this.generalSummery_line_centerName = generalSummery_line_centerName;
        this.generalSummery_line_fc = generalSummery_line_fc;
        this.generalSummery_line_payment = generalSummery_line_payment;
        this.generalSummery_line_date = generalSummery_line_date;
        this.generalSummery_id = generalSummery_id;
    }

    /**
     * @return the generalSummery_line_id
     */
    public Integer getGeneralSummery_line_id() {
        return generalSummery_line_id;
    }

    /**
     * @param generalSummery_line_id the generalSummery_line_id to set
     */
    public void setGeneralSummery_line_id(Integer generalSummery_line_id) {
        this.generalSummery_line_id = generalSummery_line_id;
    }

    /**
     * @return the generalSummery_line_centerName
     */
    public String getGeneralSummery_line_centerName() {
        return generalSummery_line_centerName;
    }

    /**
     * @param generalSummery_line_centerName the generalSummery_line_centerName
     * to set
     */
    public void setGeneralSummery_line_centerName(String generalSummery_line_centerName) {
        this.generalSummery_line_centerName = generalSummery_line_centerName;
    }

    /**
     * @return the generalSummery_line_fc
     */
    public double getGeneralSummery_line_fc() {
        return generalSummery_line_fc;
    }

    /**
     * @param generalSummery_line_fc the generalSummery_line_fc to set
     */
    public void setGeneralSummery_line_fc(double generalSummery_line_fc) {
        this.generalSummery_line_fc = generalSummery_line_fc;
    }

    /**
     * @return the generalSummery_line_payment
     */
    public double getGeneralSummery_line_payment() {
        return generalSummery_line_payment;
    }

    /**
     * @param generalSummery_line_payment the generalSummery_line_payment to set
     */
    public void setGeneralSummery_line_payment(double generalSummery_line_payment) {
        this.generalSummery_line_payment = generalSummery_line_payment;
    }

    /**
     * @return the generalSummery_line_date
     */
    public String getGeneralSummery_line_date() {
        return generalSummery_line_date;
    }

    /**
     * @param generalSummery_line_date the generalSummery_line_date to set
     */
    public void setGeneralSummery_line_date(String generalSummery_line_date) {
        this.generalSummery_line_date = generalSummery_line_date;
    }

    /**
     * @return the generalSummery_id
     */
    public int getGeneralSummery_id() {
        return generalSummery_id;
    }

    /**
     * @param generalSummery_id the generalSummery_id to set
     */
    public void setGeneralSummery_id(int generalSummery_id) {
        this.generalSummery_id = generalSummery_id;
    }

}
