import { Moment } from 'moment';
import { PaymentType } from 'app/shared/model/enumerations/payment-type.model';
import { PaymentStatus } from 'app/shared/model/enumerations/payment-status.model';

export interface IPayment {
  id?: number;
  placeDate?: Moment;
  totalPrice?: number;
  payment?: PaymentType;
  paymentReference?: string;
  paymentStatus?: PaymentStatus;
  message?: string;
}

export class Payment implements IPayment {
  constructor(
    public id?: number,
    public placeDate?: Moment,
    public totalPrice?: number,
    public payment?: PaymentType,
    public paymentReference?: string,
    public paymentStatus?: PaymentStatus,
    public message?: string
  ) {}
}
