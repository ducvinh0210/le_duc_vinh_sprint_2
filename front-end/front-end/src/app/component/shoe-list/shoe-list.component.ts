import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {IType} from '../../model/i-type';
import {IShoeDto} from '../../model/i-shoe-dto';
import {ShoeService} from '../../service/shoe.service';
import {TokenStorageService} from '../../service/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-shoe-list',
  templateUrl: './shoe-list.component.html',
  styleUrls: ['./shoe-list.component.css']
})
export class ShoeListComponent implements OnInit {

  nameShoe = '';
  typeShoe = '';
  manufacturerShoe = '';
  priceStart = 0;
  priceEnd = 10000000;
  sortBy = 'newest';
  page = 1;
  pageSize = 4;
  quantity: number;
  totalPage: number;
  shoeList$: Observable<IShoeDto[]>;
  shoeTypeList$: Observable<IType[]>;
  manufacturer$: Observable<string[]>;


  username: string;
  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;

  constructor(private shoeService: ShoeService,
              private tokenService: TokenStorageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllShoePaging();
    this.getAllShoeType();
    this.getAllManufacturer();

    this.username = '';
    this.showUsername();
  }


  showUsername() {
    this.username = this.tokenService.getUser().username;
    console.log(this.username);
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;
    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;
  }


  getAllShoePaging(): void {

    this.shoeService.showListShoe(this.page, this.pageSize, this.nameShoe, this.typeShoe, this.manufacturerShoe,
      this.priceStart, this.priceEnd, this.sortBy).subscribe(value => {
        console.log(value);
        this.shoeList$ = new BehaviorSubject<IShoeDto[]>(value.content);
        console.log('day la value +' + this.shoeList$);
        this.quantity = value.totalElements;
        this.totalPage = Math.ceil(this.quantity / this.pageSize);
      },
      error => {
        console.log('day la loi' + error);
      });

  }

  getAllShoeType(): void {
    this.shoeService.showListShoeType().subscribe(value => {
      this.shoeTypeList$ = new BehaviorSubject<IType[]>(value);

    }, error => {
      console.log(error);
    });
  }

  getAllManufacturer(): void {
    this.shoeService.showListManufacturer().subscribe(value => {
      this.manufacturer$ = new BehaviorSubject<string[]>(value);
    }, error => {
      console.log(error);
    });
  }


  searchAllPrice(): void {
    this.priceStart = 0;
    this.priceEnd = 10000000;
    this.page = 1;
    this.getAllShoePaging();
  }

  searchType(name: string): void {
    this.page = 1;
    this.typeShoe = name;
    this.getAllShoePaging();
  }

  searchManufacturer(item: string): void {
    this.page = 1;
    this.manufacturerShoe = item;
    this.getAllShoePaging();

  }

  previous(): void {
    this.page--;
    this.getAllShoePaging();
  }

  next(): void {
    this.page++;
    this.getAllShoePaging();
  }

  getPage1(): void {
    this.page = 1;
    this.getAllShoePaging();
  }

  getPageEnd(): void {
    this.page = this.totalPage;
    this.getAllShoePaging();
  }

  getPageP(): void {
    this.page -= 2;

  }

  getPageN(): void {
    this.page += 2;
  }

  cleanInput(): void {
    this.nameShoe = '';
    this.page = 1;
    this.getAllShoePaging();

  }


  getLogin(id: number) {
    this.router.navigateByUrl('detail/' + id);
  }


}
