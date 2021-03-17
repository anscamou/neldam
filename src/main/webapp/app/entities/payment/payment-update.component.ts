import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPayment, Payment } from 'app/shared/model/payment.model';
import { PaymentService } from './payment.service';

@Component({
  selector: 'jhi-payment-update',
  templateUrl: './payment-update.component.html',
})
export class PaymentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    placeDate: [null, [Validators.required]],
    totalPrice: [null, [Validators.required, Validators.min(0)]],
    payment: [null, [Validators.required]],
    paymentReference: [],
    paymentStatus: [],
    message: [],
  });

  constructor(protected paymentService: PaymentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ payment }) => {
      if (!payment.id) {
        const today = moment().startOf('day');
        payment.placeDate = today;
      }

      this.updateForm(payment);
    });
  }

  updateForm(payment: IPayment): void {
    this.editForm.patchValue({
      id: payment.id,
      placeDate: payment.placeDate ? payment.placeDate.format(DATE_TIME_FORMAT) : null,
      totalPrice: payment.totalPrice,
      payment: payment.payment,
      paymentReference: payment.paymentReference,
      paymentStatus: payment.paymentStatus,
      message: payment.message,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const payment = this.createFromForm();
    if (payment.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentService.update(payment));
    } else {
      this.subscribeToSaveResponse(this.paymentService.create(payment));
    }
  }

  private createFromForm(): IPayment {
    return {
      ...new Payment(),
      id: this.editForm.get(['id'])!.value,
      placeDate: this.editForm.get(['placeDate'])!.value ? moment(this.editForm.get(['placeDate'])!.value, DATE_TIME_FORMAT) : undefined,
      totalPrice: this.editForm.get(['totalPrice'])!.value,
      payment: this.editForm.get(['payment'])!.value,
      paymentReference: this.editForm.get(['paymentReference'])!.value,
      paymentStatus: this.editForm.get(['paymentStatus'])!.value,
      message: this.editForm.get(['message'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPayment>>): void {
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
