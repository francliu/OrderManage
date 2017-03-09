package online.trading.platform.order.service;

import online.trading.platform.order.model.UpdateTrackingNumRequest;

public interface OrderService {
	
	public void downloagOrders()throws Exception;
	
	public void downloagOrderItem(String orderId, String sellerId)throws Exception;
	
	public void updateTrackingNum(UpdateTrackingNumRequest updateTrackingNumRequest)throws Exception;

}
