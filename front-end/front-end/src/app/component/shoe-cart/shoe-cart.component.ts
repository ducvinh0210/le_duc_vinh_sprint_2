import {Component, OnInit} from '@angular/core';
import {ICart} from '../../model/i-cart';
import {ICustomer} from '../../model/i-customer';
import {ShoeService} from '../../service/shoe.service';
import {Router} from '@angular/router';
import {TokenStorageService} from '../../service/token-storage.service';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-shoe-cart',
  templateUrl: './shoe-cart.component.html',
  styleUrls: ['./shoe-cart.component.css']
})
export class ShoeCartComponent implements OnInit {


  cart: ICart[];
  totalPrice = 0;
  finalPrice = 0;
  username: string;

  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;
  pay = 'none';
  customer: ICustomer;

  constructor(private shoeService: ShoeService,
              private router: Router,
              private tokenService: TokenStorageService,
              private title: Title) {
    title.setTitle('Giỏ hàng');
  }

  ngOnInit(): void {
    this.username = '';
    this.showUsername();


  }


  showUsername() {
    this.username = this.tokenService.getUser().username;
    console.log(this.username);
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;
    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;
    if (this.username !== '') {
      this.shoeService.findCustomer(this.username).subscribe(customer => {
        if (customer != null) {

          this.customer = customer;
          this.getListCard();
        }
      }, error => {
        console.log('day la loi' + error);
      });
    }
  }


  getListCard() {

    this.shoeService.showListCardByUser(this.customer.id).subscribe(value => {

      console.log(value);
      this.cart = value;
      debugger

      for (const item of value) {
        this.totalPrice += item.price * item.quantity;
        this.finalPrice += item.price * (1 - item.discount / 100) * item.quantity;
      }
    }, error => {
      console.log(error);
    });
  }

}
