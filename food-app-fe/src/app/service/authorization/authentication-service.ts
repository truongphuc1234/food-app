import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtResponse } from './JwtRespone';
import JwtRequest from './JwtRequest';


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
    loginUrl = 'http://localhost:8080/login';

    constructor(private http: HttpClient) { }

    sendLogin(jwtRequest: JwtRequest): Observable<JwtResponse> {
        return this.http.post<JwtResponse>(this.loginUrl, jwtRequest, httpOptions);
    }
}
