import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  private apiUrl = 'http://localhost:8080/fooditems'; // Base URL for the backend API

  constructor(private http: HttpClient) {}

  
  createFoodItem(foodItem: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/create`, foodItem);
  }

 
  updateFoodItem(id: number, foodItem: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/${id}`, foodItem);
  }

  deleteFoodItem(id: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.apiUrl}/delete/${id}`);
  }

 
  getFoodItemById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

 
  getAllFoodItems(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`);
  }

 
  getFoodItemsByRestaurant(restaurantId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/restaurant/${restaurantId}`);
  }
}
