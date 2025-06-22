import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Trajet, TypeMarchandise, TrajetService } from '../../services/trajet.service';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-trajet',
  templateUrl: './trajet.component.html',
  imports: [
    FormsModule, CommonModule
],
  styleUrls: ['./trajet.component.css']
})
export class TrajetComponent implements OnInit {

  trajets: Trajet[] = [];
  filterDestination: string = '';
  filterLieuDepart: string = '';
  filterTypeMarchandise?: TypeMarchandise;

  newTrajet: Trajet = {
    lieuDepart: '',
    etapes: [''],
    destination: '',
    typeMarchandise: TypeMarchandise.AUTRE,
    capaciteDesponible: 0
  };

  typesMarchandise = Object.values(TypeMarchandise);

  message = '';

  constructor(private trajetService: TrajetService) {}

  ngOnInit(): void {
    this.loadTrajets();
  }

  loadTrajets(): void {
    this.trajetService.getTrajets(this.filterDestination, this.filterLieuDepart, this.filterTypeMarchandise)
      .subscribe({
        next: (data: Trajet[]) => this.trajets = data,
        error: (err: any) => console.error(err)
      });
  }

  addEtapeField(): void {
    this.newTrajet.etapes.push('');
  }

  removeEtapeField(index: number): void {
    this.newTrajet.etapes.splice(index, 1);
  }

  submitTrajet(): void {
    if (!this.newTrajet.lieuDepart || !this.newTrajet.destination || !this.newTrajet.typeMarchandise) {
      this.message = 'Veuillez remplir tous les champs obligatoires';
      return;
    }

    this.trajetService.createTrajet(this.newTrajet).subscribe({
      next: (trajet: Trajet) => {
        this.message = 'Trajet créé avec succès!';
        this.newTrajet = {
          lieuDepart: '',
          etapes: [''],
          destination: '',
          typeMarchandise: TypeMarchandise.AUTRE,
          capaciteDesponible: 0
        };
        this.loadTrajets();
      },
      error: (err: any) => {
        this.message = 'Erreur lors de la création du trajet : ' + err.message;
      }
    });
  }
}
