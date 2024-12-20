import { Component } from '@angular/core';
import { AuthServiceService } from '../../services/auth-service.service';
import { Router } from '@angular/router';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthServiceService, private router: Router,private storageService: StorageService) {}

  onSubmit(): void {
    this.authService.login(this.email, this.password).subscribe(
      (response) => {

        // Use StorageService to store user data
        this.storageService.setItem('userId', response.id);
        this.storageService.setItem('userRole', response.role);
        
        this.router.navigate(['/home']);
    
      },
      (error) => {
        this.errorMessage = 'Invalid email or password. Please try again.';
      }
    );
  }

}
