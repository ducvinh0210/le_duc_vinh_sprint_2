import {IUser} from './i-user';

export interface ICustomer {
  id?: number;
  name?: string;
  dateOfBirth?: string;
  gender?: number;
  idCard?: string;
  email?: string;
  phoneNumber?: string;
  img?: string;
  address?: string;
  user?: IUser;


}
