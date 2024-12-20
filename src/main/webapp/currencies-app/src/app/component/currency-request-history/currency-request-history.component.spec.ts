import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrencyRequestHistoryComponent } from './currency-request-history.component';

describe('CurrencyRequestHistoryComponent', () => {
  let component: CurrencyRequestHistoryComponent;
  let fixture: ComponentFixture<CurrencyRequestHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CurrencyRequestHistoryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CurrencyRequestHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
