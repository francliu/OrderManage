package online.trading.platform.order.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.amazonservices.mws.orders._2013_09_01.model.Address;
import com.amazonservices.mws.orders._2013_09_01.model.Order;

import online.trading.platform.order.common.Constants;

public class AmazonOrder implements Serializable {
	
	private static final long serialVersionUID = 368701876008561960L;
	private Integer ID;
	private String SellerId;
	private String OrderId;
	private String FulfillmentChannel;
	private Date LastUpdateDate;
	private String OrderStatus;
	private String OrderChannel;
	private Integer ShippedItemNumber;
	private Integer UnshippedItemNumber;
	private Double OrderAmount;
	private String OrderCurrency;
	private Date PurchaseDate;
	private String SalesChannel;
	private String BuyerEmail;
	private String BuyerName;
	private String BuyerID;
	private String ShippingName;
	private String ShippingStreet1;
	private String ShippingStreet2;
	private String ShippingStreet3;
	private String ShippingCity;
	private String ShippingCountryCode;
	private String ShippingCountryName;
	private String ShippingState;
	private String ShippingPostalCode;
	private String ShippingPhone;
	private String ShipServiceLevel;
	private String ConfirmStatus;
	private String Carrier;
	private String TrackingNumber;
	private String DownOrderItem;
	private String Updater;
	private Date UpdateTime;
	private String DownloadStatus;
	private Date DownloadTime;
	private String ShippingMethod;
	private String MailStatus;
	private String MailStatus2;
	private Date UpdateBuyerIDTime;
	private List<AmazonOrderItem> orderItems;
	
	public AmazonOrder(){};
	
