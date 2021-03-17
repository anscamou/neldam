import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IOrder, Order } from 'app/shared/model/order.model';
import { OrderService } from './order.service';
import { IPayment } from 'app/shared/model/payment.model';
import { PaymentService } from 'app/entities/payment/payment.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';

type SelectableEntity = IPayment | ICustomer;

@Component({
  selector: 'jhi-order-update',
  templateUrl: './order-update.component.html',
})
export class OrderUpdateComponent implements OnInit {
  isSaving = false;
  orders: IPayment[] = [];
  customers: ICustomer[] = [];

  editForm = this.fb.group({
    id: [],
    description: [],
    latFrom: [null, [Validators.required]],
    longFrom: [null, [Validators.required]],
    addrFrom: [],
    phoneTo: [null, [Validators.required]],
    latTo: [null, [Validators.required]],
    longTo: [null, [Validators.required]],
    addrTo: [],
    orderStatus: [null, [Validators.required]],
    orderId: [],
    customerId: [],
  });

  constructor(
    protected orderService: OrderService,
    protected paymentService: PaymentService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ order }) => {
      this.updateForm(order);

      this.paymentService
        .query({ filter: 'order-is-null' })
        .pipe(
          map((res: HttpResponse<IPayment[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPayment[]) => {
          if (!order.orderId) {
            this.orders = resBody;
          } else {
            this.paymentService
              .find(order.orderId)
              .pipe(
                map((subRes: HttpResponse<IPayment>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPayment[]) => (this.orders = concatRes));
          }
        });

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));
    });
  }

  updateForm(order: IOrder): void {
    this.editForm.patchValue({
      id: order.id,
      description: order.description,
      latFrom: order.latFrom,
      longFrom: order.longFrom,
      addrFrom: order.addrFrom,
      phoneTo: order.phoneTo,
      latTo: order.latTo,
      longTo: order.longTo,
      addrTo: order.addrTo,
      orderStatus: order.orderStatus,
      orderId: order.orderId,
      customerId: order.customerId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const order = this.createFromForm();
    if (order.id !== undefined) {
      this.subscribeToSaveResponse(this.orderService.update(order));
    } else {
      this.subscribeToSaveResponse(this.orderService.create(order));
    }
  }

  private createFromForm(): IOrder {
    return {
      ...new Order(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      latFrom: this.editForm.get(['latFrom'])!.value,
      longFrom: this.editForm.get(['longFrom'])!.value,
      addrFrom: this.editForm.get(['addrFrom'])!.value,
      phoneTo: this.editForm.get(['phoneTo'])!.value,
      latTo: this.editForm.get(['latTo'])!.value,
      longTo: this.editForm.get(['longTo'])!.value,
      addrTo: this.editForm.get(['addrTo'])!.value,
      orderStatus: this.editForm.get(['orderStatus'])!.value,
      orderId: this.editForm.get(['orderId'])!.value,
      customerId: this.editForm.get(['customerId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrder>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
