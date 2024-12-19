import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooditemADDComponent } from './fooditem-add.component';

describe('FooditemADDComponent', () => {
  let component: FooditemADDComponent;
  let fixture: ComponentFixture<FooditemADDComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FooditemADDComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FooditemADDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
