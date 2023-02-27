import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrolarComponent } from './enrolar.component';

describe('EnrolarComponent', () => {
  let component: EnrolarComponent;
  let fixture: ComponentFixture<EnrolarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnrolarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EnrolarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
