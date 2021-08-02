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




<body style="text-align: center" class="bodymargin" onload="window.print();">
	<form method="post" class="bodymargin">
		<div style="page-break-after: always; ">
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
			
				<td class="dataTextBoldLeft" style="padding-left: 30px;">Customer Name: ${billdetailscustomername}
				</td>
				
				<td class="dataTextBoldRight" style="padding-left: 350px;">Date: <c:out
						value="${billdetailstransactiondate}" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bill No: ${billno}
				</td>
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
			style="border-collapse: collapse;">


			<tr>

				<td class="headerText" style="font-weight: bold">Sl.No.</td>
				<td class="headerText" style="font-weight: bold">Particulars</td>
				<td class="headerText" style="font-weight: bold">Unit Price</td>
				<td class="headerText" style="font-weight: bold">Qty.</td>
				<td class="headerText" style="font-weight: bold">Total Price</td>

			</tr>
			
			<c:forEach items="${billdetails}" var="billdetails" varStatus="status">


						<tr>
								<td class="dataText" style="font-size: 9px;">${status.index+1}</td>
								<td class="dataText" style="font-size: 9px;">${billdetails.externalid}</td>
								<td class="dataText" style="font-size: 9px;"><c:out value="${billdetails.purpose}" />
								<fmt:parseNumber var = "salesrate" type = "number" value = "${billdetails.purpose}" />
								</td>
								<td class="dataText" style="font-size: 9px;"><c:out value="${billdetails.quantity}" /></td>
								<td class="dataText" style="font-size: 9px;">
								 <c:set var="itemTotal" value="${itemTotal + salesrate * billdetails.quantity}" />
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${salesrate * billdetails.quantity}" />
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
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>
		
			
			<tr>
				<!-- <td>&nbsp;</td> -->
				<td colspan="4" class="headerText" style="font-weight: bold;text-align: right;" >
				Total</td>
				<td class="headerText">Rs. <c:out
						value="${itemTotal}" /></td>
			</tr>
			

<tr>
			
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			<td><hr width="100%"></td>
			</tr>

<%-- 
<tr>

<td >In Words: Rupees <c:out value="${billdetailstotaltotal}" /></td>
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

</tr>  --%>
		</TABLE>

	<table width="100%">
		<tr>
			
			<td >In Words: Rupees <c:out value="${billdetailstotaltotal}" /></td>
			
			</tr>
			
			<!-- <tr>
			<td align="left">Note: Fees once deposited will not be refunded under any Circumstances</td>
			</tr> -->
			
			<tr>
			<td><br><br></td>
			</tr>
			<tr>
			
			<td align="Center"></td>
			
			<td align="left">Cashier</td>
			
			</tr> 
	
	</table>
</div>
	</form>
</body>
</html>
