/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.POSTransaction.controller;

public class clsBuyPromotionItemDtl
{

    private String itemCode;

    private double buyPromoItemQty;

    private double totalItemQty;

    private double totalAmount;
    private String buyPromoOn;
    private String getPromoOn;

    public String getItemCode()
    {
        return itemCode;
    }

    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }

    public double getBuyPromoItemQty()
    {
        return buyPromoItemQty;
    }

    public void setBuyPromoItemQty(double buyPromoItemQty)
    {
        this.buyPromoItemQty = buyPromoItemQty;
    }

    public double getTotalItemQty()
    {
        return totalItemQty;
    }

    public void setTotalItemQty(double totalItemQty)
    {
        this.totalItemQty = totalItemQty;
    }

    public double getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getBuyPromoOn()
    {
        return buyPromoOn;
    }

    public void setBuyPromoOn(String buyPromoOn)
    {
        this.buyPromoOn = buyPromoOn;
    }

    public String getGetPromoOn()
    {
        return getPromoOn;
    }

    public void setGetPromoOn(String getPromoOn)
    {
        this.getPromoOn = getPromoOn;
    }

}
