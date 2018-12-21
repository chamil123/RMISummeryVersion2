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
public class Center implements Serializable {

    private Integer center_id;
    private String center_name;
    private int center_com;
    private double center_paperCash;
    private double center_loan;
    private int center_noCom;
    private int center_lessCom;
    private double center_exp;
    private String center_accountType;
    private String center_status;
    private int account_id;

    public Center(Integer center_id, String center_name, int center_com, double center_paperCash, double center_loan, int center_noCom, int center_lessCom, double center_exp, String center_accountType, String center_status, int account_id) {
        this.center_id = center_id;
        this.center_name = center_name;
        this.center_com = center_com;
        this.center_paperCash = center_paperCash;
        this.center_loan = center_loan;
        this.center_noCom = center_noCom;
        this.center_lessCom = center_lessCom;
        this.center_exp = center_exp;
        this.center_accountType = center_accountType;
        this.center_status = center_status;
        this.account_id = account_id;
    }

    /**
     * @return the center_id
     */
    public Integer getCenter_id() {
        return center_id;
    }

    /**
     * @param center_id the center_id to set
     */
    public void setCenter_id(Integer center_id) {
        this.center_id = center_id;
    }

    /**
     * @return the center_name
     */
    public String getCenter_name() {
        return center_name;
    }

    /**
     * @param center_name the center_name to set
     */
    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    /**
     * @return the center_com
     */
    public int getCenter_com() {
        return center_com;
    }

    /**
     * @param center_com the center_com to set
     */
    public void setCenter_com(int center_com) {
        this.center_com = center_com;
    }

    /**
     * @return the center_paperCash
     */
    public double getCenter_paperCash() {
        return center_paperCash;
    }

    /**
     * @param center_paperCash the center_paperCash to set
     */
    public void setCenter_paperCash(double center_paperCash) {
        this.center_paperCash = center_paperCash;
    }

    /**
     * @return the center_loan
     */
    public double getCenter_loan() {
        return center_loan;
    }

    /**
     * @param center_loan the center_loan to set
     */
    public void setCenter_loan(double center_loan) {
        this.center_loan = center_loan;
    }

    /**
     * @return the center_noCom
     */
    public int getCenter_noCom() {
        return center_noCom;
    }

    /**
     * @param center_noCom the center_noCom to set
     */
    public void setCenter_noCom(int center_noCom) {
        this.center_noCom = center_noCom;
    }

    /**
     * @return the center_lessCom
     */
    public int getCenter_lessCom() {
        return center_lessCom;
    }

    /**
     * @param center_lessCom the center_lessCom to set
     */
    public void setCenter_lessCom(int center_lessCom) {
        this.center_lessCom = center_lessCom;
    }

    /**
     * @return the center_exp
     */
    public double getCenter_exp() {
        return center_exp;
    }

    /**
     * @param center_exp the center_exp to set
     */
    public void setCenter_exp(double center_exp) {
        this.center_exp = center_exp;
    }

    /**
     * @return the center_accountType
     */
    public String getCenter_accountType() {
        return center_accountType;
    }

    /**
     * @param center_accountType the center_accountType to set
     */
    public void setCenter_accountType(String center_accountType) {
        this.center_accountType = center_accountType;
    }

    /**
     * @return the center_status
     */
    public String getCenter_status() {
        return center_status;
    }

    /**
     * @param center_status the center_status to set
     */
    public void setCenter_status(String center_status) {
        this.center_status = center_status;
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
