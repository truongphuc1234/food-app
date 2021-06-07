import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FoodQueryDTO} from '../../model/food-query-dto.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FoodBackendService {

  public API_URL = 'http://localhost:8080/food';

  constructor(public httpClient: HttpClient) {
  }

  public getListByQuery(query: FoodQueryDTO): Observable<any> {
    return this.httpClient.post(this.API_URL + '/list', query);
  }

  public getCategoriesList(): Observable<any> {
    return this.httpClient.get(this.API_URL + '/category-list');
  }

  public getFoodDetail(foodId: number): Observable<any> {
    return this.httpClient.get(this.API_URL + '/detail/' + foodId);
  }

}
