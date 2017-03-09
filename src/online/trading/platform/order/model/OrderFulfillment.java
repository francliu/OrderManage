package online.trading.platform.order.model;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class OrderFulfillment implements Serializable {

	private static final long serialVersionUID = -8122466008763527470L;
	
	private String OrderID;				//Amazon's unique identifier for an order, which identifies the entire order regardless of the number of individual items in the order
	private String TrackingNumber;		//Tracking number for the shipment
	private String CarrierCode;			//Shipping carrier code
	private String ShippingMethod;		//Shipping method used to deliver the item
	private String FulfillmentDate;		//The date the item was actually shipped or picked up, depending on the fulfillment method specified in the order
										//FulfillmentDate format
										//2006-12-11T09:50:00  		local time zome applies
										//2006-12-11T09:50:00+02:00  	GMT time zone applies
	private List<AmazonOrderItem> orderItems;
	
	public String getTrackingNumber() {
		return TrackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		TrackingNumber = trackingNumber;
	}
	public String getShippingMethod() {
		return ShippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		ShippingMethod = shippingMethod;
	}
	public String getFulfillmentDate() {
		return FulfillmentDate;
	}
	public void setFulfillmentDate(String fulfillmentDate) {
		FulfillmentDate = fulfillmentDate;
	}
	public String getCarrierCode() {
		return CarrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		CarrierCode = carrierCode;
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public List<AmazonOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<AmazonOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public boolean validate(){
		if(StringUtils.isEmpty(OrderID) || StringUtils.isEmpty(TrackingNumber) || StringUtils.isEmpty(CarrierCode) || StringUtils.isEmpty(ShippingMethod) 
				|| StringUtils.isEmpty(FulfillmentDate) )return false;
		if(orderItems==null || orderItems.size()==0)return false;
		return true;
	}

}
