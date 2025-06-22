import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-logout',
  template: `<button (click)="logout()">Déconnexion</button>`
})
export class LogoutComponent {
  constructor(private authService: AuthService, private router: Router) {}

  logout() {
    this.authService.logout().subscribe({
      next: () => {
        this.authService.clearToken();
        this.router.navigate(['/login']);
      },
      error: () => {
        this.authService.clearToken(); // fallback
        this.router.navigate(['/login']);
      }
    });
  }
}
