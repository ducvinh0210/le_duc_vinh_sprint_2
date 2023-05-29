import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DataResult} from '../model/data-result';
import {IShoeDto} from '../model/i-shoe-dto';
import {IType} from '../model/i-type';

const API_URL = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})
export class ShoeService {


  constructor(private httpClient: HttpClient) {
  }


  showListShoe(curPage: number, numberRecord: number, nameShoe: string, typeShoe: string,
               priceStart: any, priceEnd: any, sortBy: string): Observable<DataResult<IShoeDto>> {

    return this.httpClient.get<DataResult<IShoeDto>>(API_URL + '/shoes/list-' + sortBy + '/' +
      nameShoe + '&' + typeShoe + '&' + priceStart + '&' +
      priceEnd + '?page=' + (curPage - 1) + '&size=' + numberRecord);
  }


  showListShoeType(): Observable<IType[]> {
    return this.httpClient.get<IType[]>(API_URL + '/shoes/list-shoe-type');
  }
}
