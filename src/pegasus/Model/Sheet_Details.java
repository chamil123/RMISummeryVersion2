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
public class Sheet_Details {

    private Integer sheetdetails_id;
    private double sheetdetails_fullCollection;
    private double sheetdetails_cash;
    private double sheetdetails_excess;
    private double sheetdetails_pd;
    private int senter_id;
    private String sheetdetails_date;

    public Sheet_Details(Integer sheetdetails_id, double sheetdetails_fullCollection, double sheetdetails_cash, double sheetdetails_excess, double sheetdetails_pd, int senter_id, String sheetdetails_date) {
        this.sheetdetails_id = sheetdetails_id;
        this.sheetdetails_fullCollection = sheetdetails_fullCollection;
        this.sheetdetails_cash = sheetdetails_cash;
        this.sheetdetails_excess = sheetdetails_excess;
        this.sheetdetails_pd = sheetdetails_pd;
        this.senter_id = senter_id;
        this.sheetdetails_date = sheetdetails_date;
    }

    /**
     * @return the sheetdetails_id
     */
    public Integer getSheetdetails_id() {
        return sheetdetails_id;
    }

    /**
     * @param sheetdetails_id the sheetdetails_id to set
     */
    public void setSheetdetails_id(Integer sheetdetails_id) {
        this.sheetdetails_id = sheetdetails_id;
    }

    /**
     * @return the sheetdetails_fullCollection
     */
    public double getSheetdetails_fullCollection() {
        return sheetdetails_fullCollection;
    }

    /**
     * @param sheetdetails_fullCollection the sheetdetails_fullCollection to set
     */
    public void setSheetdetails_fullCollection(double sheetdetails_fullCollection) {
        this.sheetdetails_fullCollection = sheetdetails_fullCollection;
    }

    /**
     * @return the sheetdetails_cash
     */
    public double getSheetdetails_cash() {
        return sheetdetails_cash;
    }

    /**
     * @param sheetdetails_cash the sheetdetails_cash to set
     */
    public void setSheetdetails_cash(double sheetdetails_cash) {
        this.sheetdetails_cash = sheetdetails_cash;
    }

    /**
     * @return the sheetdetails_excess
     */
    public double getSheetdetails_excess() {
        return sheetdetails_excess;
    }

    /**
     * @param sheetdetails_excess the sheetdetails_excess to set
     */
    public void setSheetdetails_excess(double sheetdetails_excess) {
        this.sheetdetails_excess = sheetdetails_excess;
    }

    /**
     * @return the sheetdetails_pd
     */
    public double getSheetdetails_pd() {
        return sheetdetails_pd;
    }

    /**
     * @param sheetdetails_pd the sheetdetails_pd to set
     */
    public void setSheetdetails_pd(double sheetdetails_pd) {
        this.sheetdetails_pd = sheetdetails_pd;
    }

    /**
     * @return the senter_id
     */
    public int getSenter_id() {
        return senter_id;
    }

    /**
     * @param senter_id the senter_id to set
     */
    public void setSenter_id(int senter_id) {
        this.senter_id = senter_id;
    }

    /**
     * @return the sheetdetails_date
     */
    public String getSheetdetails_date() {
        return sheetdetails_date;
    }

    /**
     * @param sheetdetails_date the sheetdetails_date to set
     */
    public void setSheetdetails_date(String sheetdetails_date) {
        this.sheetdetails_date = sheetdetails_date;
    }

}
