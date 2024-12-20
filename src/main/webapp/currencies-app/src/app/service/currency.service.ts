import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { map } from "rxjs/operators";
import { Observable, of } from 'rxjs';
import { CurrencyResponse } from './currency-response';
import { CurrencyRequest } from './currency-request';
import { CurrencyRequestHistory } from './currency-request-history';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  private readonly CURRENCIES_API_URL = 'http://localhost:8080/currencies';

  constructor(
     private readonly http: HttpClient,
  ) {}

  getAllRequestHistory(): Observable<CurrencyRequestHistory[]> {
    return this.http.get<CurrencyRequestHistory[]>(`${this.CURRENCIES_API_URL}/requests`)
  }

  sendCurrencyRequest(currencyRequest: Partial<CurrencyRequest>): Observable<CurrencyResponse> {
    return this.http.post<CurrencyResponse>(`${this.CURRENCIES_API_URL}/get-current-currency-value-command`, currencyRequest)
  }
}
