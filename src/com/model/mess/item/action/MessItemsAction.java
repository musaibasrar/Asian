/**
 * 
 */
package com.model.mess.item.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.mess.item.dto.MessItems;
import com.model.mess.item.service.MessItemsService;
import com.model.mess.supplier.dao.MessSuppliersDAO;
import com.model.mess.supplier.service.MessSuppliersService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class MessItemsAction {

        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession httpSession;
        String url;

        public MessItemsAction(HttpServletRequest request,
                        HttpServletResponse response) {
                this.request = request;
                this.response = response;
                this.httpSession = request.getSession();
        }

        public String execute(String action) {
                // TODO Auto-generated method stub
                if (action.equalsIgnoreCase("purchaseItems")) {
                        url = purchaseItems();
                }else if (action.equalsIgnoreCase("addItems")) {
                        url = addItems();
                }else if (action.equalsIgnoreCase("updateItems")) {
                    	url = updateItems();
                }else if (action.equalsIgnoreCase("deleteItems")) {
                		url = deleteItems();
                }else if (action.equalsIgnoreCase("viewItems")) {
                    url = viewItems();
                }else if (action.equalsIgnoreCase("savePurchase")) {
                    url = savePurchase();
                }else if (action.equalsIgnoreCase("cancelPurchase")) {
                    url = cancelPurchase();
                }else if (action.equalsIgnoreCase("currentStock")) {
                    url = currentStock();
                }else if (action.equalsIgnoreCase("printStockAvailability")) {
                    url = printStockAvailability();
                }else if (action.equalsIgnoreCase("batchStock")) {
                    url = batchStock();
                }else if (action.equalsIgnoreCase("printBatchStockAvailability")) {
                    url = printBatchStockAvailability();
                }else if (action.equalsIgnoreCase("issuanceStock")) {
                    url = issuanceStock();
                }else if (action.equalsIgnoreCase("generateStockIssuanceReport")) {
                    url = generateStockIssuanceReport();
                }else if (action.equalsIgnoreCase("printStockIssuanceReport")) {
                    url = printStockIssuanceReport();
                }else if (action.equalsIgnoreCase("receiveStock")) {
                    url = receiveStockReport();
                }else if (action.equalsIgnoreCase("generateStockReceivedReport")) {
                    url = generateStockReceivedReport();
                }else if (action.equalsIgnoreCase("printStockReceivedReport")) {
                    url = printStockReceivedReport();
                }  else if (action.equalsIgnoreCase("getCustomerLastPrice")) {
                    getCustomerLastPrice();
                }      
                return url;
        }
        


		private void getCustomerLastPrice() {
			try {
				new MessItemsService(request, response).getCustomerLastPrice();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private String printStockReceivedReport() {
			return "printstockreceivedreport.jsp";
		}

		private String generateStockReceivedReport() {
			new MessItemsService(request, response).generateStockReceivedReport();
			return "stockreceivedreport.jsp";
		}

		private String receiveStockReport() {
			new MessItemsService(request, response).receiveStockReport();
			return "stockreceivedreport.jsp";
		}

		private String printStockIssuanceReport() {
			return "printstockissuancereport.jsp";
		}

		private String generateStockIssuanceReport() {
			new MessItemsService(request, response).generateStockIssuanceReport();
        	//Get Customers
        	new StudentService(request, response).viewAllStudentsParents();
			return "stockissuancereport.jsp";
		}

		private String issuanceStock() {
			new MessItemsService(request, response).getIssuanceStock();
        	//Get Customers
        	new StudentService(request, response).viewAllStudentsParents();
			return "stockissuancereport.jsp";
		}

		private String printBatchStockAvailability() {
			new MessItemsService(request, response).getBatchStock();
			return "printbatchstock.jsp";
		}

		private String batchStock() {
			new MessItemsService(request, response).getBatchStock();
			return "batchstock.jsp";
		}

		private String printStockAvailability() {
			new MessItemsService(request, response).getCurrentStock();
			return "printcurrentstock.jsp";
		}

		private String currentStock() {
			new MessItemsService(request, response).getCurrentStock();
			return "currentstock.jsp";
		}

		private String cancelPurchase() {
			
			new MessItemsService(request, response).cancelPurchase();
			new MessSuppliersService(request, response).viewSuppliersDetails();
        	new MessItemsService(request, response).viewItemDetails();
			new MessItemsService(request, response).getInvoiceDetails();
        	return "purchase.jsp";
		}

		private String savePurchase() {
			new MessItemsService(request, response).savePurchase();
			new MessSuppliersService(request, response).viewSuppliersDetails();
        	new MessItemsService(request, response).viewItemDetails();
			new MessItemsService(request, response).getInvoiceDetails();
        	return "purchase.jsp";
		}

		private String deleteItems() {
        	new MessItemsService(request, response).deleteMultipleItems();
            return viewItems();
		}

		private String updateItems() {
			
			new MessItemsService(request, response).updateItems();
            return viewItems();
		}

		private String viewItems() {
        	return new MessItemsService(request, response).viewItemDetails();
		}

		private String addItems() {
        		 new MessItemsService(request, response).addItemDetails();
        		 return viewItems();
        }

        private String purchaseItems() {
        	new MessSuppliersService(request, response).viewSuppliersDetails();
        	new MessItemsService(request, response).viewItemDetails();
        	new MessItemsService(request, response).getInvoiceDetails();
                return "purchase.jsp";
        }
        
        private String addSuppliers() {
            return "addsuppliers.jsp";
    }


}
