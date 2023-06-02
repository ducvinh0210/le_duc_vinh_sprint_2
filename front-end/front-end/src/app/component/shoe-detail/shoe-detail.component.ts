import {Component, OnInit} from '@angular/core';
import {IShoeDto} from '../../model/i-shoe-dto';
import {ShoeService} from '../../service/shoe.service';
import {ActivatedRoute} from '@angular/router';
import {TokenStorageService} from '../../service/token-storage.service';
import {Title} from '@angular/platform-browser';
import {IShoeSizeDto} from '../../model/i-shoe-size-dto';
import Swal from 'sweetalert2';


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
    this.getQuantitySizeProduct(item.idSize);
  }

  descQuantity(): void {
    this.quantityChoose--;
  }

  ascQuantity(): void {
    if (this.quantityChoose < this.quantityOfCurrentSize) {
      this.quantityChoose++;
    }
  }


  addToCart(): void {
    if (this.checkQuantity()) {
      debugger
      this.shoeService.addToCart(this.quantityChoose, this.idUSer, this.shoeSizeIdChoose).subscribe(() => {

        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 1000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer);
            toast.addEventListener('mouseleave', Swal.resumeTimer);
          }
        });

        Toast.fire({
          icon: 'success',
          title: 'Thêm vào giỏ hàng thành công!'
        }).then(r => location.replace('cart'));
      }, error => {
        console.log(error);
      });

    } else {
      this.quantityChoose = this.quantityOfCurrentSize;
    }

  }

  getQuantitySizeProduct(idSize: number) {
    this.shoeService.getQuantitySizeProduct(idSize, this.id).subscribe(value => {
      this.quantityOfCurrentSize = value;
    });
  }


  checkQuantity(): boolean {
    if (this.quantityChoose > this.quantityOfCurrentSize) {
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 1000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer);
          toast.addEventListener('mouseleave', Swal.resumeTimer);
        }
      });

      Toast.fire({
        icon: 'warning',
        title: 'Qúa số lượng trong kho, chỉ có thể chọn tối đa' + this.quantityOfCurrentSize
      });

      return false;
    } else {
      return true;
    }
  }


}
