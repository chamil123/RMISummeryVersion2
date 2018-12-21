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
public class Summery implements Serializable {

    private Integer summery_id;
    private String summery_date;
    private String summery_name;
    private double summery_fc;
    private double summery_cash;
    private double summery_pd;
    private double summery_noComAmount;
    private double summery_lessComAmount;
    private double summery_lcnc;
    private double summery_paperCash;
    private double summery_compay;
    private double summery_expenses;
    private double summery_slippaytot;
    private double summery_loan;
    private double summery_excess;
    private double summery_salary;
    private double summery_payment;
    private double summery_due;
    private int center_id;

    public Summery(Integer summery_id, String summery_date, String summery_name, double summery_fc, double summery_cash, double summery_pd, double summery_noComAmount, double summery_lessComAmount, double summery_lcnc, double summery_paperCash, double summery_compay, double summery_expenses, double summery_slippaytot, double summery_loan, double summery_excess, double summery_salary, double summery_payment, double summery_due, int center_id) {
        this.summery_id = summery_id;
        this.summery_date = summery_date;
        this.summery_name = summery_name;
        this.summery_fc = summery_fc;
        this.summery_cash = summery_cash;
        this.summery_pd = summery_pd;
        this.summery_noComAmount = summery_noComAmount;
        this.summery_lessComAmount = summery_lessComAmount;
        this.summery_lcnc = summery_lcnc;
        this.summery_paperCash = summery_paperCash;
        this.summery_compay = summery_compay;
        this.summery_expenses = summery_expenses;
        this.summery_slippaytot = summery_slippaytot;
        this.summery_loan = summery_loan;
        this.summery_excess = summery_excess;
        this.summery_salary = summery_salary;
        this.summery_payment = summery_payment;
        this.summery_due = summery_due;
        this.center_id = center_id;
    }

    /**
     * @return the summery_id
     */
    public Integer getSummery_id() {
        return summery_id;
    }

    /**
     * @param summery_id the summery_id to set
     */
    public void setSummery_id(Integer summery_id) {
        this.summery_id = summery_id;
    }

    /**
     * @return the summery_date
     */
    public String getSummery_date() {
        return summery_date;
    }

    /**
     * @param summery_date the summery_date to set
     */
    public void setSummery_date(String summery_date) {
        this.summery_date = summery_date;
    }

    /**
     * @return the summery_name
     */
    public String getSummery_name() {
        return summery_name;
    }

    /**
     * @param summery_name the summery_name to set
     */
    public void setSummery_name(String summery_name) {
        this.summery_name = summery_name;
    }

    /**
     * @return the summery_fc
     */
    public double getSummery_fc() {
        return summery_fc;
    }

    /**
     * @param summery_fc the summery_fc to set
     */
    public void setSummery_fc(double summery_fc) {
        this.summery_fc = summery_fc;
    }

    /**
     * @return the summery_cash
     */
    public double getSummery_cash() {
        return summery_cash;
    }

    /**
     * @param summery_cash the summery_cash to set
     */
    public void setSummery_cash(double summery_cash) {
        this.summery_cash = summery_cash;
    }

    /**
     * @return the summery_pd
     */
    public double getSummery_pd() {
        return summery_pd;
    }

    /**
     * @param summery_pd the summery_pd to set
     */
    public void setSummery_pd(double summery_pd) {
        this.summery_pd = summery_pd;
    }

    /**
     * @return the summery_noComAmount
     */
    public double getSummery_noComAmount() {
        return summery_noComAmount;
    }

    /**
     * @param summery_noComAmount the summery_noComAmount to set
     */
    public void setSummery_noComAmount(double summery_noComAmount) {
        this.summery_noComAmount = summery_noComAmount;
    }

    /**
     * @return the summery_lessComAmount
     */
    public double getSummery_lessComAmount() {
        return summery_lessComAmount;
    }

    /**
     * @param summery_lessComAmount the summery_lessComAmount to set
     */
    public void setSummery_lessComAmount(double summery_lessComAmount) {
        this.summery_lessComAmount = summery_lessComAmount;
    }

    /**
     * @return the summery_lcnc
     */
    public double getSummery_lcnc() {
        return summery_lcnc;
    }

    /**
     * @param summery_lcnc the summery_lcnc to set
     */
    public void setSummery_lcnc(double summery_lcnc) {
        this.summery_lcnc = summery_lcnc;
    }

    /**
     * @return the summery_paperCash
     */
    public double getSummery_paperCash() {
        return summery_paperCash;
    }

    /**
     * @param summery_paperCash the summery_paperCash to set
     */
    public void setSummery_paperCash(double summery_paperCash) {
        this.summery_paperCash = summery_paperCash;
    }

    /**
     * @return the summery_compay
     */
    public double getSummery_compay() {
        return summery_compay;
    }

    /**
     * @param summery_compay the summery_compay to set
     */
    public void setSummery_compay(double summery_compay) {
        this.summery_compay = summery_compay;
    }

    /**
     * @return the summery_expenses
     */
    public double getSummery_expenses() {
        return summery_expenses;
    }

    /**
     * @param summery_expenses the summery_expenses to set
     */
    public void setSummery_expenses(double summery_expenses) {
        this.summery_expenses = summery_expenses;
    }

    /**
     * @return the summery_slippaytot
     */
    public double getSummery_slippaytot() {
        return summery_slippaytot;
    }

    /**
     * @param summery_slippaytot the summery_slippaytot to set
     */
    public void setSummery_slippaytot(double summery_slippaytot) {
        this.summery_slippaytot = summery_slippaytot;
    }

    /**
     * @return the summery_loan
     */
    public double getSummery_loan() {
        return summery_loan;
    }

    /**
     * @param summery_loan the summery_loan to set
     */
    public void setSummery_loan(double summery_loan) {
        this.summery_loan = summery_loan;
    }

    /**
     * @return the summery_excess
     */
    public double getSummery_excess() {
        return summery_excess;
    }

    /**
     * @param summery_excess the summery_excess to set
     */
    public void setSummery_excess(double summery_excess) {
        this.summery_excess = summery_excess;
    }

    /**
     * @return the summery_salary
     */
    public double getSummery_salary() {
        return summery_salary;
    }

    /**
     * @param summery_salary the summery_salary to set
     */
    public void setSummery_salary(double summery_salary) {
        this.summery_salary = summery_salary;
    }

    /**
     * @return the summery_payment
     */
    public double getSummery_payment() {
        return summery_payment;
    }

    /**
     * @param summery_payment the summery_payment to set
     */
    public void setSummery_payment(double summery_payment) {
        this.summery_payment = summery_payment;
    }

    /**
     * @return the summery_due
     */
    public double getSummery_due() {
        return summery_due;
    }

    /**
     * @param summery_due the summery_due to set
     */
    public void setSummery_due(double summery_due) {
        this.summery_due = summery_due;
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
