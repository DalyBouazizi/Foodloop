import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../services/order.service';
import { StorageService } from '../../services/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit  {

  orders: any[] = [];

  constructor(private orderService: OrderService,private storageService: StorageService,private router: Router) {}

  ngOnInit(): void {
    // Use StorageService to retrieve restaurantId
    const restaurantId = this.storageService.getItem('userId') || '';
    this.orderService.getOrdersByRestaurantId(restaurantId).subscribe((data) => {
      this.orders = data;
    });

    

}


cleanStatus(status: string): string {
  return status.replace(/['"]/g, '').trim(); // Remove quotes and trim whitespace
}

 // Function to mark the order as completed
 markAsCompleted(order: any): void {
  const newStatus = 'COMPLETED';
  this.orderService.updateOrderStatus(order.id, newStatus).subscribe(
    (updatedOrder) => {
      // Update the order in the UI
      order.status = updatedOrder.status;
      console.log(`Order ${order.id} marked as ${newStatus}`);
      this.router.navigate(['/OrdersComponent']);
    },
    (error) => {
      console.error('Failed to update order status:', error);
    }
  );
}


}
