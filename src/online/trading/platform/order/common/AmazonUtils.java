package online.trading.platform.order.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import online.trading.platform.order.model.AmazonOrderItem;
import online.trading.platform.order.model.OrderFulfillment;

public class AmazonUtils {
	
	public static String getOrderFulfillmentMessage(List<OrderFulfillment> orderFulfillmentList){
		StringBuffer xml = new StringBuffer();
		OrderFulfillment orderFulfillment = null;
		List<AmazonOrderItem> orderItems = null;
		AmazonOrderItem amazonOrderItem = null;
		for(int i=0;i<orderFulfillmentList.size();i++){
			orderFulfillment = orderFulfillmentList.get(i);
	        xml.append("<Message>");
	        xml.append("<MessageID>"+(i+1)+"</MessageID>");
	        xml.append("<OrderFulfillment>");
	        xml.append("<AmazonOrderID>"+orderFulfillment.getOrderID()+"</AmazonOrderID>");
	        xml.append("<FulfillmentDate>"+orderFulfillment.getFulfillmentDate()+"</FulfillmentDate>");
	        xml.append("<FulfillmentData>");
	        xml.append("<CarrierCode>"+orderFulfillment.getCarrierCode()+"</CarrierCode>");
	        xml.append("<ShippingMethod>"+orderFulfillment.getShippingMethod()+"</ShippingMethod>");
	        xml.append("<ShipperTrackingNumber>"+orderFulfillment.getTrackingNumber()+"</ShipperTrackingNumber>");
	        xml.append("</FulfillmentData>");
	        orderItems = orderFulfillment.getOrderItems();
	        for(int j=0;j<orderItems.size();j++){
	        	amazonOrderItem = orderItems.get(i);
		        xml.append("<Item>");
		        xml.append("<AmazonOrderItemCode>"+amazonOrderItem.getOrderItemId()+"</AmazonOrderItemCode>");
		        xml.append("<Quantity>"+amazonOrderItem.getOrderedQuantity()+"</Quantity>");
		        xml.append("</Item>");
	        }
	        xml.append("</OrderFulfillment>");
	        xml.append("</Message>");
		}
		
		return xml.toString();
	}
	
	public static boolean saveOrderFulfillmentFile(String fileName, String fileContent){
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		boolean flag = true; 
        try {
            File file = new File(fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(fileContent.toString().getBytes());  
		} catch (Exception e) {
			flag = false;
		}finally{
			try {
				bos.flush();
				fos.close();
				bos.close();
			} catch (IOException e) {
			}
		}
        return flag;
	}
	
    public static String computeContentMD5HeaderValue( FileInputStream fis ) 
    	    throws IOException, NoSuchAlgorithmException {

    	    DigestInputStream dis = new DigestInputStream( fis,
    	        MessageDigest.getInstance( "MD5" ));

    	    byte[] buffer = new byte[8192];
    	    while( dis.read( buffer ) > 0 );

    	    String md5Content = new String(
    	        org.apache.commons.codec.binary.Base64.encodeBase64(
    	            dis.getMessageDigest().digest()) ); 

    	    // Effectively resets the stream to be beginning of the file
    	    // via a FileChannel.
    	    fis.getChannel().position( 0 );

    	    return md5Content;
    }

}
