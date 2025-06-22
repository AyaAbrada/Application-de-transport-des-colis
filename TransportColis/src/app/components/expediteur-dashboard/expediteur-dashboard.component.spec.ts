import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpediteurDashboardComponent } from './expediteur-dashboard.component';

describe('ExpediteurDashboardComponent', () => {
  let component: ExpediteurDashboardComponent;
  let fixture: ComponentFixture<ExpediteurDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExpediteurDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExpediteurDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
