import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {FormsModule} from '@angular/forms';

interface Demande {
  id?: number;
  poids: number;
  type: string;
  longeur: number;
  largeur: number;
  hauteur: number;
  statut?: string;
  trajetId: number;
  expediteurId?: number;
}

@Component({
  selector: 'app-demande',
  templateUrl: './demande.component.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./demande.component.css']
})
export class DemandeComponent implements OnInit {

  demande = {
    poids: null,
    type: '',
    longeur: null,
    largeur: null,
    hauteur: null,
    trajetId: null,
  };


  demandes: Demande[] = [];
  filterStatut: string = '';
  formData: Demande = {
    poids: 0,
    type: '',
    longeur: 0,
    largeur: 0,
    hauteur: 0,
    trajetId: 0
  };
  editing: boolean = false;

  token = 'TON_JWT_TOKEN'; // Remplacer par token réel

  baseUrl = 'http://localhost:8080/demandes';

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.loadDemandes();
  }

  loadDemandes() {
    let url = this.baseUrl;
    if (this.filterStatut) {
      url += `/statut/${this.filterStatut}`;
    }
    this.http.get<Demande[]>(url).subscribe(data => this.demandes = data);
  }

  appliquerFiltre(statut: string) {
    this.filterStatut = statut;
    this.loadDemandes();
  }

  startEdit(demande: Demande) {
    this.editing = true;
    this.formData = { ...demande };
  }

  annulerEdit() {
    this.editing = false;
    this.resetForm();
  }

  resetForm() {
    this.formData = {
      poids: 0,
      type: '',
      longeur: 0,
      largeur: 0,
      hauteur: 0,
      trajetId: 0
    };
  }

  saveDemande() {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);

    if (this.editing && this.formData.id) {
      this.http.put<Demande>(`${this.baseUrl}/${this.formData.id}`, this.formData, { headers }).subscribe(() => {
        alert('Demande mise à jour');
        this.loadDemandes();
        this.annulerEdit();
      });
    } else {
      this.http.post<Demande>(this.baseUrl, this.formData, { headers }).subscribe(() => {
        alert('Demande créée');
        this.loadDemandes();
        this.resetForm();
      });
    }
  }

  accepterDemande(id: number) {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);
    this.http.put<Demande>(`${this.baseUrl}/${id}/statut?action=accepter`, null, { headers }).subscribe(() => this.loadDemandes());
  }

  refuserDemande(id: number) {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);
    this.http.put<Demande>(`${this.baseUrl}/${id}/statut?action=refuser`, null, { headers }).subscribe(() => this.loadDemandes());
  }

  supprimerDemande(id: number) {
    if (confirm('Confirmer la suppression ?')) {
      const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);
      this.http.delete<void>(`${this.baseUrl}/${id}`, { headers }).subscribe(() => this.loadDemandes());
    }
  }
  
}
