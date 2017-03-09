package online.trading.platform.order.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GeteBayOfficialTimeCall;

import online.trading.platform.order.model.AmazonOrderItem;
import online.trading.platform.order.model.OrderFulfillment;
import online.trading.platform.order.model.UpdateTrackingNumRequest;
import online.trading.platform.order.service.OrderService;
import online.trading.platform.order.service.impl.AmazonOrderServiceImpl;

public class CallTest {

	@Test
	public void updateTrackingNum() {
		OrderService orderService = new AmazonOrderServiceImpl();
		try {
			List<OrderFulfillment> orderFulfillmentList = new ArrayList<OrderFulfillment>();
			OrderFulfillment orderFulfillment = new OrderFulfillment();
			orderFulfillment.setFulfillmentDate("2017-02-27T14:30:00");
			orderFulfillment.setOrderID("112-1667704-4369000");
			orderFulfillment.setShippingMethod("FedEx Ground");
			orderFulfillment.setCarrierCode("FedEx");
			orderFulfillment.setTrackingNumber("785734552268");
			List<AmazonOrderItem> orderItems = new ArrayList<AmazonOrderItem>();
			AmazonOrderItem orderItem = new AmazonOrderItem();
			orderItem.setOrderedQuantity(1);
			orderItem.setOrderItemId("23761140770018");
			orderItems.add(orderItem);
			orderFulfillment.setOrderItems(orderItems);
			orderFulfillmentList.add(orderFulfillment);
			
			UpdateTrackingNumRequest request = new UpdateTrackingNumRequest();
			request.setOrderFulfillmentList(orderFulfillmentList);
			
			orderService.updateTrackingNum(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getOrders(){
		OrderService orderService = new AmazonOrderServiceImpl();
		//OrderService orderService = new EbayOrderServiceImpl();
		try {
			orderService.downloagOrders();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void geteBayOfficialTime(){
	    try {

	        System.out.print("\n");
	        System.out.print("+++++++++++++++++++++++++++++++++++++++\n");
	        System.out.print("+ Welcome to eBay SDK for Java Sample +\n");
	        System.out.print("+  - HelloWorld                   +\n");
	        System.out.print("+++++++++++++++++++++++++++++++++++++++\n");
	        System.out.print("\n");

	        // [Step 1] Initialize eBay ApiContext object
	  	    System.out.println("===== [1] Account Information ====");
	        ApiContext apiContext = getApiContext();
	        
	        // [Step 2] Create call object and execute the call
	        GeteBayOfficialTimeCall apiCall = new GeteBayOfficialTimeCall(apiContext);
	        System.out.println("Begin to call eBay API, please wait ... ");
	        Calendar cal = apiCall.geteBayOfficialTime();
	        System.out.println("End to call eBay API, show call result ...");
	        
	        // [Setp 3] Handle the result returned
	        System.out.println("Official eBay Time : " + cal.getTime().toString());
	    }
	    catch(Exception e) {
	        System.out.println("Fail to get eBay official time.");
	        e.printStackTrace();
	    }
	}
	

	  /**
	   * Populate eBay SDK ApiContext object with data input from user
	   * @return ApiContext object
	   */
	  private static ApiContext getApiContext() throws IOException {
		  
		  String token,url;
	      ApiContext apiContext = new ApiContext();
	      
	      //set Api Token to access eBay Api Server
	      ApiCredential cred = apiContext.getApiCredential();

	      //sandbox config
	      token = "AgAAAA**AQAAAA**aAAAAA**dqiiWA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GiAZiDoQidj6x9nY+seQ**wxcEAA**AAMAAA**Rk5+3EXKHum4P200tqLQsmAsIDrmJJ6jcw28GLj3FOccjU1wBqqvBBS7Y3mZ21wZfAL6imzA16A82aEdB9wb/fgfueP8hY2AcFCfWbX/3crVPawjyT7hTQwb5nBnQTpTA3sVvYNgvv5DEozqEu42jrdOkjybLu9LWJj0X3ae9/x6WrZOk1QrAy0j3yF6Ot9qCbcF3GLrst+5iDV6/GPNpUlMGs9q0PUgeyjn3+ZFvDxYI5mvP8wGbanVwt6fW9LuqyZ7x/MQE4B067ziufKTyF3JmWP8AcpALBN2ZYQbh6Bc4OUuplBFxP2TzkLEabxUU12Jyzqx/oL4S3eMIjPek8/uLKNyyG1MotFaRYsHO0pHvxbl1zWtqEOWkt5oNdAcY8TaQIRvWKfmVK9R6bMCwOpDyL888+ZsGuKflumuoCxivDl6bGvfSdS3VNlWGTpQDnsXbR2AZ/8itU2v2o16iW+Zfl+aeqQ7UYlMbamR9jjHswgG6R0hiROImDmTiozN58PWdZlNSt6PzTaS/Mk2iT8Dmo9ME1gKCm5qbxA3+wD9BJKA+qXPHtfwkmKM+iNIANGGTyXeX4dvztPR/20nnoaKUxrz12ShG8yo75QPGivD0qXsYJvS/tEkSTJJ/EiSelBydVlO/21iZ98RYYJhxqUJUGJIuRAC5yj0EHUWsskZ8o2tB+VCJ7r9DD2vEm+NBK8h+ndZM8EQnxm/xcDuWb9O9BQZNfFGGCBYduaSTF8KlVByvT0ycO/pqueji53A";
	      url = "https://api.sandbox.ebay.com/wsapi";
	      
	      //production config
	      //token = "B35933DE434CDA5481C280F67C78CC562662B35FE3E434F29A3D7DCE6E364536BE4CCB4EACC55E195354BA712064467855B9F7793B1F1B3C524D567D0243B25636DAA774241614B1B38ED75EDB5E374403F1295062554B5B1483D00DF032D018410BA696AACE3B216F37C0F34CC930DD5A70BCF404D9AAADC0D366E0F475BCE4B8E2441FAE18E69C487B16F11C7EA0F20884ECDA9197DCDAECF334AE14821D9D2D6FF5A49701A637A16D70BF8A3E0C0ADDBD3DA1EC8DA986B36FE615A8B6062EA5F4A9C8CDA55F8E7EAC130D4428861AA8C73E14B4F64DAB1CE7001FD39B43C58DD2702CF5F837511DBEDA882D8E2D043C9646E4E0D52D0DE84A087833A1848AFEA442D30DAECF1E291B01842C71ACDC204C288F21432DC703C32973C3978839AC01EC77D42861E6C98DF27583A92B5C7F6DF59E3B12009C95328433B4E69A30DEBF965E40C92030E80DCD9A4C62C48E0FE3A55E19BB5AD272E220FCBDCCB6B90767C97B50BA600286218863845A37AAC0E3BD6711F35EE3E767857E9ABDF4EF7F6A189E01968693EB14B7647F342CAD8D041B916FC55755D280815304D3B2CA54D64952BE343039F47F68239556648DBD5BE107CDEFA858DE4834734DA3375A533A44A5538249D5F71535A22E60C7A73A07E769DAED0B1B2622E5817927A999F4BD6DB3C683A76389CD2B1DCFC552CCE9CB308651E6CD0B7801F116DFD91F93893F7FE10C2F155A9BA0B1F957840FC8FE704609292919AA15E257FD4D4B21E659B6D66C27043434B30832204E0914C6B1DB6FFFA8958ADD5BA6FCF596E554711B55405612BB8CAEF311299745E9333B7182ACF1C41E61EEE2A34A4B516486EA5ECE3C1995172D60109FBFF404A5EDE521C14A9E1E9E7C67EC98FFEBBD18C6FB0C4DA1D2CC870360EC787424F32D39698C095675FDF3DC8F88FE7BAD2C3DBBD37599171A5AE8CF129DED3E4224F8795695271931E2291DC94686684BA141A763F6699FE2540E64B55EB93F483F3002FAA7BFD65D2534D944CE2F1CC9A06B8541413EC602B237F22715F84B2133E83028779A8AD62F2A43B930A9332DC4167EA7F02B831D2929FFCF84C6DBD03BCC5D5F66989D0606C7866FFFBE7AF8F29471788AE756835398292EC6202941C0C554E6DD540E195D6B4A949ECBEC8C7D589E3A7897D76874616708118799CC39D95E7D4181B1C40569E188B54E2585EEB8831E9D28E96368245BDA3A838D2584119636";
	      //url = "https://api.ebay.com/wsapi";
	      
	      cred.seteBayToken(token);
	      apiContext.setApiServerUrl(url);
	      
	      return apiContext;
	  }

}
