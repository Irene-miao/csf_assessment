package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class PendingOrdersRepository {


	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private static final String PENDING_ORDER = "pending_order";

	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	public void add(PizzaOrder order) {

		JsonObject obj = Json.createObjectBuilder()
		.add("orderId", )
		.add("date",)
		.add("total", )
		.add("name", order.getName())
		.add("email", order.getEmail())
		.build();

		String json = obj.toString();

		redisTemplate.opsForHash().put(PENDING_ORDER,  , json);
	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	public boolean delete(String orderId) {
		return false;
	}

}
