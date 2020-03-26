import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DondeEstamosComponent } from './donde-estamos.component';

describe('DondeEstamosComponent', () => {
  let component: DondeEstamosComponent;
  let fixture: ComponentFixture<DondeEstamosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DondeEstamosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DondeEstamosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
