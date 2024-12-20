import { Component, Input } from "@angular/core";
import { Errors } from "./errors.model";
import { NgForOf, NgIf, NgFor } from "@angular/common";

@Component({
  selector: "app-list-errors",
  templateUrl: "./list-errors.component.html",
  imports: [NgIf, NgForOf, NgFor],
  standalone: true,
})
export class ListErrorsComponent {

  @Input()
  errors: string | null = null;

}
