import { IOrder } from 'app/shared/model/order.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface ICustomer {
  id?: number;
  gender?: Gender;
  phone?: string;
  addressLine1?: string;
  addressLine2?: string;
  city?: string;
  country?: string;
  userLogin?: string;
  userId?: number;
  orders?: IOrder[];
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public gender?: Gender,
    public phone?: string,
    public addressLine1?: string,
    public addressLine2?: string,
    public city?: string,
    public country?: string,
    public userLogin?: string,
    public userId?: number,
    public orders?: IOrder[]
  ) {}
}
