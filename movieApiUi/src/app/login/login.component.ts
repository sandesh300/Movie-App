import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService, LoginRequest } from '../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email = new FormControl<string>('', [Validators.email, Validators.required]);
  password = new FormControl<string>('', [Validators.required, Validators.minLength(5)]);
  loginForm: FormGroup;

  inlineNotification = {
    show: false,
    type: '',
    text: '',
  }

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {
    this.loginForm = this.formBuilder.group({
      email: this.email,
      password: this.password,
    });
  }

  login() {
    if(this.loginForm.valid) {
      console.log(this.loginForm.value);
      const loginRequest: LoginRequest = {
        email: this.loginForm.get('email')?.value,
        password: this.loginForm.get('password')?.value,
      };
      this.authService.login(loginRequest).subscribe({
        next: (res: any) => {
          console.log("Response :: " + res);
          this.inlineNotification = {
            show: false,
            type: '',
            text: '',
          };
          console.log('came here1');
          this.authService.setLoggedIn(true);
          console.log('came here2');
          this.router.navigate(['add-movie']);
          console.log('came here3');
        },
        error: (err: any) => {
          console.log("Some error occurred :: " + err);
          this.loginForm.reset();
          this.inlineNotification = {
            show: true,
            type: 'error',
            text: 'Login failed. Please try again!',
          };
        }
      })
    } else {
      this.inlineNotification = {
        show: true,
        type: 'error',
        text: 'Please fill up the mandatory fields!',
      }
      document.body.scrollTop = document.documentElement.scrollTop = 0;
    }
  }
}
