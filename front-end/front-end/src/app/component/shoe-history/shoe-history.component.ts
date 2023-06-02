import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {ICart} from '../../model/i-cart';
import {TokenStorageService} from '../../service/token-storage.service';
import {ShoeService} from '../../service/shoe.service';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-shoe-history',
  templateUrl: './shoe-history.component.html',
  styleUrls: ['./shoe-history.component.css']
})
export class ShoeHistoryComponent implements OnInit {

  page = 1;
  historyList$: Observable<ICart[]>;
  pageSize = 3;
  totalPage: number;

  username: string;
  roles: string[] = [];

  isCustomer = false;
  isAdmin = false;

  constructor(private tokenService: TokenStorageService,
              private shoeService: ShoeService,
              private router: Router,
              private title: Title) {
    title.setTitle('Lịch sử ');
  }

  ngOnInit(): void {
    this.username = '';
    this.showUsername();
  }


  showUsername() {
    this.username = this.tokenService.getUser().username;
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;
    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;
    if (this.username !== '') {
      this.shoeService.findCustomer(this.username).subscribe(customer => {
        if (customer != null) {
          this.shoeService.getAllHistoryShoe(customer.id, this.page, this.pageSize).subscribe(value => {
            this.historyList$ = new BehaviorSubject<ICart[]>(value.content);
            this.totalPage = Math.ceil(value.totalElements / this.pageSize);
          }, error => {
            console.log(error);
          });
        }
      }, error => {
        console.log(error);
      });
    }
  }

  previous(): void {
    this.page--;
    this.showUsername();

  }

  next(): void {
    this.page++;
    this.showUsername();
  }

  getPage1(): void {
    this.page = 1;
    this.showUsername();
  }

  getPageEnd(): void {
    this.page = this.totalPage;
    this.showUsername();
  }

  getPageP(): void {
    this.page -= 2;
    this.showUsername();
  }

  getPageN(): void {
    this.page += 2;
    this.showUsername();
  }


}
