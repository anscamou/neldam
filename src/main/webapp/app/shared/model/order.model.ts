import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';

export interface IOrder {
  id?: number;
  description?: string;
  latFrom?: number;
  longFrom?: number;
  addrFrom?: string;
  phoneTo?: string;
  latTo?: number;
  longTo?: number;
  addrTo?: string;
  orderStatus?: OrderStatus;
  orderId?: number;
  customerId?: number;
}

export class Order implements IOrder {
  constructor(
    public id?: number,
    public description?: string,
    public latFrom?: number,
    public longFrom?: number,
    public addrFrom?: string,
    public phoneTo?: string,
    public latTo?: number,
    public longTo?: number,
    public addrTo?: string,
    public orderStatus?: OrderStatus,
    public orderId?: number,
    public customerId?: number
  ) {}
}
