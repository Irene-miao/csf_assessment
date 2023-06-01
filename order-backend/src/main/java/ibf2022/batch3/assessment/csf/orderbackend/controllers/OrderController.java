package ibf2022.batch3.assessment.csf.orderbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.services.OrderingService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
@CrossOrigin(origins="*")
@RequestMapping(path="/api")
public class OrderController {

	@Autowired
private OrderingService orderSvc;

	// TODO: Task 3 - POST /api/order
@PostMapping(path="/order", produces=MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="*")
@ResponseBody
public ResponseEntity<String> postOrder(MultiValueMap<String, String> form) {
	
	String json = null;
	String name = form.getFirst("name");
	String email = form.getFirst("email");
	String pizzaSize= form.getFirst("pizzaSize");
	String base = form.getFirst("base");
	String sauce = form.getFirst("sauce");
	List<String> toppings = form.get("toppings");
	String comments = form.getFirst("comments");

	System.out.println(pizzaSize);
	Integer size = Integer.parseInt(pizzaSize.substring(pizzaSize.length()-7, pizzaSize.length()));
	System.out.println(size);

	boolean thickCrust = base == "thick" ? true : false;


	PizzaOrder order = new PizzaOrder();
	order.setName(name);
	order.setEmail(email);
	order.setSize(size);
	order.setThickCrust(thickCrust);
	order.setSauce(sauce);
	order.setTopplings(toppings);
	order.setComments(comments);


	try {
		PizzaOrder o = orderSvc.placeOrder(order);
		JsonObject obj = Json.createObjectBuilder()
		.add("orderId", o.getOrderId())
		.add("date", o.getDate().toString())
		.add("total", o.getTotal())
		.add("name", o.getName())
		.add("email", o.getEmail())
		.build();

		json = obj.toString();
	}catch (Exception ex) {
		ex.printStackTrace();
		return ResponseEntity.badRequest()
		.body(Json.createObjectBuilder()
		.add("error", ex.getMessage())
		.build().toString());
	}

	return ResponseEntity.status(201)
	.contentType(MediaType.APPLICATION_JSON)
	.body(json);

}

	// TODO: Task 6 - GET /api/orders/<email>

	@GetMapping(path="/order/{email}")
	@CrossOrigin(origins="*")
	@ResponseBody
	public ResponseEntity<String> getOrderByEmail(@PathVariable String email) {
		

		try { 
			List<PizzaOrder> orders = orderSvc.getPendingOrdersByEmail(email);
			
		}catch( Exception ex) { 
			ex.printStackTrace();
		}
	} 
	// TODO: Task 7 - DELETE /api/order/<orderId>

}
