package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class OrdersRepository {

	@Autowired
	private MongoTemplate template;

	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//  db.orders.insert({ })
	public void add(PizzaOrder order) {

		String crust = order.getThickCrust() ? "thick" : "thin";

		JsonObject obj = Json.createObjectBuilder()
		.add("_id", order.getOrderId())
		.add("date", order.getDate())
		.add("total", order.getTotal() )
		.add("name", order.getName())
		.add("email", order.getEmail())
		.add("sauce", order.getSauce())
		.add("size", order.getSize())
		.add("crust", crust)
		.add("comments", order.getComments())
		.add("toppings", order.getTopplings())
		.build();

		String json = obj.toString();
		Document d = Document.parse(json);

		if (!template.getCollectionNames().contains("orders")) {
			template.createCollection("orders");
		}

		Document document = template.insert(document, "orders");
		System.out.println(document);

	}
	
	// TODO: Task 6
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for getPendingOrdersByEmail()
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {

		List<PizzaOrder> list = new ArrayList<>();

		Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        List<Document> docs = template.find(query, Document.class, "orders");
        System.out.printf("docs: %s\n", docs);
        PizzaOrder o = new PizzaOrder();
        for (Document doc : docs){
            o.setOrderId(doc.getString("orderId"));
			o.setDate((doc.getString("date")));
			o.setTotal(Float.parseFloat(doc.getString("total")));
			o.setName(doc.getString("name"));
			o.setEmail(doc.getString("email"));

			list.add(o);
        }

        return list;
	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for markOrderDelivered()
	public boolean markOrderDelivered(String orderId) {

		return false;
	}


}
