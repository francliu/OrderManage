package online.trading.platform.order.service.impl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.IdList;
import com.amazonaws.mws.model.SubmitFeedRequest;
import com.amazonaws.mws.model.SubmitFeedResponse;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersAsyncClient;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersConfig;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersException;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsByNextTokenRequest;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsByNextTokenResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsRequest;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsResult;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersByNextTokenRequest;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersByNextTokenResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersRequest;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersResult;
import com.amazonservices.mws.orders._2013_09_01.model.MWSResponse;
import com.amazonservices.mws.orders._2013_09_01.model.Order;
import com.amazonservices.mws.orders._2013_09_01.model.OrderItem;
import com.amazonservices.mws.orders._2013_09_01.model.ResponseHeaderMetadata;

import online.trading.platform.order.common.AmazonUtils;
import online.trading.platform.order.common.Constants;
import online.trading.platform.order.model.AmazonOrder;
import online.trading.platform.order.model.AmazonOrderItem;
import online.trading.platform.order.model.UpdateTrackingNumRequest;
import online.trading.platform.order.service.OrderService;

public class AmazonOrderServiceImpl implements OrderService {
	
	private static Logger log = Logger.getLogger(AmazonOrderServiceImpl.class.getName());
	private static MarketplaceWebServiceOrdersAsyncClient wsOrderclient = null;
	private static MarketplaceWebServiceClient wsClient = null;

	@Override
	public void downloagOrders()throws Exception {
		// TODO Auto-generated method stub

        // init time for request
        GregorianCalendar gcal =new GregorianCalendar();
        //gcal.add(GregorianCalendar.DATE, -1);
        //XMLGregorianCalendar createdBefore = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        gcal.add(GregorianCalendar.DATE, -2);
        XMLGregorianCalendar createdAfter = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

        // init orderStatus
        List<String> orderStatus = new ArrayList<String>();
        orderStatus.add("Unshipped");			//Unshipped - Payment has been authorized and order is ready for shipment, but no items in the order have been shipped.
        orderStatus.add("PartiallyShipped");	//Unshipped and PartiallyShipped should be used together when filtering by OrderStatus
        // init marketplaceId
        List<String> marketplaceId = new ArrayList<String>();
        marketplaceId.add(Constants.AMAZON_MARKETPLACE_ID_US);	//US marketplaceId

        // Create a request.
        ListOrdersRequest request = new ListOrdersRequest();
        request.setSellerId(Constants.AMAZON_SELLER_ID);
        request.setMWSAuthToken(Constants.AMAZON_AUTH_TOKEN);
        request.setCreatedAfter(createdAfter);
        //request.setCreatedBefore(createdBefore);
        request.setOrderStatus(orderStatus);
        request.setMarketplaceId(marketplaceId);
        //request.setMaxResultsPerPage(5);
        
        // 	Make the call.
        ListOrdersResponse listOrdersResponse = null;
        try{
        	// Call the service.
	        listOrdersResponse = getAsyncClient().listOrders(request);
	        amazonWebLog(listOrdersResponse);
        }catch (MarketplaceWebServiceOrdersException ex) {
        	amazonWebServiceException(ex);
            return;
        }
        
        // handle response
        ListOrdersResult listOrdersResult = listOrdersResponse.getListOrdersResult();
        List<Order> orders = listOrdersResult.getOrders();
        if(listOrdersResult.isSetNextToken()){
        	getOrdersByNextToken(listOrdersResult.getNextToken(),orders);
        }
        
        //get local AmazonOrder list
        List<AmazonOrder> amazonOrders = null;
        if(orders!=null){
        	amazonOrders = new ArrayList<AmazonOrder>();
        	for(int i=0;i<orders.size();i++){
        		log.info((i+1)+":"+orders.get(i).getAmazonOrderId());
        		amazonOrders.add(new AmazonOrder(orders.get(i)));
        	}
        }else{
        	log.info("orders is empty");
        }
        
        //save local order to db
        log.info("downloaded:"+amazonOrders.size());
	}
	
