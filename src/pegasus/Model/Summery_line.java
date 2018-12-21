/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.Model;

import java.io.Serializable;

/**
 *
 * @author Chamil123
 */
public class Summery_line implements Serializable {

    private Integer summery_line_id;
    private String summery_line_chitNumber;
    private double summery_line_investment;
    private double summery_line_payment;
    private String summery_line_date;
    private int center_id;

    public Summery_line(Integer summery_line_id, String summery_line_chitNumber, double summery_line_investment, double summery_line_payment, String summery_line_date, int center_id) {
        this.summery_line_id = summery_line_id;
        this.summery_line_chitNumber = summery_line_chitNumber;
        this.summery_line_investment = summery_line_investment;
        this.summery_line_payment = summery_line_payment;
        this.summery_line_date = summery_line_date;
        this.center_id = center_id;
    }

    /**
     * @return the summery_line_id
     */
    public Integer getSummery_line_id() {
        return summery_line_id;
    }

    /**
     * @param summery_line_id the summery_line_id to set
     */
    public void setSummery_line_id(Integer summery_line_id) {
        this.summery_line_id = summery_line_id;
    }

    /**
     * @return the summery_line_chitNumber
     */
    public String getSummery_line_chitNumber() {
        return summery_line_chitNumber;
    }

    /**
     * @param summery_line_chitNumber the summery_line_chitNumber to set
     */
    public void setSummery_line_chitNumber(String summery_line_chitNumber) {
        this.summery_line_chitNumber = summery_line_chitNumber;
    }

    /**
     * @return the summery_line_investment
     */
    public double getSummery_line_investment() {
        return summery_line_investment;
    }

    /**
     * @param summery_line_investment the summery_line_investment to set
     */
    public void setSummery_line_investment(double summery_line_investment) {
        this.summery_line_investment = summery_line_investment;
    }

    /**
     * @return the summery_line_payment
     */
    public double getSummery_line_payment() {
        return summery_line_payment;
    }

    /**
     * @param summery_line_payment the summery_line_payment to set
     */
    public void setSummery_line_payment(double summery_line_payment) {
        this.summery_line_payment = summery_line_payment;
    }

    /**
     * @return the summery_line_date
     */
    public String getSummery_line_date() {
        return summery_line_date;
    }

    /**
     * @param summery_line_date the summery_line_date to set
     */
    public void setSummery_line_date(String summery_line_date) {
        this.summery_line_date = summery_line_date;
    }

    /**
     * @return the center_id
     */
    public int getCenter_id() {
        return center_id;
    }

    /**
     * @param center_id the center_id to set
     */
    public void setCenter_id(int center_id) {
        this.center_id = center_id;
    }

}
