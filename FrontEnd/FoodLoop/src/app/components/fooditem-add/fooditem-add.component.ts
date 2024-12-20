import { Component } from '@angular/core';
import { FoodService } from '../../services/food.service';
import { Router } from '@angular/router';
import { StorageService } from '../../services/storage.service';


@Component({
  selector: 'app-fooditem-add',
  templateUrl: './fooditem-add.component.html',
  styleUrl: './fooditem-add.component.css'
})
export class FooditemADDComponent {
  foodItem: any = {
    name: '',
    description: '',
    price: 0,
    quantity: 0,
    expirationDate: '',
    picture: ''
  };

  constructor(private foodService: FoodService,private router: Router,private storageService : StorageService ) {}

  onSubmit(): void {

// Get the restaurant ID from local storage
const restaurantId = this.storageService.getItem('userId');
if (!restaurantId) {
  alert('Error: Restaurant ID not found in local storage.');
  return;
}

// Add the restaurant object to the food item
const foodItemWithRestaurant = {
  ...this.foodItem,
  restaurant: { id: Number(restaurantId) }
};

console.log('Adding food item:', foodItemWithRestaurant);

    this.foodService.createFoodItem(foodItemWithRestaurant).subscribe({
      next: () => {
        alert('Food item added successfully!');
        this.router.navigate(['/FoodItemList']);
        this.resetForm();
      },
      error: (err) => {
        console.error('Error adding food item:', err);
        alert('Failed to add food item.');
      }
    });
  }

  resetForm(): void {
    this.foodItem = {
      name: '',
      description: '',
      price: 0,
      quantity: 0,
      expirationDate: '',
      picture: ''
    };
  }
}
