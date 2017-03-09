package online.trading.platform.order.model;

import java.io.Serializable;
import java.util.Date;

import com.amazonservices.mws.orders._2013_09_01.model.OrderItem;

public class AmazonOrderItem implements Serializable {

	private static final long serialVersionUID = -7478094070239226075L;
	
	private Integer ID;
	private String OrderId;
	private String ASIN;
	private String OrderItemId;
	private String SellerSKU;
	private String Title;
	private Double Price;
	private String Currency;
	private Double Tax;
	private Integer OrderedQuantity;
	private Integer ShippedQuantity;
	private Double ShippingDiscount;
	private Double ShippingPrice;
	private Double ShippingTax;
	private String Updater;
	private Date UpdateTime;
	private Double GiftWrapPrice;
	private Double GiftWrapTax;
	private Double PromotionDiscount;
	private Double CODFee;
	private Double CODFeeDiscount;
	
	public AmazonOrderItem(){}
	
	public AmazonOrderItem(String orderId,OrderItem orderItem){
		this.setOrderId(orderId);
		this.setASIN(orderItem.getASIN());
		this.setOrderItemId(orderItem.getOrderItemId());
		this.setSellerSKU(orderItem.getSellerSKU());
		this.setTitle(orderItem.getTitle());
		if(orderItem.getItemPrice()!=null)
			this.setPrice(Double.valueOf(orderItem.getItemPrice().getAmount()));
		this.setCurrency(orderItem.getItemPrice().getCurrencyCode());
		if(orderItem.getItemTax()!=null)
			this.setTax(Double.valueOf(orderItem.getItemTax().getAmount()));
		this.setOrderedQuantity(orderItem.getQuantityOrdered());
		this.setShippedQuantity(orderItem.getQuantityShipped());
		if(orderItem.getShippingDiscount()!=null)
			this.setShippingDiscount(Double.valueOf(orderItem.getShippingDiscount().getAmount()));
		if(orderItem.getShippingPrice()!=null)	
			this.setShippingPrice(Double.valueOf(orderItem.getShippingPrice().getAmount()));
		if(orderItem.getShippingTax()!=null)
			this.setShippingTax(Double.valueOf(orderItem.getShippingTax().getAmount()));
		if(orderItem.getGiftWrapPrice()!=null)
			this.setGiftWrapPrice(Double.valueOf(orderItem.getGiftWrapPrice().getAmount()));
		if(orderItem.getGiftWrapTax()!=null)
			this.setGiftWrapTax(Double.valueOf(orderItem.getGiftWrapTax().getAmount()));
		if(orderItem.getPromotionDiscount()!=null)
			this.setPromotionDiscount(Double.valueOf(orderItem.getPromotionDiscount().getAmount()));
		if(orderItem.getCODFee()!=null)
				this.setCODFee(Double.valueOf(orderItem.getCODFee().getAmount()));
		if(orderItem.getCODFeeDiscount()!=null)
			this.setCODFeeDiscount(Double.valueOf(orderItem.getCODFeeDiscount().getAmount()));
	}
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	public String getASIN() {
		return ASIN;
	}
	public void setASIN(String aSIN) {
		ASIN = aSIN;
	}
	public String getOrderItemId() {
		return OrderItemId;
	}
	public void setOrderItemId(String orderItemId) {
		OrderItemId = orderItemId;
	}
	public String getSellerSKU() {
		return SellerSKU;
	}
	public void setSellerSKU(String sellerSKU) {
		SellerSKU = sellerSKU;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public Double getTax() {
		return Tax;
	}
	public void setTax(Double tax) {
		Tax = tax;
	}
	public Integer getOrderedQuantity() {
		return OrderedQuantity;
	}
	public void setOrderedQuantity(Integer orderedQuantity) {
		OrderedQuantity = orderedQuantity;
	}
	public Integer getShippedQuantity() {
		return ShippedQuantity;
	}
	public void setShippedQuantity(Integer shippedQuantity) {
		ShippedQuantity = shippedQuantity;
	}
	public Double getShippingDiscount() {
		return ShippingDiscount;
	}
	public void setShippingDiscount(Double shippingDiscount) {
		ShippingDiscount = shippingDiscount;
	}
	public Double getShippingPrice() {
		return ShippingPrice;
	}
	public void setShippingPrice(Double shippingPrice) {
		ShippingPrice = shippingPrice;
	}
	public Double getShippingTax() {
		return ShippingTax;
	}
	public void setShippingTax(Double shippingTax) {
		ShippingTax = shippingTax;
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
	public Double getGiftWrapPrice() {
		return GiftWrapPrice;
	}
	public void setGiftWrapPrice(Double giftWrapPrice) {
		GiftWrapPrice = giftWrapPrice;
	}
	public Double getGiftWrapTax() {
		return GiftWrapTax;
	}
	public void setGiftWrapTax(Double giftWrapTax) {
		GiftWrapTax = giftWrapTax;
	}
	public Double getPromotionDiscount() {
		return PromotionDiscount;
	}
	public void setPromotionDiscount(Double promotionDiscount) {
		PromotionDiscount = promotionDiscount;
	}
	public Double getCODFee() {
		return CODFee;
	}
	public void setCODFee(Double cODFee) {
		CODFee = cODFee;
	}
	public Double getCODFeeDiscount() {
		return CODFeeDiscount;
	}
	public void setCODFeeDiscount(Double cODFeeDiscount) {
		CODFeeDiscount = cODFeeDiscount;
	}
	
	

}
