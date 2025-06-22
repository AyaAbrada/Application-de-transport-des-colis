import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  standalone: true,
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = {
    username: '',
    password: '',
    role:'ADMIN | EXPEDITEUR | CONDUCTEUR'
  };

  message: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.authService.login(this.user).subscribe({
      next: (res: any) => {
        console.log('Réponse de login:', res);

        this.authService.saveToken(res.token);

        const role = (res.role || '').toUpperCase().trim();

        switch (role) {
          case 'ADMIN':
            this.router.navigate(['/admin-dashboard']);
            break;
          case 'EXPEDITEUR':
            this.router.navigate(['/expediteur-dashboard']);
            break;
          case 'CONDUCTEUR':
            this.router.navigate(['/conducteur-dashboard']);
            break;
          default:
            this.router.navigate(['/dashboard']);
            break;
        }

      },
      error: () => {
        this.message = 'Identifiants invalides.';
      }
    });
  }
}
