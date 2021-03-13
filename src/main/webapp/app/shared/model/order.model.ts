import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';
import { Payment } from 'app/shared/model/enumerations/payment.model';

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
  payment?: Payment;
  orderId?: number;
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
    public payment?: Payment,
    public orderId?: number
  ) {}
}
