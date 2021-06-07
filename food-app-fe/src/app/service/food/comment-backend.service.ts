import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {FoodComment} from '../../model/food-comment.model';

@Injectable({
  providedIn: 'root'
})
export class CommentBackendService {


  public API_URL = 'http://localhost:8080/comment';

  constructor(public httpClient: HttpClient) {
  }

  public getCommentById(foodId: number): Observable<any> {
    return this.httpClient.get(this.API_URL + '/list/' + foodId);
  }

  public saveComment(comment: FoodComment): Observable<any> {
    return this.httpClient.post(this.API_URL + '/save', comment);
  }

}
