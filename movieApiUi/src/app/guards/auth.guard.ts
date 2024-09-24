import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  
  if(authService.isAuthenticated()) {
    console.log('here auth guard');
    return true;
  } else {
    console.log('here auth guard2');
    router.navigate(['login']);
    return false;
  }
};
