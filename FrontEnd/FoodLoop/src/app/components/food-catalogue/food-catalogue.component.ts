import { Component } from '@angular/core';
import { FoodService } from '../../services/food.service';

@Component({
  selector: 'app-food-catalogue',
  templateUrl: './food-catalogue.component.html',
  styleUrl: './food-catalogue.component.css'
})
export class FoodCatalogueComponent {

  foodItems: any[] = []; // Store food items from the service

  constructor(private foodService: FoodService) {}

  ngOnInit(): void {
    this.loadFoodItems();
  }

  loadFoodItems() {
    this.foodService.getAllFoodItems().subscribe((items: any[]) => {
      this.foodItems = items;
    });
  }

}
