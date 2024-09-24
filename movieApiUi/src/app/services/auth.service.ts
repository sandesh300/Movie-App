import { HttpClient } from '@angular/common/http';
import { Injectable, signal, WritableSignal } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { catchError, Observable, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public BASE_URL = "http://localhost:8080";
  private loggedIn = signal<boolean>(this.isAuthenticated());

  constructor(private http: HttpClient) { }

  register(registerRequest: RegisterRequest): Observable<any> {
    return this.http.post(`${this.BASE_URL}/api/v1/auth/register`, registerRequest);
  }

  login(loginRequest: LoginRequest): Observable<AuthResponse> {
    // Update the endpoint to match your backend
    return this.http.post<AuthResponse>(`${this.BASE_URL}/api/v1/auth/login`, loginRequest)
               .pipe(tap(response => {
                // Use accessToken instead of token to match backend response
                if(response && response.accessToken) {
                  sessionStorage.setItem('authToken', response.accessToken);
                  sessionStorage.setItem('refreshToken', response.refreshToken);
                  sessionStorage.setItem('name', response.name);
                  sessionStorage.setItem('username', response.username);
                  sessionStorage.setItem('email', response.email);
                }
               }));
  }

  logout(): void {
    sessionStorage.removeItem('authToken');
    sessionStorage.removeItem('refreshToken');
    sessionStorage.removeItem('name');
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('email');
  }

  // this will check if token is present as well as it is not expired
  isAuthenticated(): boolean {
    const token = sessionStorage.getItem('authToken');
    return token != null && !this.isTokenExpired(token);
  }

  setLoggedIn(value: boolean) {
    this.loggedIn.set(value);
  }

  getLoggedInSignal(): WritableSignal<boolean> {
    return this.loggedIn;
  }
  
  // If JWT expires, this method gives another JWT using refreshToken or proceeds to login page
  refreshToken(): Observable<any> {
    const refreshToken = sessionStorage.getItem('refreshToken');
    return this.http.post(`${this.BASE_URL}/api/v1/auth/refresh`, { refreshToken }).pipe(
      tap((res: any) => sessionStorage.setItem('authToken', res.accessToken)),
      catchError((err) => {
        this.logout();
        return throwError(() => err);
      })
    );
  }

  isTokenExpired(token: string): boolean {
    const decoded: any = jwtDecode(token);
    return (decoded.exp * 1000) < Date.now();
  }

  setToken(token: string) {
    sessionStorage.setItem('authToken', token);
  }
}

export type LoginRequest = {
  email: string,
  password: string,
}

export type RegisterRequest = {
  name: string,
  email: string,
  username: string,
  password: string,
}

export type AuthResponse = {
  accessToken: string,  // Updated to match backend
  refreshToken: string,
  name: string,
  username: string,
  email: string,
}
