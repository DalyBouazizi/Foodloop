import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = 'http://localhost:8080/orders';

  constructor(private http: HttpClient) {}

  getOrdersByRestaurantId(restaurantId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/restaurant/${restaurantId}`);
  }

  // API call to update the order status
  updateOrderStatus(orderId: number, status: string): Observable<any> {
    const url = `${this.apiUrl}/updateStatus/${orderId}`;
    return this.http.put(url, status, {
      headers: { 'Content-Type': 'application/json' }
    });
  }
}
