import { element, by, ElementFinder } from 'protractor';

export class PaymentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-payment div table .btn-danger'));
  title = element.all(by.css('jhi-payment div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class PaymentUpdatePage {
  pageTitle = element(by.id('jhi-payment-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  placeDateInput = element(by.id('field_placeDate'));
  totalPriceInput = element(by.id('field_totalPrice'));
  paymentSelect = element(by.id('field_payment'));
  paymentReferenceInput = element(by.id('field_paymentReference'));
  paymentStatusSelect = element(by.id('field_paymentStatus'));
  messageInput = element(by.id('field_message'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setPlaceDateInput(placeDate: string): Promise<void> {
    await this.placeDateInput.sendKeys(placeDate);
  }

  async getPlaceDateInput(): Promise<string> {
    return await this.placeDateInput.getAttribute('value');
  }

  async setTotalPriceInput(totalPrice: string): Promise<void> {
    await this.totalPriceInput.sendKeys(totalPrice);
  }

  async getTotalPriceInput(): Promise<string> {
    return await this.totalPriceInput.getAttribute('value');
  }

  async setPaymentSelect(payment: string): Promise<void> {
    await this.paymentSelect.sendKeys(payment);
  }

  async getPaymentSelect(): Promise<string> {
    return await this.paymentSelect.element(by.css('option:checked')).getText();
  }

  async paymentSelectLastOption(): Promise<void> {
    await this.paymentSelect.all(by.tagName('option')).last().click();
  }

  async setPaymentReferenceInput(paymentReference: string): Promise<void> {
    await this.paymentReferenceInput.sendKeys(paymentReference);
  }

  async getPaymentReferenceInput(): Promise<string> {
    return await this.paymentReferenceInput.getAttribute('value');
  }

  async setPaymentStatusSelect(paymentStatus: string): Promise<void> {
    await this.paymentStatusSelect.sendKeys(paymentStatus);
  }

  async getPaymentStatusSelect(): Promise<string> {
    return await this.paymentStatusSelect.element(by.css('option:checked')).getText();
  }

  async paymentStatusSelectLastOption(): Promise<void> {
    await this.paymentStatusSelect.all(by.tagName('option')).last().click();
  }

  async setMessageInput(message: string): Promise<void> {
    await this.messageInput.sendKeys(message);
  }

  async getMessageInput(): Promise<string> {
    return await this.messageInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class PaymentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-payment-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-payment'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
