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
public class Account implements Serializable {

    private Integer account_id;
    private String account_name;
    private int account_com;
    private double account_paperCash;
    private double account_loan;
    private int account_noCom;
    private int account_lessCom;
    private double account_exp;

    public Account(Integer account_id, String account_name, int account_com, double account_paperCash, double account_loan, int account_noCom, int account_lessCom, double account_exp) {
        this.account_id = account_id;
        this.account_name = account_name;
        this.account_com = account_com;
        this.account_paperCash = account_paperCash;
        this.account_loan = account_loan;
        this.account_noCom = account_noCom;
        this.account_lessCom = account_lessCom;
        this.account_exp = account_exp;
    }

    /**
     * @return the account_id
     */
    public Integer getAccount_id() {
        return account_id;
    }

    /**
     * @param account_id the account_id to set
     */
    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    /**
     * @return the account_name
     */
    public String getAccount_name() {
        return account_name;
    }

    /**
     * @param account_name the account_name to set
     */
    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    /**
     * @return the account_com
     */
    public int getAccount_com() {
        return account_com;
    }

    /**
     * @param account_com the account_com to set
     */
    public void setAccount_com(int account_com) {
        this.account_com = account_com;
    }

    /**
     * @return the account_paperCash
     */
    public double getAccount_paperCash() {
        return account_paperCash;
    }

    /**
     * @param account_paperCash the account_paperCash to set
     */
    public void setAccount_paperCash(double account_paperCash) {
        this.account_paperCash = account_paperCash;
    }

    /**
     * @return the account_loan
     */
    public double getAccount_loan() {
        return account_loan;
    }

    /**
     * @param account_loan the account_loan to set
     */
    public void setAccount_loan(double account_loan) {
        this.account_loan = account_loan;
    }

    /**
     * @return the account_noCom
     */
    public int getAccount_noCom() {
        return account_noCom;
    }

    /**
     * @param account_noCom the account_noCom to set
     */
    public void setAccount_noCom(int account_noCom) {
        this.account_noCom = account_noCom;
    }

    /**
     * @return the account_lessCom
     */
    public int getAccount_lessCom() {
        return account_lessCom;
    }

    /**
     * @param account_lessCom the account_lessCom to set
     */
    public void setAccount_lessCom(int account_lessCom) {
        this.account_lessCom = account_lessCom;
    }

    /**
     * @return the account_exp
     */
    public double getAccount_exp() {
        return account_exp;
    }

    /**
     * @param account_exp the account_exp to set
     */
    public void setAccount_exp(double account_exp) {
        this.account_exp = account_exp;
    }

}
