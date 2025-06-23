import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

interface Demande {
  id?: number;
  poids: number;
  type: string;
  longeur: number;
  largeur: number;
  hauteur: number;
  statut?: string;
  trajetId: number;
  
}

interface Trajet {
  id: number;
  lieuDepart: string;
  destination: string;
}

@Component({
  selector: 'app-demande',
  templateUrl: './demande.component.html',
  imports: [FormsModule, CommonModule],
  styleUrls: ['./demande.component.css'],
  standalone: true
})
export class DemandeComponent implements OnInit {

  trajets: Trajet[] = [];
  demandes: Demande[] = [];
  formData: Demande = this.resetFormData();
  message = '';

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Charger les trajets
    this.http.get<Trajet[]>('http://localhost:8080/trajets')
      .subscribe(data => this.trajets = data);

    // Charger toutes les demandes
    this.loadDemandes();

    // Récupérer trajetId depuis query params (optionnel)
    this.route.queryParams.subscribe(params => {
      const trajetId = params['trajetId'];
      if (trajetId) {
        this.initDemandeForm(+trajetId);
      }
    });
  }

  loadDemandes(): void {
    this.http.get<Demande[]>('http://localhost:8080/demandes')
      .subscribe(data => this.demandes = data);
  }

  initDemandeForm(trajetId: number): void {
    this.formData = this.resetFormData();
    this.formData.trajetId = trajetId;
    this.message = '';
  }

  saveDemande(): void {
    const token = localStorage.getItem('token');
    if (!token) {
      alert('Token non trouvé, veuillez vous connecter.');
      return;
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.http.post('http://localhost:8080/demandes', this.formData, { headers })
      .subscribe({
        next: () => {
          this.message = 'Demande envoyée avec succès !';
          this.formData = this.resetFormData();
          this.loadDemandes(); // Actualiser la liste des demandes
        },
        error: (error) => {
          this.message = 'Erreur lors de l’envoi de la demande : ' + error.message;
        }
      });
  }

  resetFormData(): Demande {
    return {
      poids: 0,
      type: '',
      longeur: 0,
      largeur: 0,
      hauteur: 0,
      trajetId: 0
    };
  }
}
