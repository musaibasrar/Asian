package com.model.mess.stockentry.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.mess.item.dao.MessItemsDAO;
import com.model.mess.item.dto.MessItems;
import com.model.mess.stockentry.dao.MessStockEntryDAO;
import com.model.mess.stockentry.dto.MessStockEntry;

public class MessStockEntryService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	
	public MessStockEntryService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}


	public void getMRVDetails()  throws IOException {
		
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			int invoiceDetailsId = Integer.parseInt(request.getParameter("invoicedetailsid"));
			String supplierRefNo = request.getParameter("supplierreferenceno");
			String invoicetotal = request.getParameter("invoicetotal");
			String supplierName = request.getParameter("suppliername");
			String invoiceDate = request.getParameter("entrydate");
			
			request.setAttribute("supplierreferenceno", supplierRefNo);
			request.setAttribute("invoicetotal", invoicetotal);
			request.setAttribute("suppliername", supplierName);
			request.setAttribute("entrydate", invoiceDate);
			
			List<MessStockEntry> messStockEntryList = new ArrayList<MessStockEntry>();
			messStockEntryList = new MessStockEntryDAO().getMRVDetails(invoiceDetailsId);
			
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/xml");
		        response.setHeader("Cache-Control", "no-cache");
		        
		        
		        try {
		        	
		        	StringBuilder tableBuilder = new StringBuilder(
		        				"<table  style='margin-left: auto;margin-right: auto;'>" + 
		        				"						<tr>" + 
		        				"							<td class='alignRight'>Supplier &nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'>" + 
		        				"								"+supplierName+"" + 
		        				"							</td>" + 
		        				"							" + 
		        				"							<td class='alignRight'>Reference/Invoice No.&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'>"
		        				+ "									"+supplierRefNo+"</td>" + 
		        				"							" + 
		        				"						</tr>" + 
		        				"						<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"						<tr>" + 
		        				"						<td class='alignRight'>Invoice Date&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'> "+invoiceDate+"</td>" + 
		        				"						<td class='alignRight'>Grand Total&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'> "+invoicetotal+"</td>" + 
		        				"							</tr>" + 
		        				"							<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"					</table>"
		        			
		        			
		        			);
		        	
		        	
		        	StringBuilder rowBuidler = new StringBuilder( "<table border='1' style='margin-left: auto;margin-right: auto;' style='border-color:#4b6a84' id='vd'>" + 
			        													"<thead>" + 
			        													"<tr class='headerText' >" + 
			        													"<th>Name</th>" + 
			        													"<th>Batch No</th>" + 
			        													"<th>Quantity</th>" + 
			        													"<th>Nature</th>" + 
			        													"<th>SPrice</th>" +
			        													"<th>PPrice</th>" + 
			        													"<th>Item Total</th>" + 
			        													"</tr>" + 
			        													"</thead>" + 
		        													"<tbody>");
		        	
		        	
		        	for (MessStockEntry messStockEntry : messStockEntryList) {
		        		
		        			   MessItems messItems = new MessItemsDAO().getItem(messStockEntry.getItemid());
		        			   float itemTotal = messStockEntry.getQuantity() * messStockEntry.getItemunitprice();
		        			   
		        		rowBuidler.append(
		        	                 "<tr style='border-color:#000000' border='1' cellpadding='1' cellspacing='1' >" + 
		        			         "<td class='dataText'>"+messItems.getName()+"</td>" + 
		        			         "<td class='dataText'>"+messStockEntry.getBatchno()+"</td>" +
		        			         "<td class='dataText'>"+messStockEntry.getQuantity()+"</td>" + 
		        			         "<td class='dataText'>"+messItems.getUnitofmeasure()+"</td>" + 
		        			         "<td class='dataText'>"+messStockEntry.getItemunitprice()+"</td>" + 
		        			         "<td class='dataText'>"+messStockEntry.getExternalid()+"</td>" + 
		        			         "<td class='dataText'>"+itemTotal+"</td>" + 
		        			         "</tr>");
		        		
		        		
		        	}
		        	
		        	rowBuidler.append("</tbody>" + 
		        			"		                </table>");
		        	
		        	tableBuilder.append(rowBuidler.toString());
		        	String outputTable = tableBuilder.toString();
		        	
		        	response.getWriter().println(outputTable);
		        	
		        } catch (Exception e) {
		            out.write("<table> <tr><td>Data Not Available</td></tr></table>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}
		
		
	}
	
}
