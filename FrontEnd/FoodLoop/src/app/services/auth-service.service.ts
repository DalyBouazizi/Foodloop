import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private apiUrl = 'http://localhost:8080/users'; // Replace with your backend URL

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> { // Changed from 'string' to 'any' to handle the JSON response
    const params = new HttpParams().set('email', email).set('password', password);
    return this.http.post<any>(`${this.apiUrl}/login`, null, { // Specified the type as 'any'
      params,
      responseType: 'json', // Corrected response type to 'json'
    });
}

  signUpBuyer(buyerData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/buyers`, buyerData)
      .pipe(
        catchError(this.handleError)
      );
  }

  signUpRestaurant(restaurantData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/restaurants`, restaurantData)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 409) {
      // Return a specific error for "email already in use"
      return throwError(() => new Error('Email is already in use.'));
    }
    return throwError(() => new Error('An unexpected error occurred.'));
  }
}
