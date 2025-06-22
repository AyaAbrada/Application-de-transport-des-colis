import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export enum TypeMarchandise {
  ELECTRONIQUE = 'ELECTRONIQUE',
  TEXTILE = 'TEXTILE',
  ALIMENTAIRE = 'ALIMENTAIRE',
  FRAGILE = 'FRAGILE',
  DOCUMENTS = 'DOCUMENTS',
  LIVRES = 'LIVRES',
  MOBILIER = 'MOBILIER',
  MEDICAL = 'MEDICAL',
  AUTOMOBILE = 'AUTOMOBILE',
  SPORT = 'SPORT',
  AUTRE = 'AUTRE',
}

export interface Trajet {
  id?: number;
  lieuDepart: string;
  etapes: string[];
  destination: string;
  typeMarchandise: TypeMarchandise;
  capaciteDesponible: number;
}

@Injectable({
  providedIn: 'root'
})
export class TrajetService {

  private baseUrl = 'http://localhost:8080/trajets';

  constructor(private http: HttpClient) { }

  getTrajets(destination?: string, lieuDepart?: string, typeMarchandise?: TypeMarchandise): Observable<Trajet[]> {
    let url = `${this.baseUrl}/search?`;
    if(destination) url += `destination=${destination}&`;
    if(lieuDepart) url += `lieuDepart=${lieuDepart}&`;
    if(typeMarchandise) url += `typeMarchandise=${typeMarchandise}&`;
    return this.http.get<Trajet[]>(url);
  }

  createTrajet(trajet: Trajet): Observable<Trajet> {
    return this.http.post<Trajet>(this.baseUrl, trajet);
  }
}