	public AmazonOrder(Order order){
		this.setOrderId(order.getAmazonOrderId());
		this.setSellerId(Constants.AMAZON_SELLER_ID);
		this.setPurchaseDate(order.getPurchaseDate()!=null?order.getPurchaseDate().toGregorianCalendar().getTime():null);
		this.setLastUpdateDate(order.getLastUpdateDate()!=null?order.getLastUpdateDate().toGregorianCalendar().getTime():null);
		this.setOrderStatus(order.getOrderStatus());
		this.setFulfillmentChannel(order.getFulfillmentChannel());
		this.setSalesChannel(order.getSalesChannel());
		this.setOrderChannel(order.getOrderChannel());
		this.setShipServiceLevel(order.getShipServiceLevel());
		if(order.getShippingAddress()!=null){
			Address address = order.getShippingAddress();
			this.setShippingState(address.getStateOrRegion());
			this.setShippingCity(address.getCity());
			this.setShippingCountryCode(address.getCountryCode());
			this.setShippingPostalCode(address.getPostalCode());
			this.setShippingName(address.getName());
			this.setShippingStreet1(address.getAddressLine1());
			this.setShippingStreet1(address.getAddressLine2());
			this.setShippingStreet1(address.getAddressLine3());
			this.setShippingPhone(address.getPhone());
		}
		this.setOrderCurrency(order.getOrderTotal().getCurrencyCode());
		this.setOrderAmount(Double.valueOf(order.getOrderTotal().getAmount()));
		this.setBuyerEmail(order.getBuyerEmail());
		this.setBuyerName(order.getBuyerName());
	}

	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getSellerId() {
		return SellerId;
	}
	public void setSellerId(String sellerId) {
		SellerId = sellerId;
	}
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	public String getFulfillmentChannel() {
		return FulfillmentChannel;
	}
	public void setFulfillmentChannel(String fulfillmentChannel) {
		FulfillmentChannel = fulfillmentChannel;
	}
	public Date getLastUpdateDate() {
		return LastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		LastUpdateDate = lastUpdateDate;
	}
	public String getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
	public String getOrderChannel() {
		return OrderChannel;
	}
	public void setOrderChannel(String orderChannel) {
		OrderChannel = orderChannel;
	}
	public Integer getShippedItemNumber() {
		return ShippedItemNumber;
	}
	public void setShippedItemNumber(Integer shippedItemNumber) {
		ShippedItemNumber = shippedItemNumber;
	}
	public Integer getUnshippedItemNumber() {
		return UnshippedItemNumber;
	}
	public void setUnshippedItemNumber(Integer unshippedItemNumber) {
		UnshippedItemNumber = unshippedItemNumber;
	}
	public Double getOrderAmount() {
		return OrderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		OrderAmount = orderAmount;
	}
	public String getOrderCurrency() {
		return OrderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		OrderCurrency = orderCurrency;
	}
	public Date getPurchaseDate() {
		return PurchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		PurchaseDate = purchaseDate;
	}
	public String getSalesChannel() {
		return SalesChannel;
	}
	public void setSalesChannel(String salesChannel) {
		SalesChannel = salesChannel;
	}
	public String getBuyerEmail() {
		return BuyerEmail;
	}
	public void setBuyerEmail(String buyerEmail) {
		BuyerEmail = buyerEmail;
	}
	public String getBuyerName() {
		return BuyerName;
	}
	public void setBuyerName(String buyerName) {
		BuyerName = buyerName;
	}
	public String getBuyerID() {
		return BuyerID;
	}
	public void setBuyerID(String buyerID) {
		BuyerID = buyerID;
	}
	public String getShippingName() {
		return ShippingName;
	}
	public void setShippingName(String shippingName) {
		ShippingName = shippingName;
	}
	public String getShippingStreet1() {
		return ShippingStreet1;
	}
	public void setShippingStreet1(String shippingStreet1) {
		ShippingStreet1 = shippingStreet1;
	}
	public String getShippingStreet2() {
		return ShippingStreet2;
	}
	public void setShippingStreet2(String shippingStreet2) {
		ShippingStreet2 = shippingStreet2;
	}
	public String getShippingStreet3() {
		return ShippingStreet3;
	}
	public void setShippingStreet3(String shippingStreet3) {
		ShippingStreet3 = shippingStreet3;
	}
	public String getShippingCity() {
		return ShippingCity;
	}
	public void setShippingCity(String shippingCity) {
		ShippingCity = shippingCity;
	}
	public String getShippingCountryCode() {
		return ShippingCountryCode;
	}
	public void setShippingCountryCode(String shippingCountryCode) {
		ShippingCountryCode = shippingCountryCode;
	}
	public String getShippingCountryName() {
		return ShippingCountryName;
	}
	public void setShippingCountryName(String shippingCountryName) {
		ShippingCountryName = shippingCountryName;
	}
	public String getShippingState() {
		return ShippingState;
	}
	public void setShippingState(String shippingState) {
		ShippingState = shippingState;
	}
	public String getShippingPostalCode() {
		return ShippingPostalCode;
	}
	public void setShippingPostalCode(String shippingPostalCode) {
		ShippingPostalCode = shippingPostalCode;
	}
	public String getShippingPhone() {
		return ShippingPhone;
	}
	public void setShippingPhone(String shippingPhone) {
		ShippingPhone = shippingPhone;
	}
	public String getShipServiceLevel() {
		return ShipServiceLevel;
	}
	public void setShipServiceLevel(String shipServiceLevel) {
		ShipServiceLevel = shipServiceLevel;
	}
	public String getConfirmStatus() {
		return ConfirmStatus;
	}
	public void setConfirmStatus(String confirmStatus) {
		ConfirmStatus = confirmStatus;
	}
	public String getCarrier() {
		return Carrier;
	}
	public void setCarrier(String carrier) {
		Carrier = carrier;
	}
	public String getTrackingNumber() {
		return TrackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		TrackingNumber = trackingNumber;
	}
	public String getDownOrderItem() {
		return DownOrderItem;
	}
	public void setDownOrderItem(String downOrderItem) {
		DownOrderItem = downOrderItem;
	}
	public String getUpdater() {
		return Updater;
	}
	public void setUpdater(String updater) {
		Updater = updater;
	}
	public Date getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}
	public String getDownloadStatus() {
		return DownloadStatus;
	}
	public void setDownloadStatus(String downloadStatus) {
		DownloadStatus = downloadStatus;
	}
	public Date getDownloadTime() {
		return DownloadTime;
	}
	public void setDownloadTime(Date downloadTime) {
		DownloadTime = downloadTime;
	}
	public String getShippingMethod() {
		return ShippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		ShippingMethod = shippingMethod;
	}
	public String getMailStatus() {
		return MailStatus;
	}
	public void setMailStatus(String mailStatus) {
		MailStatus = mailStatus;
	}
	public String getMailStatus2() {
		return MailStatus2;
	}
	public void setMailStatus2(String mailStatus2) {
		MailStatus2 = mailStatus2;
	}
	public Date getUpdateBuyerIDTime() {
		return UpdateBuyerIDTime;
	}
	public void setUpdateBuyerIDTime(Date updateBuyerIDTime) {
		UpdateBuyerIDTime = updateBuyerIDTime;
	}
	public List<AmazonOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<AmazonOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
