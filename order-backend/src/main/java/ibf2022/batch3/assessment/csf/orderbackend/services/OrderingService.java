package ibf2022.batch3.assessment.csf.orderbackend.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.OrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.PendingOrdersRepository;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class OrderingService {

	
	@Value("${spring.data.api.url}")
    private String url;

	@Autowired
	private OrdersRepository ordersRepo;

	@Autowired
	private PendingOrdersRepository pendingOrdersRepo;
	
	// TODO: Task 5
	// WARNING: DO NOT CHANGE THE METHOD'S SIGNATURE
	public PizzaOrder placeOrder(PizzaOrder order) throws OrderException {

		String orderId = null;
		 String price = null;
		 String date = null;

		RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;
       
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
       
		JsonArrayBuilder arr = Json.createArrayBuilder();
		for (String t : order.getTopplings()){
			arr.add(t);
		}
		JsonObject obj = Json.createObjectBuilder()
		.add("name", order.getName())
		.add("email", order.getEmail())
		.add("sauce", order.getSauce())
		.add("size", order.getSize())
		.add("thickCrust", order.getThickCrust())
		.add("comments", order.getComments())
		.add("toppings", arr )
		.build();

		String json = obj.toString();
        HttpEntity<String> requestEntity = new HttpEntity<>( json, headers);
        resp = template.exchange(url, HttpMethod.POST, requestEntity, String.class);
        if (null == resp) {
            JsonObject error = Json.createObjectBuilder()
       .add("message", "order posting failure")
       .build();
       
	   System.out.println(error.toString());
       
        }
      
    
	 System.out.println(resp.getBody());

	 String data = resp.getBody().toString();
		String[] array = data.split(",");

		for (String a : array) {
			orderId = a;
			date = a;
			price = a;
		}

		
		order.setOrderId(orderId);
		order.setDate();
		order.setTotal(Float.parseFloat(price));

		 ordersRepo.add(order);
		 pendingOrdersRepo.add(order);

		 return order;
	}

	// For Task 6
	// WARNING: Do not change the method's signature or its implemenation
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {
		return ordersRepo.getPendingOrdersByEmail(email);
	}

	// For Task 7
	// WARNING: Do not change the method's signature or its implemenation
	public boolean markOrderDelivered(String orderId) {
		return ordersRepo.markOrderDelivered(orderId) && pendingOrdersRepo.delete(orderId);
	}


}
