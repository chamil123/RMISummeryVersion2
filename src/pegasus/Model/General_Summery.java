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
public class General_Summery implements Serializable {

    private Integer generalSummery_id;
    private String generalSummery_date;
    private String generalSummery_name;
    private double generalSummery_fc;
    private double generalSummery_cash;
    private double generalSummery_pd;
    private double generalSummery_noComAmmount;
    private double generalSummery_lessComAmmount;
    private double generalSummery_lcnc;
    private double generalSummery_paperCash;
    private double generalSummery_compay;
    private double generalSummery_expenses;
    private double generalSummery_paymentTot;
    private double generalSummery_loan;
    private double generalSummery_excess;
    private double generalSummery_payment;
    private int account_id;

    public General_Summery(Integer generalSummery_id, String generalSummery_date, String generalSummery_name, double generalSummery_fc, double generalSummery_cash, double generalSummery_pd, double generalSummery_noComAmmount, double generalSummery_lessComAmmount, double generalSummery_lcnc, double generalSummery_paperCash, double generalSummery_compay, double generalSummery_expenses, double generalSummery_paymentTot, double generalSummery_loan, double generalSummery_excess, double generalSummery_payment, int account_id) {
        this.generalSummery_id = generalSummery_id;
        this.generalSummery_date = generalSummery_date;
        this.generalSummery_name = generalSummery_name;
        this.generalSummery_fc = generalSummery_fc;
        this.generalSummery_cash = generalSummery_cash;
        this.generalSummery_pd = generalSummery_pd;
        this.generalSummery_noComAmmount = generalSummery_noComAmmount;
        this.generalSummery_lessComAmmount = generalSummery_lessComAmmount;
        this.generalSummery_lcnc = generalSummery_lcnc;
        this.generalSummery_paperCash = generalSummery_paperCash;
        this.generalSummery_compay = generalSummery_compay;
        this.generalSummery_expenses = generalSummery_expenses;
        this.generalSummery_paymentTot = generalSummery_paymentTot;
        this.generalSummery_loan = generalSummery_loan;
        this.generalSummery_excess = generalSummery_excess;
        this.generalSummery_payment = generalSummery_payment;
        this.account_id = account_id;
    }

    /**
     * @return the generalSummery_id
     */
    public Integer getGeneralSummery_id() {
        return generalSummery_id;
    }

    /**
     * @param generalSummery_id the generalSummery_id to set
     */
    public void setGeneralSummery_id(Integer generalSummery_id) {
        this.generalSummery_id = generalSummery_id;
    }

    /**
     * @return the generalSummery_date
     */
    public String getGeneralSummery_date() {
        return generalSummery_date;
    }

    /**
     * @param generalSummery_date the generalSummery_date to set
     */
    public void setGeneralSummery_date(String generalSummery_date) {
        this.generalSummery_date = generalSummery_date;
    }

    /**
     * @return the generalSummery_name
     */
    public String getGeneralSummery_name() {
        return generalSummery_name;
    }

    /**
     * @param generalSummery_name the generalSummery_name to set
     */
    public void setGeneralSummery_name(String generalSummery_name) {
        this.generalSummery_name = generalSummery_name;
    }

    /**
     * @return the generalSummery_fc
     */
    public double getGeneralSummery_fc() {
        return generalSummery_fc;
    }

    /**
     * @param generalSummery_fc the generalSummery_fc to set
     */
    public void setGeneralSummery_fc(double generalSummery_fc) {
        this.generalSummery_fc = generalSummery_fc;
    }

    /**
     * @return the generalSummery_cash
     */
    public double getGeneralSummery_cash() {
        return generalSummery_cash;
    }

    /**
     * @param generalSummery_cash the generalSummery_cash to set
     */
    public void setGeneralSummery_cash(double generalSummery_cash) {
        this.generalSummery_cash = generalSummery_cash;
    }

    /**
     * @return the generalSummery_pd
     */
    public double getGeneralSummery_pd() {
        return generalSummery_pd;
    }

