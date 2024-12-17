import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterParametersComponent } from './register-parameters.component';

describe('RegisterParametersComponent', () => {
  let component: RegisterParametersComponent;
  let fixture: ComponentFixture<RegisterParametersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegisterParametersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterParametersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
