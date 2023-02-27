import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Transactions } from 'src/app/Model/Transactions';
import {ServiceService} from '../../Service/service.service'
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-enrolar',
  templateUrl: './enrolar.component.html',
  styleUrls: ['./enrolar.component.css']
})
export class EnrolarComponent implements OnInit{
  transactiones:Transactions[]=[];
  constructor(private service:ServiceService, private router:Router, private http: HttpClient){}

  ngOnInit() {
        this.http.get<Transactions[]>('/app/transaction/allTransactions')
    .subscribe(
      data=>{
      this.transactiones=data;
    })
  }

}
