import { Component } from '@angular/core';
import { AuthServiceService } from '../../services/auth-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  formData = {
    username: '',
    email: '',
    password: '',
    role: 'BUYER', // BUYER or RESTAURANT
    cart: null, // Buyer-specific
    restaurantName: '', // Restaurant-specific
    location: '', // Restaurant-specific
    logo: '', // Restaurant-specific
  };

  errorMessage = ''; // To store error message from backend

  constructor(private authService: AuthServiceService) {}

  onClear(){
    this.formData.role = 'BUYER';
    this.errorMessage = '';
  }

  onSubmit() {
    this.errorMessage = ''; // Reset the error message before submission
    if (this.formData.role === 'BUYER') {
      const buyerData = {
        username: this.formData.username,
        email: this.formData.email,
        password: this.formData.password,
        role: this.formData.role,
      };
      this.authService.signUpBuyer(buyerData).subscribe(
        (response) => {
          alert('Buyer registered successfully!');
          this.onClear();
        },
        (error) => {
          this.errorMessage = error.message;
        }
      );
    } else if (this.formData.role === 'RESTAURANT') {
      const restaurantData = {
        username: this.formData.username,
        email: this.formData.email,
        password: this.formData.password,
        role: this.formData.role,
        restaurantName: this.formData.restaurantName,
        location: this.formData.location,
        logo: this.formData.logo,
      };
      this.authService.signUpRestaurant(restaurantData).subscribe(
        (response) => {
          alert('Restaurant registered successfully!');
          this.onClear();
        },
        (error) => {
          this.errorMessage = error.message;
        }
      );
    }
  }
}