	public void downloagOrderItem(String orderId, String sellerId)throws Exception {
		
        // Create a request.
        ListOrderItemsRequest request = new ListOrderItemsRequest();
        request.setSellerId(sellerId);
        request.setMWSAuthToken(Constants.AMAZON_AUTH_TOKEN);
        request.setAmazonOrderId(orderId);

        ListOrderItemsResponse listOrderItemsResponse = null;
        try {
            // Call the service.
            listOrderItemsResponse = getAsyncClient().listOrderItems(request);
            amazonWebLog(listOrderItemsResponse);
        } catch (MarketplaceWebServiceOrdersException ex) {
        	amazonWebServiceException(ex);
            return;
        }
        
        ListOrderItemsResult listOrderItemsResult = listOrderItemsResponse.getListOrderItemsResult();
        List<OrderItem> orderItems = listOrderItemsResult.getOrderItems();
        if(listOrderItemsResult.isSetNextToken()){
        	getOrderItemsByNextToken(listOrderItemsResult.getNextToken(), orderItems);
        }
        
        //get local AmazonOrderItem list
        List<AmazonOrderItem> amazonOrderItems = null;
        if(orderItems!=null){
        	amazonOrderItems = new ArrayList<AmazonOrderItem>();
        	for(int i=0;i<orderItems.size();i++){
        		log.info((i+1)+":"+orderItems.get(i).getOrderItemId());
        		amazonOrderItems.add(new AmazonOrderItem(orderId,orderItems.get(i)));
        	}
        }else{
        	log.info("orderItems is empty");
        }
        
        log.info("downloaded:"+amazonOrderItems.size());
        
	}

	@Override
	public void updateTrackingNum(UpdateTrackingNumRequest updateTrackingNumRequest) throws Exception {
		// TODO Auto-generated method stub

        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amzn-envelope.xsd\">");
        xml.append("<Header>");
        xml.append("<DocumentVersion>1.0</DocumentVersion>");
        xml.append("<MerchantIdentifier>"+Constants.AMAZON_SELLER_ID+"</MerchantIdentifier>");
        xml.append("</Header>");
        xml.append("<MessageType>OrderFulfillment</MessageType>");
        xml.append(AmazonUtils.getOrderFulfillmentMessage(updateTrackingNumRequest.getOrderFulfillmentList()));
        xml.append("</AmazonEnvelope>");

        //save submitFeed content
		String fileName = Constants.AMAZON_UPLOAD_FILE_PATH+System.currentTimeMillis()+".txt";
		if(!AmazonUtils.saveOrderFulfillmentFile(fileName, xml.toString())){
			log.info("save file found exception");
			return;
		}
        
