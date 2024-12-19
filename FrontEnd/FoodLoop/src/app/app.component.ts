import { Component } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'FoodLoop';
  showNavbar = true;

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Get the route's data for showNavbar
        const childRoute = this.activatedRoute.firstChild;
        if (childRoute) {
          childRoute.data.subscribe((data) => {
            this.showNavbar = data['showNavbar'] !== false; // Default to true
          });
        }
      }
    });
  }
  
}
