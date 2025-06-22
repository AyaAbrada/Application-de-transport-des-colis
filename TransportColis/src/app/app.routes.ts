import {Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {LogoutComponent} from './components/logout/logout.component';
import {ExpediteurDashboardComponent} from './components/expediteur-dashboard/expediteur-dashboard.component';
import {AdminDashboardComponent} from './components/admin-dashboard/admin-dashboard.component';
import {ConducteurDashboardComponent} from './components/conducteur-dashboard/conducteur-dashboard.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {DemandeComponent} from './components/demande/demande.component';
import {TrajetComponent} from './components/trajet/trajet.component';

export const appRoutes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'expediteur-dashboard', component: ExpediteurDashboardComponent},
  { path: 'admin-dashboard', component: AdminDashboardComponent},
  { path: 'conducteur-dashboard', component: ConducteurDashboardComponent},
  { path: 'dashboard', component: DashboardComponent},
    { path: 'demande', component: DemandeComponent},
    { path: 'trajet', component: TrajetComponent},


  ]
;
