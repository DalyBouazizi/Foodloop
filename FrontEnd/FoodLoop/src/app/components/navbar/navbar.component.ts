import { Component } from '@angular/core';
import { StorageService } from '../../services/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {


  isLoggedIn = false; // Track login status
  userRole: string | null = null; // Track user role ('restaurant' or 'buyer')

  constructor(private storageService: StorageService, private router: Router) {}

  ngOnInit(): void {
    // Check if the user is logged in
    const userId = this.storageService.getItem('userId');
    this.isLoggedIn = !!userId;

    // Retrieve user role (assumes role is stored in localStorage)
    this.userRole = this.storageService.getItem('userRole');
  }

  signOut(): void {
    // Clear user data from localStorage
    this.storageService.clear();
    this.isLoggedIn = false;
    this.userRole = null;

    // Redirect to the home page or login page
    this.router.navigate(['/home']);
  }
}
