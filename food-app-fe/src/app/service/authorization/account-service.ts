import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenStorageService} from './token-storage';


@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private options: any;
  private isLogout = false;
  baseURL = 'http://localhost:8080/';

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
    this.setOptions();
  }

  setOptions() {
    this.options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ` + this.tokenStorage.getToken()
      })
      ,
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };
  }

  get httpOptions() {
    if (this.tokenStorage.getTime() && !this.isLogout) {
      if (this.tokenStorage.getTime().getTime() < new Date().getTime()) {
        this.isLogout = true;
        this.logout().subscribe(() => {
          this.tokenStorage.logOut();
          this.setOptions();
        });
      }
      this.isLogout = false;
    }
    return this.options;
  }

  logout(): Observable<any> {
    return this.http.put<Observable<any>>(this.baseURL + 'api/account/guest/logout', this.tokenStorage.getAccount());
  }

}




