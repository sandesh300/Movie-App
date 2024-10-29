import { Routes } from '@angular/router';
import { AddMovieComponent } from './components/add-movie/add-movie.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
    {path: '', title: "MovieApp - Home", component: HomeComponent},
    {path: 'login', title: "MovieApp - Login", component: LoginComponent},
    {path: 'register', title: "MovieApp - Register", component: RegisterComponent},
    {path: 'add-movie', title: "MovieApp - Add Movie", component: AddMovieComponent, canActivate: [authGuard]},
];
