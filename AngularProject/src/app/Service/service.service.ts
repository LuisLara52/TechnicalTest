import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Card } from '../Model/Card';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  //tarjeta:Card[];
  constructor(private http:HttpClient) { }

  Url='http://localhost:8080/app/card/allCards';

  getTarjetas(){
    return this.http.get<Card[]>(this.Url);
  }
}
