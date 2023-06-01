import { HttpClient } from "@angular/common/http";
import { inject } from "@angular/core";
import { Order } from "./models";

export class PizzaService {

  http = inject(HttpClient)


  // TODO: Task 3
  // You may add any parameters and return any type from placeOrder() method
  // Do not change the method name
  placeOrder(order : Order) {
    console.warn(order)
    return this.http.post<any>('http://localhost:8080/api/order', order);
  }

  // TODO: Task 5
  // You may add any parameters and return any type from getOrders() method
  // Do not change the method name
  getOrders() {
  }

  // TODO: Task 7
  // You may add any parameters and return any type from delivered() method
  // Do not change the method name
  delivered() {
  }

}
