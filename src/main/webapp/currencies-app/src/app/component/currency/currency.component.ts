import { Component } from '@angular/core';
import {
  Validators,
  FormGroup,
  FormControl,
  ReactiveFormsModule,
  UntypedFormGroup
} from "@angular/forms";
import { NgForOf, NgIf } from "@angular/common";
import { CurrencyService } from '../../service/currency.service';
import { CurrencyResponse } from '../../service/currency-response';
import { CurrencyRequest } from '../../service/currency-request';
import { ListErrorsComponent } from "../errors/list-errors.component";

interface CurrencyForm {
  currency: FormControl<string>;
  name: FormControl<string>;
}

@Component({
  selector: 'app-currency',
  standalone: true,
  imports: [ReactiveFormsModule, ListErrorsComponent, NgForOf, NgIf],
  templateUrl: './currency.component.html',
  styleUrl: './currency.component.css'
})
export class CurrencyComponent {
    isSubmitting = false;
    currencyForm: FormGroup<CurrencyForm>;
    currencyResponse: CurrencyResponse | null = null;
    errors: string | null = null;

    constructor(private readonly currencyService: CurrencyService) {
      this.currencyForm = new FormGroup<CurrencyForm>({
        currency: new FormControl("", {
          validators: [Validators.required],
          nonNullable: true,
        }),
        name: new FormControl("", {
          validators: [Validators.required],
          nonNullable: true,
        }),
      });
    }


    submitForm(): void {
      this.currencyResponse = null;
      this.isSubmitting = true;
      this.currencyService.sendCurrencyRequest(this.currencyForm.value)
         .subscribe({
           next: (response: CurrencyResponse) => {
              this.isSubmitting = false;
              this.errors = null;
              this.currencyResponse = response;
           },
           error: (err) => {
             this.isSubmitting = false;
             this.errors = err.error.message
           },
         });
    }

}
