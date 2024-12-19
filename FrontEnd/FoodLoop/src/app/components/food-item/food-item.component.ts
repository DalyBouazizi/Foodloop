import { Component, OnInit } from '@angular/core';
import { FoodService } from '../../services/food.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-food-item',
  templateUrl: './food-item.component.html',
  styleUrl: './food-item.component.css'
})
export class FoodItemComponent implements OnInit {
  foodItems: any[] = [];

  constructor(private foodService: FoodService, private router: Router) {}

  ngOnInit(): void {
    this.loadFoodItems();
  }

  loadFoodItems(): void {
    this.foodService.getAllFoodItems().subscribe((data) => {
      this.foodItems = data;
    });
  }

  addFoodItem(): void {
    this.router.navigate(['/food-item-form']);
  }

  editFoodItem(id: number): void {
    this.router.navigate(['/food-item-form', id]);
  }
 // Delete a food item
 deleteFoodItem(id: number): void {
  if (confirm('Are you sure you want to delete this food item?')) {
    this.foodService.deleteFoodItem(id).subscribe({
      next: () => {
        // Remove the deleted item from the local list
        this.foodItems = this.foodItems.filter(item => item.id !== id);
        alert('Food item deleted successfully!');
      },
      error: (err) => {
        console.error('Error deleting food item:', err);
        alert('Failed to delete the food item.');
      }
    });
  }
}
}