    /**
     * @param generalSummery_pd the generalSummery_pd to set
     */
    public void setGeneralSummery_pd(double generalSummery_pd) {
        this.generalSummery_pd = generalSummery_pd;
    }

    /**
     * @return the generalSummery_noComAmmount
     */
    public double getGeneralSummery_noComAmmount() {
        return generalSummery_noComAmmount;
    }

    /**
     * @param generalSummery_noComAmmount the generalSummery_noComAmmount to set
     */
    public void setGeneralSummery_noComAmmount(double generalSummery_noComAmmount) {
        this.generalSummery_noComAmmount = generalSummery_noComAmmount;
    }

    /**
     * @return the generalSummery_lessComAmmount
     */
    public double getGeneralSummery_lessComAmmount() {
        return generalSummery_lessComAmmount;
    }

    /**
     * @param generalSummery_lessComAmmount the generalSummery_lessComAmmount to
     * set
     */
    public void setGeneralSummery_lessComAmmount(double generalSummery_lessComAmmount) {
        this.generalSummery_lessComAmmount = generalSummery_lessComAmmount;
    }

    /**
     * @return the generalSummery_lcnc
     */
    public double getGeneralSummery_lcnc() {
        return generalSummery_lcnc;
    }

    /**
     * @param generalSummery_lcnc the generalSummery_lcnc to set
     */
    public void setGeneralSummery_lcnc(double generalSummery_lcnc) {
        this.generalSummery_lcnc = generalSummery_lcnc;
    }

    /**
     * @return the generalSummery_paperCash
     */
    public double getGeneralSummery_paperCash() {
        return generalSummery_paperCash;
    }

    /**
     * @param generalSummery_paperCash the generalSummery_paperCash to set
     */
    public void setGeneralSummery_paperCash(double generalSummery_paperCash) {
        this.generalSummery_paperCash = generalSummery_paperCash;
    }

    /**
     * @return the generalSummery_compay
     */
    public double getGeneralSummery_compay() {
        return generalSummery_compay;
    }

    /**
     * @param generalSummery_compay the generalSummery_compay to set
     */
    public void setGeneralSummery_compay(double generalSummery_compay) {
        this.generalSummery_compay = generalSummery_compay;
    }

    /**
     * @return the generalSummery_expenses
     */
    public double getGeneralSummery_expenses() {
        return generalSummery_expenses;
    }

    /**
     * @param generalSummery_expenses the generalSummery_expenses to set
     */
    public void setGeneralSummery_expenses(double generalSummery_expenses) {
        this.generalSummery_expenses = generalSummery_expenses;
    }

    /**
     * @return the generalSummery_paymentTot
     */
    public double getGeneralSummery_paymentTot() {
        return generalSummery_paymentTot;
    }

    /**
     * @param generalSummery_paymentTot the generalSummery_paymentTot to set
     */
    public void setGeneralSummery_paymentTot(double generalSummery_paymentTot) {
        this.generalSummery_paymentTot = generalSummery_paymentTot;
    }

    /**
     * @return the generalSummery_loan
     */
    public double getGeneralSummery_loan() {
        return generalSummery_loan;
    }

    /**
     * @param generalSummery_loan the generalSummery_loan to set
     */
    public void setGeneralSummery_loan(double generalSummery_loan) {
        this.generalSummery_loan = generalSummery_loan;
    }

    /**
     * @return the generalSummery_excess
     */
    public double getGeneralSummery_excess() {
        return generalSummery_excess;
    }

    /**
     * @param generalSummery_excess the generalSummery_excess to set
     */
    public void setGeneralSummery_excess(double generalSummery_excess) {
        this.generalSummery_excess = generalSummery_excess;
    }

    /**
     * @return the generalSummery_payment
     */
    public double getGeneralSummery_payment() {
        return generalSummery_payment;
    }

    /**
     * @param generalSummery_payment the generalSummery_payment to set
     */
    public void setGeneralSummery_payment(double generalSummery_payment) {
        this.generalSummery_payment = generalSummery_payment;
    }

    /**
     * @return the account_id
     */
    public int getAccount_id() {
        return account_id;
    }

    /**
     * @param account_id the account_id to set
     */
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

}
