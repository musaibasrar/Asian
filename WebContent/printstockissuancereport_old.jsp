<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<title>Bill/Invoice</title>
<head>
<style type="text/css">
<!--
.headerText {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: black;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: center;
}

.headerTextLeft {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: black;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: left;
}

.dataTextBold {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.dataTextBoldLeft {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: left;
}


.dataTextBoldRight {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: right;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 32px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 18px;
	letter-spacing: normal;
	text-align: center;
}

.dataText {
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}
-->
</style>


<!-- <style type="text/css">

        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
            	margin-top: 0px;
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              size: auto;   /* auto is the current printer page size */
           	  margin: 0mm;  /* this affects the margin in the printer settings */ 
            
        }

        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style> -->
    
    <style type="text/css">

	

</head>




        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              
             margin-left:  0cm;
             margin-right: 0cm;
             margin-bottom: 0cm;
             margin-top: 0cm;
        }

        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style>




<body style="text-align: center" class="bodymargin">
	<form method="post" class="bodymargin">
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="left">
				<img src="images/logo.png" width="200" height="50"/>
				</td> 
				<td style="width: 100%;" align="left">
				<label class="dataTextBoldCenter">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Asian Agencies </label><br>
				<label class="addressLine" style="padding-left: 50px;">Railway Station Road, Bidar – 585401
				 </label>
				</td>
			</tr>
</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>
                    <td colspan="4" ></td>
                </tr>
            </TABLE>

		<table>
		
			<tr>
			<td></td>
			
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">${issuedtoselected}
				</td>
				
				<td class="dataTextBoldRight" ><c:out
						value="${transactionfromdateselected}" />
				</td>
			
				<%-- <td class="dataTextBoldLeft" style="width: 30%">Admission No:<c:out value="${student.admissionnumber}" /> 
				</td>--%>
				

				

<%-- 				<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;Receipt No:<c:out
						value="${recieptinfo.branchreceiptnumber}" />
				</td> --%>

			</tr>
			<tr>
			<td></td>
			</tr>
			<%-- <tr>
			<td class="dataTextBoldLeft" style="width: 50%">Fathers
					Name: <c:out value="${parents.fathersname}" />
				</td>
			
				<td class="dataTextBoldLeft" style="width: 30%">Class:
				
				<c:set var="classstudying" value="${student.classstudying}"/>  
				<c:set var = "string2" value = "${fn:replace(classstudying, '--', '')}" />
				 ${string2}
				</td>

			<td class="dataTextBoldLeft" >&nbsp;&nbsp;&nbsp;Date: <c:out
						value="${transactionfromdateselected}" />
				</td>
			</tr> --%>

			<tr>
			<td></td>
			
			</tr>
			<tr>
			<td></td>
			
			</tr>

		</table>
		<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>

		<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">


			<tr>

				<td class="headerText" style="font-weight: bold">Sl.No.</td>
				<td class="headerText" style="font-weight: bold">Particulars</td>
				<td class="headerText" style="font-weight: bold">Unit Price</td>
				<td class="headerText" style="font-weight: bold">Qty.</td>
				<td class="headerText" style="font-weight: bold">Total Price</td>

			</tr>
			
			<!-- <tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr> -->


			<c:forEach items="${stockissuancelist}" var="stockissuancelist" varStatus="status">


						<tr>
								<td class="dataText" style="font-size: 9px;">${status.index+1}</td>
								<%-- <td class="datatd" style="font-size: 9px;"><c:out value="${stockissuancelist.transactiondate}" /></td>
								<td class="datatd" style="font-size: 9px;"><c:out value="${stockissuancelist.issuedto}" /></td>
								<td class="datatd" style="font-size: 9px;"><c:out value="${stockissuancelist.purpose}" /></td> --%>
								<td class="dataText" style="font-size: 9px;"><c:out value="${stockissuancelist.itemname}" /></td>
								<%-- <td class="datatd" style="font-size: 9px;"><c:out value="${stockissuancelist.unitofmeasure}" /></td> --%>
								<td class="dataText" style="font-size: 9px;"><c:out value="${stockissuancelist.itemunitprice}" /></td>
								<td class="dataText" style="font-size: 9px;"><c:out value="${stockissuancelist.quantity}" /></td>
								<td class="dataText" style="font-size: 9px;">
								 <c:set var="itemTotal" value="${itemTotal + stockissuancelist.itemunitprice * stockissuancelist.quantity}" />
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${stockissuancelist.itemunitprice * stockissuancelist.quantity}" />
								</td>
						</tr>	
						
					</c:forEach>
			<tr>
			<td></td>
			<td></td>
			</tr>
			
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			</tr>
			
			<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
			
			
			<tr>
				<!-- <td>&nbsp;</td> -->
				<td class="headerText" style="font-weight: bold;" >
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Total</td>
				<td class="headerText">Rs. <c:out
						value="${itemTotal}" /></td>
			</tr>
			

<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>


<tr>

<td >In Words: Rupees <c:out value="${grandTotal}" /></td>
<td></td>
</tr>

<!-- <tr>
<td align="left">Note: Fees once deposited will not be refunded under any Circumstances</td>
</tr> -->

<tr>
<td><br><br></td>
</tr>
<tr>

<td align="Center"><br>Accountant</td>

<td align="left">Cashier</td>

</tr>
		</TABLE>


	</form>
</body>
</html>
