import { Routes } from '@angular/router';
import { AddMovieComponent } from './add-movie/add-movie.component';
import { authGuard } from './guards/auth.guard';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

export const routes: Routes = [
    {path: '', title: 'MovieFlix - Home', component: HomeComponent},
    {path: 'add-movie', title: 'MovieFlix - Add Movie', component: AddMovieComponent, canActivate: [authGuard]},
    {path: 'login', title: 'MovieFlix - Login', component: LoginComponent},
    {path: 'register', title: 'MovieFlix - Register', component: RegisterComponent},
];
