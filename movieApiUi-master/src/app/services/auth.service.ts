import { HttpClient } from '@angular/common/http';
import { Injectable, signal, WritableSignal } from '@angular/core';
import { Observable, tap } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public BASE_URL = "http://localhost:8080";
  private loggedIn = signal<boolean>(this.isAuthenticated());

  constructor(private http: HttpClient) { }

  register(registerRequest: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.BASE_URL}/api/v1/auth/register`, registerRequest);
  }

  login(loginRequest: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.BASE_URL}/api/v1/auth/login`, loginRequest)
    .pipe(tap(response => {
      if(response && response.accessToken) {
        sessionStorage.setItem('accessToken', response.accessToken);
        sessionStorage.setItem('refreshToken', response.refreshToken);
        sessionStorage.setItem('name', response.name);
        sessionStorage.setItem('username', response.username);
        sessionStorage.setItem('email', response.email);
      }
    }));
  }

  isAuthenticated(): boolean {
    return !!sessionStorage.getItem('accessToken');
  }

  logout(): void {
    sessionStorage.removeItem('accessToken');
    sessionStorage.removeItem('refreshToken');
    sessionStorage.removeItem('name');
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('email');
  }

  setLoggedIn(value: boolean) {
    this.loggedIn.set(value);
  }

  getLoggedIn(): WritableSignal<boolean> {
    return this.loggedIn;
  }
}

export type RegisterRequest = {
  name: string,
  email: string,
  username: string,
  password: string,
}

export type AuthResponse = {
  accessToken: string,
  refreshToken: string,
  name: string,
  email: string,
  username: string,
}

export type LoginRequest = {
  email: string,
  password: string,
}
