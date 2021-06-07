import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CustomerBackendService {

  public API_URL = 'http://localhost:8080/customer';

  constructor(public httpClient: HttpClient) { }

  getCustomerByAccountId(accountId: number) :Observable<any> {
    return this.httpClient.get(this.API_URL + '/detail/' + accountId);
  }
}
