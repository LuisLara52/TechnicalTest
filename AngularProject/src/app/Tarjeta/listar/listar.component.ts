import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Card } from 'src/app/Model/Card';
import {ServiceService} from '../../Service/service.service'
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {

  cards: Card[]=[];
  constructor(private service:ServiceService, private router:Router, private http: HttpClient){}

  ngOnInit(){
    this.http.get<Card[]>('/app/card/allCards')
    .subscribe(
      data=>{
      this.cards=data;
    })
  }

}
