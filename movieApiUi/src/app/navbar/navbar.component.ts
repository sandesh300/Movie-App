import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {

  isLoggedIn = signal<boolean>(false);
  name: string | null = sessionStorage.getItem('name');

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.getLoggedInSignal();
  }

  logout(): void {
    this.authService.logout();
    this.authService.setLoggedIn(false);
    this.router.navigate(['login']);
  }
}
