package com.model.mess.stockmove.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.account.dao.AccountDAO;
import com.model.account.dto.VoucherEntrytransactions;
import com.model.mess.item.dao.MessItemsDAO;
import com.model.mess.item.dto.MessItems;
import com.model.mess.item.service.MessItemsService;
import com.model.mess.stockentry.dao.MessStockEntryDAO;
import com.model.mess.stockentry.dto.MessStockAvailability;
import com.model.mess.stockentry.dto.MessStockEntry;
import com.model.mess.stockmove.dao.MessStockMoveDAO;
import com.model.mess.stockmove.dto.MessStockItemDetails;
import com.model.mess.stockmove.dto.MessStockMove;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.util.DataUtil;
import com.util.DateUtil;
import com.util.NumberToWord;

public class MessStockMoveService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	
	public MessStockMoveService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}


	
	
	public boolean saveStockMove() {

		boolean result = false;
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			
				String[] StockEntryIds = request.getParameterValues("ids");
				String[] itemsName = request.getParameterValues("itemsname");
				String[] itemsIds = request.getParameterValues("itemsids");
				String[] issuequantity = request.getParameterValues("issuequantity");
				String[] itemunitprice = request.getParameterValues("itemunitprice");
				String[] purchaseprice = request.getParameterValues("purchaseprice");
				BigDecimal totalValue = BigDecimal.ZERO;
				BigDecimal PurchasePricetotalValue = BigDecimal.ZERO;
				
				List<MessStockMove> messStockMovesList = new ArrayList<MessStockMove>();
				
					for(int i=0; i < StockEntryIds.length ; i++){
						

						MessStockMove messStockMove = new MessStockMove();
						
						messStockMove.setStockentryid(Integer.parseInt(StockEntryIds[i]));
						messStockMove.setItemid(Integer.parseInt(itemsIds[i]));
						messStockMove.setExternalid(itemsName[i]);
						messStockMove.setQuantity(Float.parseFloat(issuequantity[i]));
						messStockMove.setPurpose(itemunitprice[i]);
						messStockMove.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
						messStockMove.setIssuedto(request.getParameter("issuedto"));
						messStockMove.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						messStockMove.setStatus("ACTIVE");
						
						totalValue = totalValue.add(new BigDecimal(issuequantity[i]).multiply(new BigDecimal(itemunitprice[i])));
						PurchasePricetotalValue = PurchasePricetotalValue.add(new BigDecimal(issuequantity[i]).multiply(new BigDecimal(purchaseprice[i])));
						messStockMovesList.add(messStockMove);
					
				}
					
						//Pass J.V. : credit the assets & debit the Expenses
						int drStockLedgerIdExpense = getLedgerAccountId("itemaccountidexpense");
						int crStockLedgerId = getLedgerAccountId("itemaccountid");
						
						VoucherEntrytransactions transactions = new VoucherEntrytransactions();
						
						transactions.setDraccountid(drStockLedgerIdExpense);
						transactions.setCraccountid(crStockLedgerId);
						transactions.setDramount(PurchasePricetotalValue);
						transactions.setCramount(PurchasePricetotalValue);
						transactions.setVouchertype(4);
						transactions.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
						transactions.setEntrydate(DateUtil.todaysDate());
						transactions.setNarration("Towards Stock Issue");
						transactions.setCancelvoucher("no");
						transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
						transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						
						BigDecimal drAmountReceipt = totalValue;
						String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+drStockLedgerIdExpense;

						BigDecimal crAmountReceipt = totalValue;
						String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmountReceipt+" where accountdetailsid="+crStockLedgerId;
						
						
						//Pass J.V. : credit the Revenue & debit the Cash
						int drStockLedgerIdIncome = getLedgerAccountId("cashinhandaccountid");
						int crStockLedgerIdIncome = getLedgerAccountId("incomeaccount");
						
						VoucherEntrytransactions transactionsIncome = new VoucherEntrytransactions();
						
						transactionsIncome.setDraccountid(drStockLedgerIdIncome);
						transactionsIncome.setCraccountid(crStockLedgerIdIncome);
						transactionsIncome.setDramount(totalValue);
						transactionsIncome.setCramount(totalValue);
						transactionsIncome.setVouchertype(4);
						transactionsIncome.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
						transactionsIncome.setEntrydate(DateUtil.todaysDate());
						transactionsIncome.setNarration("Towards Sales of Medicines");
						transactionsIncome.setCancelvoucher("no");
						transactionsIncome.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
						transactionsIncome.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						transactionsIncome.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						
						BigDecimal drAmountReceiptIncome = totalValue;
						String updateDrAccountIncome ="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceiptIncome+" where accountdetailsid="+drStockLedgerIdIncome;

						BigDecimal crAmountReceiptIncome = totalValue;
						String updateCrAccountIncome ="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmountReceiptIncome+" where accountdetailsid="+crStockLedgerIdIncome;
						
						
						result = new MessStockMoveDAO().moveStockSave(messStockMovesList,transactions,updateDrAccount,updateCrAccount,transactionsIncome,updateDrAccountIncome,updateCrAccountIncome);
						
							if(result) {
								
								request.setAttribute("billdetails", messStockMovesList);
								request.setAttribute("billdetailstransactiondate", request.getParameter("transactiondate"));
								request.setAttribute("billdetailscustomername", request.getParameter("issuedto"));
								
								
								NumberToWord toWord = new NumberToWord();
								String grandTotal = "";
								if(totalValue.compareTo(BigDecimal.ZERO) != 0){
									grandTotal = toWord.convert(totalValue.intValue());
								}
								
								StringBuffer res = new StringBuffer();
								String[] strArr = grandTotal.split(" ");
								
								for(String str : strArr){
									char[] stringArray = str.trim().toCharArray();
									stringArray[0] = Character.toUpperCase(stringArray[0]);
									str = new String(stringArray);
									res.append(str).append(" ");
								}
								grandTotal = res.toString().trim();
								request.setAttribute("billdetailstotaltotal", grandTotal+" "+"Only");
								
								//Get Bill No
								MessStockMove msm = new MessStockMoveDAO().getMessStockMoveMaxRow();
								int billNo = 0;
								if(msm!=null) {
									String[] externalId = msm.getExternalid().split("_");
									billNo = Integer.parseInt(externalId[1]) + 1;
								}else {
									billNo = 1;
								}
								
						     	 request.setAttribute("billno", billNo);
						     	 
							}else {
								request.setAttribute("billdetails", "");
								request.setAttribute("billdetailstransactiondate", "");
								request.setAttribute("billdetailscustomername", "");
							}
						request.setAttribute("itemsissued", result);
					}
		
						new MessItemsService(request, response).viewItemDetails();
						
						
						return result;
		}
	
	public boolean saveStockMoveOld() {

		boolean result = false;
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			
				String[] StockEntryIds = request.getParameterValues("itemids");
				/*String[] itemsName = request.getParameterValues("itemsname");
				String[] itemsIds = request.getParameterValues("itemsids");
				String[] issuequantity = request.getParameterValues("issuequantity");
				String[] itemunitprice = request.getParameterValues("itemunitprice");*/
				BigDecimal totalValue = BigDecimal.ZERO;
				
				List<MessStockMove> messStockMovesList = new ArrayList<MessStockMove>();
				
				if(StockEntryIds!=null) {
					
					for(int i=0; i < StockEntryIds.length ; i++){
						String issueQuantity = request.getParameter("issuequantity_"+StockEntryIds[i]);
						float reqQty = Float.parseFloat(issueQuantity);
						//Query stock entry
						
							List<MessStockEntry> messStockEntryList = new MessStockEntryDAO().getItemsStockEntry(Integer.parseInt(StockEntryIds[i])); 
								
								for (MessStockEntry messStockEntry : messStockEntryList) {

									if(reqQty<=messStockEntry.getAvailablequantity()) {

										MessStockMove messStockMove = new MessStockMove();
										
										messStockMove.setStockentryid(messStockEntry.getId());
										messStockMove.setItemid(messStockEntry.getItemid());
										messStockMove.setExternalid(request.getParameter("itemname_"+StockEntryIds[i]));
										messStockMove.setQuantity(reqQty);
										messStockMove.setPurpose(request.getParameter("purpose"));
										messStockMove.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
										messStockMove.setIssuedto(request.getParameter("issuedto"));
										messStockMove.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
										messStockMove.setStatus("ACTIVE");
										messStockMove.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
										
										totalValue = totalValue.add(new BigDecimal(reqQty).multiply(new BigDecimal(messStockEntry.getItemunitprice())));
										messStockMovesList.add(messStockMove);
										break;
									}else if(reqQty>messStockEntry.getAvailablequantity()) {

										MessStockMove messStockMove = new MessStockMove();
										
										messStockMove.setStockentryid(messStockEntry.getId());
										messStockMove.setItemid(messStockEntry.getItemid());
										messStockMove.setExternalid(request.getParameter("itemname_"+StockEntryIds[i]));
										messStockMove.setQuantity(messStockEntry.getAvailablequantity());
										messStockMove.setPurpose(request.getParameter("purpose"));
										messStockMove.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
										messStockMove.setIssuedto(request.getParameter("issuedto"));
										messStockMove.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
										messStockMove.setStatus("ACTIVE");
										messStockMove.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
										
										totalValue = totalValue.add(new BigDecimal(messStockEntry.getAvailablequantity()).multiply(new BigDecimal(messStockEntry.getItemunitprice())));
										messStockMovesList.add(messStockMove);
										
										reqQty -=messStockEntry.getAvailablequantity(); 
									}
									
									
								}
					
						}
					
						//Pass J.V. : credit the assets & debit the Expenses
						int drStockLedgerIdExpense = getLedgerAccountId("itemaccountidexpense");
						int crStockLedgerId = getLedgerAccountId("itemaccountid");
						
						VoucherEntrytransactions transactions = new VoucherEntrytransactions();
						
						transactions.setDraccountid(drStockLedgerIdExpense);
						transactions.setCraccountid(crStockLedgerId);
						transactions.setDramount(totalValue);
						transactions.setCramount(totalValue);
						transactions.setVouchertype(4);
						transactions.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
						transactions.setEntrydate(DateUtil.todaysDate());
						transactions.setNarration("Towards Stock Issue");
						transactions.setCancelvoucher("no");
						transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
						transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						
						BigDecimal drAmountReceipt = totalValue;
						String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+drStockLedgerIdExpense;

						BigDecimal crAmountReceipt = totalValue;
						String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmountReceipt+" where accountdetailsid="+crStockLedgerId;
						
						//result = new MessStockMoveDAO().moveStockSave(messStockMovesList,transactions,updateDrAccount,updateCrAccount);
						request.setAttribute("itemsissued", result);
					}
		
						new MessItemsService(request, response).viewItemDetails();
						
				}	
						return result;
		}


	public void viewStockEntryDetails() {
		
		List<MessStockEntry> messStockEntryList = new ArrayList<MessStockEntry>();
		List<MessStockItemDetails> messStockItemDetailsList = new ArrayList<MessStockItemDetails>();
		List<Integer> itemIds = new ArrayList<Integer>();
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 
			 messStockEntryList = new MessItemsDAO().getItemsStockEntry();
			 
			 for (MessStockEntry messStockEntry : messStockEntryList) {
				 itemIds.add(messStockEntry.getItemid());
			}
			 
			 //get the item details
			 if(!itemIds.isEmpty()) {
				 
			 List<MessItems> messItem = new ArrayList<MessItems>();
			 messItem = new MessItemsDAO().getItemDetailByID(itemIds);
			 
			 for (MessStockEntry messStockEntry : messStockEntryList) {
				 	int stockid = messStockEntry.getItemid();
				 	
				 for (MessItems messItems : messItem) {
					 int itemid = messItems.getId();
					 
					if(stockid == itemid) {
						MessStockItemDetails messStockItemDetails = new MessStockItemDetails();
						messStockItemDetails.setAvailablequantity(messStockEntry.getAvailablequantity());
						messStockItemDetails.setBatchno(messStockEntry.getBatchno());
						messStockItemDetails.setItemid(messItems.getId());
						messStockItemDetails.setItemunitprice(messStockEntry.getItemunitprice());
						messStockItemDetails.setStockentryexternalid(messStockEntry.getExternalid());
						messStockItemDetails.setStockentryid(messStockEntry.getId());
						messStockItemDetails.setItemname(messItems.getName());
						messStockItemDetails.setUnitofmeasure(messItems.getUnitofmeasure());
						messStockItemDetails.setExternalid(messStockEntry.getExternalid());
						messStockItemDetailsList.add(messStockItemDetails);
						break;
					}
				}
			}
			 
		 }
		 }
		 request.setAttribute("messstockitemdetailslist", messStockItemDetailsList);
	}
	
	private Integer getLedgerAccountId(String itemAccount) {
		
	 	
	 	Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
		
        		try {
					properties.load(inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    
        		String ItemLedgerId = properties.getProperty(itemAccount);
		    
		    if(ItemLedgerId!=null) {
		    	return Integer.parseInt(ItemLedgerId);
		    }else {
		    	return 0;
		    }
	}


	public boolean viewStockMoveDetails() {
		
		List<MessStockMove> messStockMoveList = new ArrayList<MessStockMove>();
		boolean result = false;
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 
					try {
						int page = 1;
						int recordsPerPage = 50;
							if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
								page = Integer.parseInt(request.getParameter("page"));
							}

						messStockMoveList = new MessStockMoveDAO().getStockMoveDetails((page - 1) * recordsPerPage,
									recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())); 	
						int noOfRecords = new MessStockMoveDAO().getNoOfRecordsStockMove(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
						request.setAttribute("noOfPages", noOfPages);
						request.setAttribute("currentPage", page);
						
						result = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				
			 
		 }
		 
		 request.setAttribute("messstockmovelist", messStockMoveList);
			
		 return result;
	}


	public void cancelStockMove() {
		
	String[] smIds = request.getParameterValues("stockmoveid");
	
	if(smIds != null) {
		
		for (String stockmoveids : smIds) {
		
		
		MessStockMove messStockMove = new MessStockMoveDAO().getStockMoveDetails(Integer.parseInt(stockmoveids));
		MessStockEntry messStockEntry = new MessItemsDAO().getMessStockEntryByID(messStockMove.getStockentryid());
		float totalValue = messStockMove.getQuantity() * messStockEntry.getItemunitprice();
		
		//Pass J.V. : Debit the assets & credit the Expenses
		
		int drStockLedgerId = getLedgerAccountId("itemaccountid");
		int crStockLedgerIdExpense = getLedgerAccountId("itemaccountidexpense");
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(drStockLedgerId);
		transactions.setCraccountid(crStockLedgerIdExpense);
		transactions.setDramount(BigDecimal.valueOf(totalValue));
		transactions.setCramount(BigDecimal.valueOf(totalValue));
		transactions.setVouchertype(4);
		transactions.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate_"+stockmoveids)));
		transactions.setEntrydate(DateUtil.todaysDate());
		transactions.setNarration("Towards Revarsal of Stock Issue");
		transactions.setCancelvoucher("no");
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
		transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
		
		BigDecimal drAmountReceipt = BigDecimal.valueOf(totalValue);
		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+drStockLedgerId;

		BigDecimal crAmountReceipt = BigDecimal.valueOf(totalValue);
		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmountReceipt+" where accountdetailsid="+crStockLedgerIdExpense;
		
		
		
		//Pass J.V. : debit the Revenue & credit the Cash
		int drStockLedgerIdIncome = getLedgerAccountId("incomeaccount");
		int crStockLedgerIdIncome = getLedgerAccountId("cashinhandaccountid");
		
		VoucherEntrytransactions transactionsIncome = new VoucherEntrytransactions();
		
		transactionsIncome.setDraccountid(drStockLedgerIdIncome);
		transactionsIncome.setCraccountid(crStockLedgerIdIncome);
		transactionsIncome.setDramount(BigDecimal.valueOf(totalValue));
		transactionsIncome.setCramount(BigDecimal.valueOf(totalValue));
		transactionsIncome.setVouchertype(4);
		transactionsIncome.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
		transactionsIncome.setEntrydate(DateUtil.todaysDate());
		transactionsIncome.setNarration("Towards reversal of Sales of Medicines");
		transactionsIncome.setCancelvoucher("no");
		transactionsIncome.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
		transactionsIncome.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		transactionsIncome.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
		
		BigDecimal drAmountReceiptIncome = BigDecimal.valueOf(totalValue);
		String updateDrAccountIncome ="update Accountdetailsbalance set currentbalance=currentbalance-"+drAmountReceiptIncome+" where accountdetailsid="+drStockLedgerIdIncome;

		BigDecimal crAmountReceiptIncome = BigDecimal.valueOf(totalValue);
		String updateCrAccountIncome ="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmountReceiptIncome+" where accountdetailsid="+crStockLedgerIdIncome;
		
		boolean result = new MessStockMoveDAO().cancelStockMove(messStockMove,transactions,updateDrAccount,updateCrAccount,transactionsIncome,updateDrAccountIncome,updateCrAccountIncome);
		
			request.setAttribute("itemscancelled", result);
	}
	}
}
	
}
