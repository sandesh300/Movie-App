import {
    HttpErrorResponse,
    HttpHandler,
    HttpHandlerFn,
    HttpRequest,
} from '@angular/common/http';
import { inject } from '@angular/core';
import { switchMap, catchError, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

export function authInterceptor(
    req: HttpRequest<unknown>,
    next: HttpHandlerFn
) {
    const authService = inject(AuthService);

    if (
        req.url.includes('/authenticate') ||
        req.url.includes('/register') ||
        req.url.includes('/refresh')
    ) {
        return next(req);
    }

    if (authService.isAuthenticated()) {
        const token = sessionStorage.getItem('authToken');
        req = addToken(req, token!!);
    }

    return next(req).pipe(
        catchError((error: HttpErrorResponse) => {
            if (error.status === 401) {
                return handle401Error(req, next);
            }
            return throwError(() => error);
        })
    );
}

// if jwt expires
function handle401Error(request: HttpRequest<unknown>, next: HttpHandlerFn) {
    const authService = inject(AuthService);
    const router = inject(Router);

    return authService.refreshToken().pipe(
        switchMap((token: string) => {
            authService.setToken(token);
            return next(addToken(request, token));
        }),
        catchError((err) => {
            authService.logout();
            router.navigate(['login']);
            return throwError(() => err);
        })
    );
}

function addToken(request: HttpRequest<unknown>, token: string) {
    return request.clone({
        setHeaders: {
            Authorization: `Bearer ${token}`,
        },
    });
}
