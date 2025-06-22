import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  constructor(private router: Router) {}

  goToDemandes() {
    this.router.navigate(['/demande']);
  }
  goToTrajet() {
    this.router.navigate(['/trajet']);
  }
}
