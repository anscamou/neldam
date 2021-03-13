import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrder, Order } from 'app/shared/model/order.model';
import { OrderService } from './order.service';

@Component({
  selector: 'jhi-order-update',
  templateUrl: './order-update.component.html',
})
export class OrderUpdateComponent implements OnInit {
  isSaving = false;

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
    payment: [null, [Validators.required]],
  });

  constructor(protected orderService: OrderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ order }) => {
      this.updateForm(order);
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
      payment: order.payment,
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
      payment: this.editForm.get(['payment'])!.value,
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
}
