/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.POSTransaction.controller;

import static com.POSGlobal.controller.clsBenowIntegration.encrypt;
import com.POSGlobal.controller.clsGlobalVarClass;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 * This class is responsible for handling online food orders using WERA API.
 * You can contact to Pravin(+918976303406) a technical person from WERA Foods.
 * Other persons Pooja,WERA Foods Technology Pvt. Ltd.(+919920994466).
 * You all need to take API keys from WERA to work these all API's.These are the "X-Wera-Api-Key","merchant_id" and this
 * must be setup in property setup.
 * 
 * @author Ajim
 */
public class clsWERAOnlineOrderIntegration
{

    public clsWERAOnlineOrderIntegration()
    {

    }

    /**
     * This method is use to fetch all online menu from WERA.
     * You can test this API in the WERA Online Food Form in Transaction Module.But before that WERA Setup must be setup in Property Setup.
     * This method takes no argument and returns the Json object which contains the online menus.
     * @return JSONObject
     */
    public JSONObject funCallDownloadMenuAPI()
    {
	JSONObject rootJSONObject = new JSONObject();
	try
	{

	    String benowURL = "https://api.werafoods.com/pos/v1/menu/download";

	    URL url = new URL(benowURL);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setDoOutput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setRequestProperty("X-Wera-Api-Key", clsGlobalVarClass.gWERAAuthenticationAPIKey);

	    org.json.simple.JSONObject jObjBodyParameters = new org.json.simple.JSONObject();
	    jObjBodyParameters.put("merchant_id", Long.parseLong(clsGlobalVarClass.gWERAMerchantOutletId));//from property setup

	    OutputStream os = conn.getOutputStream();
	    os.write(jObjBodyParameters.toJSONString().getBytes());
	    os.flush();
	    /*if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED)
            {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }*/
	    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	    String output = "", op = "";

	    while ((output = br.readLine()) != null)
	    {
		op += output;
	    }

	    JSONParser parser = new JSONParser();
	    JSONObject jObjMenuDownloaded = (JSONObject) parser.parse(op);
	    conn.disconnect();

	    JSONArray jArrItems = new JSONArray();

	    JSONObject jObjDetails = (JSONObject) jObjMenuDownloaded.get("details");
	    JSONArray jArrMenu = (JSONArray) jObjDetails.get("menu");

	    for (int menuHead = 0; menuHead < jArrMenu.size(); menuHead++)
	    {
		JSONObject jObjMenuHead = (JSONObject) jArrMenu.get(menuHead);
		String menuHeadName = jObjMenuHead.get("category_name").toString();

		JSONArray arrItems = (JSONArray) jObjMenuHead.get("items");
		for (int item = 0; item < arrItems.size(); item++)
		{
		    JSONObject jObjItem = (JSONObject) arrItems.get(item);

		    String itemCode = jObjItem.get("item_id").toString();
		    String itemName = jObjItem.get("item_name").toString();

		    String addonItemExists = jObjItem.get("addon_item_exists").toString();
		    boolean isAddOnItemExists = Boolean.parseBoolean(addonItemExists);

		    JSONArray jArrItemPrices = (JSONArray) jObjItem.get("prices");

		    jObjItem = new JSONObject();
		    jObjItem.put("itemId", itemCode);
		    jObjItem.put("itemName", itemName);
		    jObjItem.put("menuHeadName", menuHeadName);

		    double itemRate = Double.parseDouble(((JSONObject) jArrItemPrices.get(0)).get("price").toString());
		    jObjItem.put("itemRate", itemRate);

		    JSONArray jArrModifires = new JSONArray();
		    for (int price = 0; price < jArrItemPrices.size(); price++)
		    {
			JSONObject jObjItemPrice = (JSONObject) jArrItemPrices.get(price);

			
			jObjItem.put("isAddOnItemExists", isAddOnItemExists);
			if (isAddOnItemExists)
			{
			    JSONArray jArrItemAddOns = (JSONArray) jObjItemPrice.get("add_on_item");
			    for (int addOn = 0; addOn < jArrItemAddOns.size(); addOn++)
			    {
				JSONObject jObjAddOn = (JSONObject) jArrItemAddOns.get(addOn);

				String modifierGroupName = jObjAddOn.get("subcat_name").toString();
				int min = Integer.parseInt(jObjAddOn.get("min").toString());
				int max = Integer.parseInt(jObjAddOn.get("max").toString());

				JSONArray jArrModifiers = (JSONArray) jObjAddOn.get("sub_item");
				for (int modifier = 0; modifier < jArrModifiers.size(); modifier++)
				{
				    JSONObject jObjModifier = (JSONObject) jArrModifiers.get(modifier);

				    String modifierName = jObjModifier.get("sub_item_name").toString();
				    double modifierRate = Double.parseDouble(jObjModifier.get("price").toString());

				    jObjModifier = new JSONObject();

				    jObjModifier.put("modifierName", modifierName);
				    jObjModifier.put("modifierRate", modifierRate);
				    jObjModifier.put("modifierGroupName", modifierGroupName);
				    jObjModifier.put("min", min);
				    jObjModifier.put("max", max);

				    jArrModifires.add(jObjModifier);
				}
			    }			    
			}
		    }
		    jObjItem.put("modifiers", jArrModifires);
		    jArrItems.add(jObjItem);
		}
	    }
	    rootJSONObject.put("items", jArrItems);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	return rootJSONObject;
    }

    /**
     * This method is use to get all pending orders(new orders).
     * This method takes no argument and returns the JSON object which contains tall new orders(Pending Orders).
     * You can test this API in the WERA Online Food Form in Transaction Module.But before that WERA Setup must be setup in Property Setup.
     * The tree in left of form is showing the pending orders.
     * @return JSONObject
     */
    public JSONObject funGetAllPendingOrders()
    {
	JSONObject rootJSONObject = new JSONObject();
	try
	{

	    String benowURL = "https://api.werafoods.com/pos/v1/merchant/pendingorders";

	    URL url = new URL(benowURL);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setDoOutput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setRequestProperty("X-Wera-Api-Key", clsGlobalVarClass.gWERAAuthenticationAPIKey);

	    org.json.simple.JSONObject jObjBodyParameters = new org.json.simple.JSONObject();
	    jObjBodyParameters.put("merchant_id", Long.parseLong(clsGlobalVarClass.gWERAMerchantOutletId));//from property setup

	    OutputStream os = conn.getOutputStream();
	    os.write(jObjBodyParameters.toJSONString().getBytes());
	    os.flush();
	    /*if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED)
            {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }*/
	    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	    String output = "", op = "";

	    while ((output = br.readLine()) != null)
	    {
		op += output;
	    }

	    JSONParser parser = new JSONParser();
	    JSONObject jObjPendingOrders = (JSONObject) parser.parse(op);
	    conn.disconnect();

	    rootJSONObject = jObjPendingOrders;
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	return rootJSONObject;
    }

    public void funSendRequestForPendingOrders()
    {

	try
	{

	    String benowURL = "https://www.werafoods.com:9005/?message=" + Long.parseLong(clsGlobalVarClass.gWERAMerchantOutletId);

	    URL url = new URL(benowURL);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setDoOutput(true);
	    conn.setRequestMethod("GET");

	    /*if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED)
            {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }*/
	    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	    String output = "", op = "";

	    while ((output = br.readLine()) != null)
	    {
		op += output;
	    }

	    System.out.println("op->" + op);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    public void funCallAcceptTheOrder(String onlineOrderNo, int deliveryTime)
    {
	JSONObject rootJSONObject = new JSONObject();
	try
	{

	    String benowURL = "https://api.werafoods.com/pos/v1/order/accept";

	    URL url = new URL(benowURL);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setDoOutput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setRequestProperty("X-Wera-Api-Key", clsGlobalVarClass.gWERAAuthenticationAPIKey);

	    org.json.simple.JSONObject jObjBodyParameters = new org.json.simple.JSONObject();
	    jObjBodyParameters.put("merchant_id", Long.parseLong(clsGlobalVarClass.gWERAMerchantOutletId));//from property setup
	    jObjBodyParameters.put("order_id", onlineOrderNo);
	    jObjBodyParameters.put("delivery_time", deliveryTime);

	    OutputStream os = conn.getOutputStream();
	    os.write(jObjBodyParameters.toJSONString().getBytes());
	    os.flush();
//	    if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED)
//            {
//                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//            }
	    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	    String output = "", op = "";

	    while ((output = br.readLine()) != null)
	    {
		op += output;
	    }

	    JSONParser parser = new JSONParser();
	    JSONObject jObjPendingOrders = (JSONObject) parser.parse(op);
	    System.out.println("Conn ResponseCode=" + conn.getResponseCode() + "\nop=" + op);
	    conn.disconnect();

	    rootJSONObject = jObjPendingOrders;
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    public void funCallRejectTheOrder(String onlineOrderNo, int rejectionId)
    {
	JSONObject rootJSONObject = new JSONObject();
	try
	{

	    String benowURL = "https://api.werafoods.com/pos/v1/order/reject";

	    URL url = new URL(benowURL);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setDoOutput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setRequestProperty("X-Wera-Api-Key", clsGlobalVarClass.gWERAAuthenticationAPIKey);

	    org.json.simple.JSONObject jObjBodyParameters = new org.json.simple.JSONObject();
	    jObjBodyParameters.put("merchant_id", Long.parseLong(clsGlobalVarClass.gWERAMerchantOutletId));//from property setup
	    jObjBodyParameters.put("order_id", Long.parseLong(onlineOrderNo));
	    jObjBodyParameters.put("rejection_id", rejectionId);

	    OutputStream os = conn.getOutputStream();
	    os.write(jObjBodyParameters.toJSONString().getBytes());
	    os.flush();
//	    if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED)
//            {
//                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//            }
	    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	    String output = "", op = "";

	    while ((output = br.readLine()) != null)
	    {
		op += output;
	    }

	    JSONParser parser = new JSONParser();
	    JSONObject jObjPendingOrders = (JSONObject) parser.parse(op);
	    System.out.println("Conn ResponseCode=" + conn.getResponseCode() + "\nop=" + op);
	    conn.disconnect();

	    rootJSONObject = jObjPendingOrders;
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

}
