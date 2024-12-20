import { Component } from '@angular/core';
import { OrderService } from '../../services/order.service';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-order-history-list',
  templateUrl: './order-history-list.component.html',
  styleUrl: './order-history-list.component.css'
})
export class OrderHistoryListComponent {

  orders: any[] = []; // Array to store orders for the buyer

  constructor(
    private orderService: OrderService,
    private storageService: StorageService
  ) {}

  ngOnInit(): void {
    // Get buyer ID from storage
    const buyerId = this.storageService.getItem('userId');
    if (buyerId) {
      this.fetchOrdersByBuyerId(buyerId);
    } else {
      console.error('Buyer ID not found in local storage');
    }
  }

  fetchOrdersByBuyerId(buyerId: string): void {
    this.orderService.getOrdersByBuyerId(buyerId).subscribe({
      next: (orders) => {
        this.orders = orders;
        console.log('Orders fetched successfully:', orders);
      },
      error: (err) => {
        console.error('Error fetching orders:', err);
      }
    });
  }

  cleanStatus(status: string): string {
    status= status.toUpperCase();
    return status.replace(/['"]/g, '').trim(); // Remove quotes and trim whitespace
  }

  }


