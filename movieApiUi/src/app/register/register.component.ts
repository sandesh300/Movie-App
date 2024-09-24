import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService, RegisterRequest } from '../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  name = new FormControl<string>('', Validators.required);
  username = new FormControl<string>('', Validators.required);
  email = new FormControl<string>('', [Validators.email, Validators.required]);
  password = new FormControl<string>('', [Validators.required, Validators.minLength(5)]);
  registerForm: FormGroup;

  inlineNotification = {
    show: false,
    type: '',
    text: '',
  }

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService) {
    this.registerForm = this.formBuilder.group({
      name: this.name,
      username: this.username,
      email: this.email,
      password: this.password,
    });
  }

  register() {
    if(this.registerForm.valid) {
      console.log(this.registerForm.value);
      const registerRequest: RegisterRequest = {
        name: this.registerForm.get('name')?.value,
        email: this.registerForm.get('email')?.value,
        username: this.registerForm.get('username')?.value,
        password: this.registerForm.get('password')?.value,
      };
      this.authService.register(registerRequest).subscribe({
        next: (res: any) => {
          console.log("Response :: " + res);
          this.inlineNotification = {
            show: false,
            type: '',
            text: '',
          };
          this.router.navigate(['login']);
        },
        error: (err: any) => {
          console.log("Some error occurred :: " + err);
          this.registerForm.reset();
          this.inlineNotification = {
            show: true,
            type: 'error',
            text: 'Registration failed. Please try again!',
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
