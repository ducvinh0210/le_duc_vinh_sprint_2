import {Component, OnInit} from '@angular/core';
import {IShoeDto} from '../../model/i-shoe-dto';
import {ShoeService} from '../../service/shoe.service';
import {ActivatedRoute} from '@angular/router';
import {TokenStorageService} from '../../service/token-storage.service';
import {Title} from '@angular/platform-browser';
import {IShoeSizeDto} from '../../model/i-shoe-size-dto';


@Component({
  selector: 'app-shoe-detail',
  templateUrl: './shoe-detail.component.html',
  styleUrls: ['./shoe-detail.component.css']
})
export class ShoeDetailComponent implements OnInit {

  id: number;
  shoe: IShoeDto;
  quantityChoose = 1;
  username: string;
  roles: string[] = [];
  shoeSizeIdChoose = 0;
  idUSer: number;
  shoeSizeList: IShoeSizeDto[];

  isCustomer = false;
  isAdmin = false;
  quantityOfCurrentSize: number;


  constructor(private shoeService: ShoeService,
              private activatedRoute: ActivatedRoute,
              private tokenService: TokenStorageService,
              private title: Title) {
  }

  ngOnInit(): void {
    this.id = Number(this.activatedRoute.snapshot.params.id);
    this.findShoeById();
    this.findAllSizeByShoe();

    this.username = '';
    this.showUsername();


  }


  showUsername() {
    this.username = this.tokenService.getUser().username;
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;
    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;
    if (this.username !== '' && this.username !== undefined) {
      this.shoeService.findCustomer(this.username).subscribe(customer => {
        if (customer != null) {
          this.idUSer = customer.id;
        }
      }, error => {
        console.log('day la loi' + error);
      });
    }

  }

  findShoeById(): void {

    this.shoeService.findShoeById(this.id).subscribe(value => {
      this.shoe = value;
      debugger
      console.log(this.shoe);
    }, error => {
      console.log('day la loi' + error);
    });
  }

  findAllSizeByShoe(): void {
    this.shoeService.findSizeByShoe(this.id).subscribe(value => {
      this.shoeSizeList = value;

    }, error => {
      console.log('day la loi' + error);
    });
  }


  chooseShoeSize(item: any) {
    this.quantityChoose = 1;
    this.shoeSizeIdChoose = item.id;
  }

  descQuantity(): void {
    this.quantityChoose--;
  }

  ascQuantity() {
    this.quantityChoose++;
  }

  checkQuantity() {

  }


  addToCart(): void {

  }
}
