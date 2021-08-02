package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.account.action.AccountAction;
import com.model.hr.action.HrAction;
import com.model.mess.item.action.MessItemsAction;
import com.model.mess.stockentry.action.MessStockEntryAction;
import com.model.mess.supplier.action.MessSuppliersAction;

/**
 * Servlet implementation class Controller
 */

public class AjaxController extends HttpServlet {
	
	
	
       
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        String process = request.getParameter("process");
        String action = request.getParameter("action");
       System.out.println("AJAX CONTROLLER");
        if (process.equalsIgnoreCase("SubGroupName")) {
        	new AccountAction(request, response).execute(action);
        }else if(process.equalsIgnoreCase("HrProcess")){
        	new HrAction(request, response).execute(action);
        }else if(process.equalsIgnoreCase("stockentry")){
        	new MessStockEntryAction(request, response).execute(action);
        }else if(process.equalsIgnoreCase("SupplierBalance")){
        	new MessSuppliersAction(request, response).execute(action);
        }else if(process.equalsIgnoreCase("CustomerLastPrice")){
        	new MessItemsAction(request, response).execute(action);
        }
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
			}

	/***
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	
}
