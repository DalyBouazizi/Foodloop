import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SignupComponent } from './components/signup/signup.component';
import { FoodCatalogueComponent } from './components/food-catalogue/food-catalogue.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { OrdersComponent } from './components/orders/orders.component';
import { FoodItemComponent } from './components/food-item/food-item.component';
import { FooditemADDComponent } from './components/fooditem-add/fooditem-add.component';
import { OrderHistoryListComponent } from './components/order-history-list/order-history-list.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    FoodCatalogueComponent,
    NavbarComponent,
    OrdersComponent,
    FoodItemComponent,
    FooditemADDComponent,
    OrderHistoryListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, // Include the AppRoutingModule here
    FormsModule, // Add FormsModule here
    HttpClientModule,
    


  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
