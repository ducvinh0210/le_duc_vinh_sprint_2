import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {IUser} from '../model/i-user';
import {Observable} from 'rxjs';
import {JwtResponseService} from './jwt-response-service';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  loginUrl = 'http://localhost:8080/api/shoes';

  constructor(private http: HttpClient) { }

  sendLogin(user: IUser): Observable<JwtResponseService> {
    return this.http.post<JwtResponseService>(this.loginUrl, user, httpOptions);
  }

  google(jwtResponse: JwtResponseService): Observable<any> {
    console.log(jwtResponse);
    return this.http.post<JwtResponseService>(this.loginUrl + '/oauth/google', jwtResponse, httpOptions);
  }
}
