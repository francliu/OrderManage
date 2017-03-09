package online.trading.platform.order.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.CompleteSaleCall;
import com.ebay.sdk.call.GetOrdersCall;
import com.ebay.soap.eBLBaseComponents.OrderIDArrayType;
import com.ebay.soap.eBLBaseComponents.OrderType;
import com.ebay.soap.eBLBaseComponents.PaginationType;

import online.trading.platform.order.common.Constants;
import online.trading.platform.order.model.UpdateTrackingNumRequest;
import online.trading.platform.order.service.OrderService;

public class EbayOrderServiceImpl implements OrderService {

	@Override
	public void downloagOrders() throws Exception {
		// TODO Auto-generated method stub
		
		ApiContext apiContext = getApiContext();
		GetOrdersCall apiCall = new GetOrdersCall(apiContext);
		apiCall.setNumberOfDays(1);	//1 day ago
		
		PaginationType paginationType = new PaginationType();
		paginationType.setPageNumber(1);
		apiCall.setPagination(paginationType);
		
		OrderIDArrayType orderIDArrayType = new OrderIDArrayType();
		String[] idArr = new String[]{"908"};
		orderIDArrayType.setOrderID(idArr);
		apiCall.setOrderIDArray(orderIDArrayType);
		
		List<OrderType> orderList = new ArrayList<OrderType>();
		getOrders(apiCall, orderList);
		
		if(orderList.size()>0){
			System.out.println("orders:"+orderList.size());
		}else{
			System.out.println("no record!");
		}
	}

	@Override
	public void updateTrackingNum(UpdateTrackingNumRequest updateTrackingNumRequest) throws Exception {
		// TODO Auto-generated method stub
		ApiContext apiContext = getApiContext();
		CompleteSaleCall apiCall = new CompleteSaleCall(apiContext);
		//apiCall.setTransactionID(transactionID);
		//apiCall.seti

	}
	
	private void getOrders(GetOrdersCall apiCall, List<OrderType> orderList)throws Exception{
		OrderType[] orders = apiCall.getOrders();
		orderList.addAll(Arrays.asList(orders));
		if(apiCall.getReturnedHasMoreOrders()){
			apiCall.getPagination().setPageNumber(apiCall.getReturnedPageNumber()+1);
			getOrders(apiCall,orderList);
		}
	}
	
	private static ApiContext getApiContext() throws IOException {
		  
	      ApiContext apiContext = new ApiContext();
	      
	      //set Api Token to access eBay Api Server
	      ApiCredential cred = apiContext.getApiCredential();
	      cred.seteBayToken(Constants.EBAY_TOKEN);
	     
	      //set Api Server Url
	      //input = ConsoleUtil.readString("Enter eBay SOAP server URL (e.g., https://api.sandbox.ebay.com/wsapi): ");
	      apiContext.setApiServerUrl(Constants.EBAY_SERVER_URL);
	      
	      return apiContext;
	}



	@Override
	public void downloagOrderItem(String orderId, String sellerId) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
