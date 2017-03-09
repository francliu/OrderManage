package online.trading.platform.order.model;

import java.util.ArrayList;
import java.util.List;

public class UpdateTrackingNumRequest {
	
	List<OrderFulfillment> orderFulfillmentList = new ArrayList<OrderFulfillment>();

	public List<OrderFulfillment> getOrderFulfillmentList() {
		return orderFulfillmentList;
	}

	public void setOrderFulfillmentList(List<OrderFulfillment> orderFulfillmentList) {
		this.orderFulfillmentList = orderFulfillmentList;
	}
	
	

}
