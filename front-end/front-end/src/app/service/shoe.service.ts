import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ObjectUnsubscribedError, Observable} from 'rxjs';
import {DataResult} from '../model/data-result';
import {IShoeDto} from '../model/i-shoe-dto';
import {IType} from '../model/i-type';
import {ICart} from '../model/i-cart';
import {ICustomer} from '../model/i-customer';
import {IShoeSizeDto} from '../model/i-shoe-size-dto';

const API_URL = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})
export class ShoeService {


  constructor(private httpClient: HttpClient) {
  }


  showListShoe(curPage: number, numberRecord: number, nameShoe: string, typeShoe: string, manufacturerShoe: string,
               priceStart: any, priceEnd: any, sortBy: string): Observable<DataResult<IShoeDto>> {

    return this.httpClient.get<DataResult<IShoeDto>>(API_URL + '/shoes/list-' + sortBy + '/' +
      nameShoe + '&' + typeShoe + '&' + manufacturerShoe + '&' + priceStart + '&' +
      priceEnd + '?page=' + (curPage - 1) + '&size=' + numberRecord);
  }


  showListShoeType(): Observable<IType[]> {
    return this.httpClient.get<IType[]>(API_URL + '/shoes/list-shoe-type');
  }

  showListManufacturer(): Observable<string[]> {
    return this.httpClient.get<string[]>(API_URL + '/shoes/list-manufacturer');
  }

  showListCardByUser(id: number): Observable<ICart[]> {

    return this.httpClient.get<ICart[]>(API_URL + '/shoes/cart/' + id);
  }


  findCustomer(username: string): Observable<ICustomer> {
    return this.httpClient.get<ICustomer>(API_URL + '/shoes/get-customer/' + username);
  }


  findShoeById(id: number): Observable<IShoeDto> {
    debugger
    return this.httpClient.get<IShoeDto>(API_URL + '/shoes/detail-shoe/' + id);
  }

  findSizeByShoe(id: number): Observable<IShoeSizeDto[]> {
    return this.httpClient.get<IShoeSizeDto[]>(API_URL + '/shoes/shoe-size/' + id);
  }

  addToCart(quantity: number, customerId: number, shoeSizeId: number): Observable<void> {
    debugger
    return this.httpClient.get<void>(API_URL + '/shoes/add-cart/' + quantity + '&' + customerId + '&' + shoeSizeId);
  }

  getQuantitySizeProduct(idSize: number, idProduct: number): Observable<any> {
    return this.httpClient.get<any>(API_URL + '/shoes/quantity-size/' + idSize + '/' + idProduct);
  }

  descQuantityCart(id: number): Observable<void> {
    return this.httpClient.get<void>(API_URL + '/shoes/desc-quantity/' + id);
  }

  ascQuantityCart(id: number): Observable<void> {
    return this.httpClient.get<void>(API_URL + '/shoes/asc-quantity/' + id);
  }

  removeCart(id: number): Observable<void> {
    return this.httpClient.get<void>(API_URL + '/shoes/remove-cart/' + id);
  }

  setQuantityShoeSize(customerId: number): Observable<any> {
    return this.httpClient.get<any>(API_URL + '/shoes/update-quantity-shoe?customerId=' + customerId);
  }

  getAllHistoryShoe(id: number, curPage: number, numberRecord: number): Observable<DataResult<ICart>> {
    return this.httpClient.get<DataResult<ICart>>(API_URL + '/shoes/history-cart/' +
      id + '?page=' + (curPage - 1) + '&size=' + numberRecord);
  }
}
