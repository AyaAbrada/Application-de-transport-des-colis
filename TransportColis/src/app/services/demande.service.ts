import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Demande {
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

@Injectable({
  providedIn: 'root'
})
export class DemandeService {

  private baseUrl = 'http://localhost:8080/demandes'; // adapter selon ton backend

  constructor(private http: HttpClient) { }

  getDemandes(): Observable<Demande[]> {
    return this.http.get<Demande[]>(this.baseUrl);
  }

  getDemandesByStatut(statut: string): Observable<Demande[]> {
    return this.http.get<Demande[]>(`${this.baseUrl}/statut/${statut}`);
  }

  getDemande(id: number): Observable<Demande> {
    return this.http.get<Demande>(`${this.baseUrl}/${id}`);
  }

  createDemande(demande: Demande, token: string): Observable<Demande> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post<Demande>(this.baseUrl, demande, { headers });
  }

  updateDemande(id: number, demande: Demande, token: string): Observable<Demande> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put<Demande>(`${this.baseUrl}/${id}`, demande, { headers });
  }

  traiterDemande(id: number, action: 'accepter' | 'refuser', token: string): Observable<Demande> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put<Demande>(`${this.baseUrl}/${id}/statut?action=${action}`, null, { headers });
  }

  deleteDemande(id: number, token: string): Observable<void> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.delete<void>(`${this.baseUrl}/${id}`, { headers });
  }
}
