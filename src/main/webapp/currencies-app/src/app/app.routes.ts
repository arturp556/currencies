import { Routes } from '@angular/router';
import { CurrencyComponent } from './component/currency/currency.component';
import { CurrencyRequestHistoryComponent } from './component/currency-request-history/currency-request-history.component';

export const routes: Routes = [
  {
    path: 'history',
    component: CurrencyRequestHistoryComponent,
    title: 'History'
  },
  {
    path: 'currency',
    component: CurrencyComponent,
    title: 'Currency'
  }
];
