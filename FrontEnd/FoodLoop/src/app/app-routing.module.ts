import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { SignupComponent } from './components/signup/signup.component';
import { FoodCatalogueComponent } from './components/food-catalogue/food-catalogue.component';
import { OrdersComponent } from './components/orders/orders.component';
import { FoodItemComponent } from './components/food-item/food-item.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },  // Default route
  { path: 'login', component: LoginComponent ,data: { showNavbar: false }} , // Add a route for the LoginComponent
  { path: 'home', component: HomeComponent },  // Add a route for the LoginComponent
  { path: 'signup', component: SignupComponent,data: { showNavbar: false } } , // Add a route for the LoginComponent
  { path: 'FoodCat', component: FoodCatalogueComponent },  // Add a route for the LoginComponent
  { path: 'OrdersList', component: OrdersComponent },  // Add a route for the LoginComponent
  { path: 'FoodItemList', component: FoodItemComponent },  // Add a route for the LoginComponent
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
