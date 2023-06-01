package ibf2022.batch3.assessment.csf.orderbackend.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins="*")
@RequestMapping(path="/api")
public class OrderController {

	// TODO: Task 3 - POST /api/order
@PostMapping(path="/order", produces=MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="*")
@ResponseBody
public ResponseEntity<String> postOrder(MultiValueMap<String, String> form) {
	

	return null;
}

	// TODO: Task 6 - GET /api/orders/<email>


	// TODO: Task 7 - DELETE /api/order/<orderId>

}