		//submitFee request
        IdList marketplaces = new IdList(Arrays.asList(Constants.AMAZON_MARKETPLACE_ID_US));
        SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(Constants.AMAZON_SELLER_ID);
        //request.setMWSAuthToken(Constants.AMAZON_AUTH_TOKEN);
        request.setMarketplaceIdList(marketplaces);
        request.setFeedType(Constants.AMAZON_FEED_TYPE_POFD);
        FileInputStream fis = null;
        try {
        	fis = new FileInputStream(fileName);
        	request.setFeedContent(fis);
			request.setContentMD5(AmazonUtils.computeContentMD5HeaderValue(fis));
			
        	SubmitFeedResponse submitFeedResponse = getWSClient().submitFeed(request);
        	com.amazonaws.mws.model.ResponseHeaderMetadata rhmd = submitFeedResponse.getResponseHeaderMetadata();
            log.info("Response-RequestId: "+rhmd.getRequestId());
            log.info("Response-Timestamp: "+rhmd.getTimestamp());
            log.info("Response-XML: "+submitFeedResponse.toXML());
        } catch (MarketplaceWebServiceException ex) {
            log.error("Caught Exception: " + ex.getMessage());
            log.error("Message: "+ex.getMessage()+",StatusCode: "+ex.getStatusCode()+",ErrorCode: "+ex.getErrorCode()+",ErrorType: "+ex.getErrorType());
		} catch (Exception e) {
			log.error("submitFee found exception",e);
		} finally{
			if(fis!=null)fis.close();
		}
	}
	
	private void getOrdersByNextToken(String token, List<Order> orders){
        ListOrdersByNextTokenRequest request = new ListOrdersByNextTokenRequest();
        request.setSellerId(Constants.AMAZON_SELLER_ID);
        request.setMWSAuthToken(Constants.AMAZON_AUTH_TOKEN);
        request.setNextToken(token);
        
        ListOrdersByNextTokenResponse listOrdersByNextTokenResponse = null;
        try{
        	listOrdersByNextTokenResponse = getAsyncClient().listOrdersByNextToken(request);
            amazonWebLog(listOrdersByNextTokenResponse);
        	
	        orders.addAll(listOrdersByNextTokenResponse.getListOrdersByNextTokenResult().getOrders());  
	        if(listOrdersByNextTokenResponse.getListOrdersByNextTokenResult().isSetNextToken()){
	        	getOrdersByNextToken(listOrdersByNextTokenResponse.getListOrdersByNextTokenResult().getNextToken(), orders);
	        }
        } catch (MarketplaceWebServiceOrdersException ex) {
            amazonWebServiceException(ex);
        }
	}
	
	private void getOrderItemsByNextToken(String token, List<OrderItem> orderItems){
        ListOrderItemsByNextTokenRequest request = new ListOrderItemsByNextTokenRequest();
        request.setSellerId(Constants.AMAZON_SELLER_ID);
        request.setMWSAuthToken(Constants.AMAZON_AUTH_TOKEN);
        request.setNextToken(token);
        
        ListOrderItemsByNextTokenResponse listOrderItemsByNextTokenResponse = null;
        try{
        	listOrderItemsByNextTokenResponse = getAsyncClient().listOrderItemsByNextToken(request);
            amazonWebLog(listOrderItemsByNextTokenResponse);
        	
            orderItems.addAll(listOrderItemsByNextTokenResponse.getListOrderItemsByNextTokenResult().getOrderItems());  
	        if(listOrderItemsByNextTokenResponse.getListOrderItemsByNextTokenResult().isSetNextToken()){
	        	getOrderItemsByNextToken(listOrderItemsByNextTokenResponse.getListOrderItemsByNextTokenResult().getNextToken(), orderItems);
	        }
        } catch (MarketplaceWebServiceOrdersException ex) {
            amazonWebServiceException(ex);
        }
	}
	
	private void amazonWebLog(MWSResponse mwsResponse){
        ResponseHeaderMetadata rhmd = mwsResponse.getResponseHeaderMetadata();
        // We recommend logging every the request id and timestamp of every call.
        log.info("Response-RequestId: "+rhmd.getRequestId());
        log.info("Response-Timestamp: "+rhmd.getTimestamp());
        log.info("Response-XML: "+mwsResponse.toXML());
	}
	
	private void amazonWebServiceException(MarketplaceWebServiceOrdersException ex){
    	log.error("Service Exception:");
        ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
        if(rhmd != null) {
        	log.error("Response-RequestId: "+rhmd.getRequestId());
        	log.error("Response-Timestamp: "+rhmd.getTimestamp());
        }
        log.error("Message: "+ex.getMessage()+",StatusCode: "+ex.getStatusCode()+",ErrorCode: "+ex.getErrorCode()+",ErrorType: "+ex.getErrorType());
	}
	
    private synchronized MarketplaceWebServiceOrdersAsyncClient getAsyncClient() {
        if (wsOrderclient==null) {
            MarketplaceWebServiceOrdersConfig config = new MarketplaceWebServiceOrdersConfig();
            config.setServiceURL(Constants.AMAZON_SERVER_URL);
            // Set other client connection configurations here.
            wsOrderclient = new MarketplaceWebServiceOrdersAsyncClient(Constants.AMAZON_ACCESS_KEY, Constants.AMAZON_SECRET_KEY, Constants.AMAZON_APP_NAME, Constants.AMAZON_APP_VERSION, config, null);
        }
        return wsOrderclient;
    }
    
    private synchronized MarketplaceWebServiceClient getWSClient() {
        if (wsClient==null) {
            MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
            config.setServiceURL(Constants.AMAZON_SERVER_URL);
            // Set other client connection configurations here.
            wsClient = new MarketplaceWebServiceClient(Constants.AMAZON_ACCESS_KEY, Constants.AMAZON_SECRET_KEY, Constants.AMAZON_APP_NAME, Constants.AMAZON_APP_VERSION, config);
        }
        return wsClient;
    }

}
