import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FoodBackendService} from "../../../../service/food/food-backend.service";
import {Food} from "../../../../model/food.model";
import {FoodQueryDTO} from "../../../../model/food-query-dto.model";
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {PageEvent} from "@angular/material/paginator";
import {Category} from "../../../../model/category.model";
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";

@Component({
  selector: 'app-food-list',
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.scss']
})
export class FoodListComponent implements OnInit {

  public foodList: Array<Food>;
  public query: FoodQueryDTO;
  public formSearch: FormGroup;
  public length: number;
  public pageSize: number;
  public pageSizeOptions: number[] = [5, 10, 15, 20];
  public categoryList: Category[];

  constructor(public foodBackendService: FoodBackendService,
              public formBuilder: FormBuilder) {
  }

  ngOnInit(): void {

    this.query = {
      size: 10,
      page: 0,
      statuses: [],
      categories: []
    }

    this.formSearch = this.formBuilder.group({
        categories: this.formBuilder.array([]),
        statuses: this.formBuilder.array([]),
        name: [],
        highPrice: [],
        lowPrice: [],
        lowRating: [],
        highRating: []
      }
    )

    this.getListByQuery();

    this.foodBackendService.getCategoriesList().subscribe(data => {
      this.categoryList = data;
    });
  }

  updatePagination(pageEvent: PageEvent) {
    this.query.size = pageEvent.pageSize;
    this.query.page = pageEvent.pageIndex;
    this.getListByQuery();
  }

  getListByQuery() {
    this.foodBackendService.getListByQuery(this.query).subscribe(data => {
      this.foodList = data.content;
      this.length = data.totalElements;
    });
  }

  formatLabel(value: number) {
    if (value >= 1000) {
      return Math.round(value / 1000) + 'k';
    }
    return value;
  }

  searchByQuery() {
    this.query.page = 0;
    this.query.name = this.formSearch.controls.name.value ? this.formSearch.controls.name.value : null;
    this.query.categories = this.formSearch.controls.categories.value;
    this.query.highPrice = (this.formSearch.controls.highPrice.value > 0) ? this.formSearch.controls.highPrice.value : null;
    this.query.lowPrice = (this.formSearch.controls.lowPrice.value > 0) ? this.formSearch.controls.lowPrice.value : null;
    this.getListByQuery();
  }

  isCheckedCategory(categoryId: number) {
    return (this.formSearch.controls.categories.value.reduce((isCheck, cat) =>
      cat.categoryId == categoryId, false));
  }

  toggleCategoryForm(e) {
    const checkArray: FormArray = this.formSearch.get('categories') as FormArray;
    checkArray.markAsTouched();
    if (e.checked) {
      checkArray.push(new FormControl(e.source.value));
    } else {
      let i: number = 0;
      checkArray.controls.forEach((item: FormControl) => {
        if (item.value == e.source.value) {
          checkArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }
}
