import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/user/login/login.component";
import {LogoutComponent} from "./pages/user/logout/logout.component";
import {RegisterComponent} from "./pages/user/register/register.component";
import {NotFoundComponent} from "./pages/not-found/not-found.component";


const routes: Routes = [
  {path: '', redirectTo: "/home", pathMatch: 'full'},
  {path: 'home', component: HomeComponent},

  {
    path: 'user', children: [
      {path: 'login', component: LoginComponent},
      {path: 'logout', component: LogoutComponent},
      {path: 'register', component: RegisterComponent}
    ]
  },

  {
    path: '**',
    component: NotFoundComponent
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
