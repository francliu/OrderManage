package com.amazonaws.mws.samples;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test {
	public static void main(String... args)throws Exception {
/*		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Header><DocumentVersion>1.01</DocumentVersion><MerchantIdentifier>my store</MerchantIdentifier></Header><MessageType>OrderFulfillment</MessageType><Message><MessageID>1</MessageID><OrderFulfillment><MerchantOrderID>103-6651079-1277000</MerchantOrderID><FulfillmentDate>2017-02-27T14:30:00</FulfillmentDate><FulfillmentData><CarrierCode>FedEx</CarrierCode><ShippingMethod>FedEx Ground</ShippingMethod><ShipperTrackingNumber>785734552268</ShipperTrackingNumber></FulfillmentData></OrderFulfillment></Message></AmazonEnvelope>";
		InputStream ins = new ByteArrayInputStream(xml.toString().getBytes());
		FileInputStream fis = new FileInputStream("d:/amazon.txt");
		System.out.println(computeContentMD5HeaderValue(ins));
		System.out.println(computeContentMD5HeaderValue(fis));
		System.out.println("tBDrjoA4scSQl9ngrtCJew%3D%3D");*/
		//System.out.println(xmlStr());
		
		FileInputStream fis = null;
		InputStream ins = null;
        try {
        	fis = new FileInputStream("d:/amazon2.txt");
        	System.out.println("FileInputStream:"+computeContentMD5HeaderValue(fis));
        	
            ins = new ByteArrayInputStream(xmlStr().getBytes());   
            System.out.println("InputStream:"+computeContentMD5HeaderValue(ins));
        	
        	
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fis!=null)fis.close();
			if(ins!=null)ins.close();
		}
	}
	
	public static String xmlStr(){
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amzn-envelope.xsd\">");
        xml.append("<Header>");
        xml.append("<DocumentVersion>1.0</DocumentVersion>");
        xml.append("<MerchantIdentifier>A1WE4ARVMYN2BL</MerchantIdentifier>");
        xml.append("</Header>");
        xml.append("<MessageType>OrderFulfillment</MessageType>");
        xml.append("<Message>");
        xml.append("<MessageID>1</MessageID>");
        xml.append("<OrderFulfillment>");
        xml.append("<AmazonOrderID>115-6130944-0050619</AmazonOrderID>");
        xml.append("<FulfillmentDate>2017-02-27T14:30:00</FulfillmentDate>");
        xml.append("<FulfillmentData>");
        xml.append("<CarrierCode>FedEx</CarrierCode>");
        xml.append("<ShippingMethod>FedEx Ground</ShippingMethod>");
        xml.append("<ShipperTrackingNumber>785734552268</ShipperTrackingNumber>");
        xml.append("</FulfillmentData>");
        xml.append("<Item>");
        xml.append("<AmazonOrderItemCode>27994655465338</AmazonOrderItemCode>");
        xml.append("<Quantity>1</Quantity>");
        xml.append("</Item>");
        xml.append("</OrderFulfillment>");
        xml.append("</Message>");
        xml.append("</AmazonEnvelope>");
        //System.out.println(xml.toString());
        return xml.toString();
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
    
    
    
    public static String computeContentMD5HeaderValue( InputStream  fis ) 
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
    	    //fis.getChannel().position( 0 );

    	    return md5Content;
    }

}

/**

<?xml version="1.0" encoding="utf-8"?>
<Header>
<DocumentVersion>1.01</DocumentVersion>
<MerchantIdentifier>my store</MerchantIdentifier>
</Header>
<MessageType>OrderFulfillment</MessageType>
<Message>
<MessageID>1</MessageID>
<OrderFulfillment>
<MerchantOrderID>103-6651079-1277000</MerchantOrderID>
<FulfillmentDate>2017-02-27T14:30:00</FulfillmentDate>
<FulfillmentData>
<CarrierCode>FedEx</CarrierCode>
<ShippingMethod>FedEx Ground</ShippingMethod>
<ShipperTrackingNumber>785734552268</ShipperTrackingNumber>
</FulfillmentData>
</OrderFulfillment>
</Message>
</AmazonEnvelope>
*/