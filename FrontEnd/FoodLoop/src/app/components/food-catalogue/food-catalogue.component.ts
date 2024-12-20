import { Component } from '@angular/core';
import { FoodService } from '../../services/food.service';
import { OrderService } from '../../services/order.service';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-food-catalogue',
  templateUrl: './food-catalogue.component.html',
  styleUrl: './food-catalogue.component.css'
})
export class FoodCatalogueComponent {

  foodItems: any[] = []; // Store food items from the service

  constructor(private foodService: FoodService,  private orderService: OrderService,
    private storageService: StorageService) {}

  ngOnInit(): void {
    this.loadFoodItems();
  }

  loadFoodItems() {
    this.foodService.getAllFoodItems().subscribe((items: any[]) => {
      this.foodItems = items;
    });
  }

  
  orderNow(food: any) {
    const buyerId = localStorage.getItem('userId'); // Retrieve buyer ID from local storage
    const restaurantId = food.restaurant.id; // Get restaurant ID from the food object
  console.log('Buyer ID:', buyerId);
    console.log('Restaurant ID:', restaurantId);
    if (buyerId && restaurantId) {
      // Prepare OrderItems object
      const orderItems = [
        {
          foodItem: { id: food.id },
          quantity: 1, // Default quantity
          price: food.price
        }
      ];
  
      this.orderService.createOrder(Number(buyerId), restaurantId, orderItems).subscribe(
        (response) => {
          console.log('Order created successfully:', response);
          alert('Order placed successfully!');
        },
        (error) => {
          console.error('Error creating order:', error);
          alert('Failed to place order. Please try again.');
        }
      );
    } else {
      alert('Buyer ID or Restaurant ID is missing!');
    }
  }
  

}
