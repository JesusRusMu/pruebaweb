import { TestBed } from '@angular/core/testing';

import { RegisterPatientsService } from './register-patients.service';

describe('RegisterPatientsService', () => {
  let service: RegisterPatientsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegisterPatientsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
