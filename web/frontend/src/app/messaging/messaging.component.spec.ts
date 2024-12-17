import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MensajesComponent } from './messaging.component';

describe('MessagingComponent', () => {
  let component: MensajesComponent;
  let fixture: ComponentFixture<MensajesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MensajesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MensajesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
