import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FoodQueryDTO} from '../../model/food-query-dto.model';
import {Observable} from 'rxjs';
import {Rating} from '../../model/rating.model';

@Injectable({
  providedIn: 'root'
})
export class RatingBackendService {


  public API_URL = 'http://localhost:8080/rating';

  constructor(public httpClient: HttpClient) {
  }

  public saveRating(rating: Rating): Observable<any> {
    return this.httpClient.post(this.API_URL, rating);
  }
}
