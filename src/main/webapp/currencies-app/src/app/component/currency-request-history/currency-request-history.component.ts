import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CurrencyRequestHistory } from '../../service/currency-request-history';
import { CurrencyService } from '../../service/currency.service';

@Component({
  selector: 'app-currency-request-history',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './currency-request-history.component.html',
  styleUrl: './currency-request-history.component.css'
})
export class CurrencyRequestHistoryComponent {
    currencyRequestHistory: CurrencyRequestHistory[] = [];

    constructor(private readonly currencyService: CurrencyService) {}

    ngOnInit() {
       this.currencyService.getAllRequestHistory()
             .subscribe({
               next: (history: CurrencyRequestHistory[]) => {
                 this.currencyRequestHistory = history;
               },
             });
    }

}
