import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CurrencyComponent } from './component/currency/currency.component';
import { CurrencyRequestHistoryComponent } from './component/currency-request-history/currency-request-history.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterLink, RouterOutlet, CurrencyComponent, CurrencyRequestHistoryComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'currencies-app';
}
