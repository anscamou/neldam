import { element, by, ElementFinder } from 'protractor';

export class OrderComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-order div table .btn-danger'));
  title = element.all(by.css('jhi-order div h2#page-heading span')).first();
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

export class OrderUpdatePage {
  pageTitle = element(by.id('jhi-order-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  descriptionInput = element(by.id('field_description'));
  latFromInput = element(by.id('field_latFrom'));
  longFromInput = element(by.id('field_longFrom'));
  addrFromInput = element(by.id('field_addrFrom'));
  phoneToInput = element(by.id('field_phoneTo'));
  latToInput = element(by.id('field_latTo'));
  longToInput = element(by.id('field_longTo'));
  addrToInput = element(by.id('field_addrTo'));
  orderStatusSelect = element(by.id('field_orderStatus'));

  orderSelect = element(by.id('field_order'));
  customerSelect = element(by.id('field_customer'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDescriptionInput(description: string): Promise<void> {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput(): Promise<string> {
    return await this.descriptionInput.getAttribute('value');
  }

  async setLatFromInput(latFrom: string): Promise<void> {
    await this.latFromInput.sendKeys(latFrom);
  }

  async getLatFromInput(): Promise<string> {
    return await this.latFromInput.getAttribute('value');
  }

  async setLongFromInput(longFrom: string): Promise<void> {
    await this.longFromInput.sendKeys(longFrom);
  }

  async getLongFromInput(): Promise<string> {
    return await this.longFromInput.getAttribute('value');
  }

  async setAddrFromInput(addrFrom: string): Promise<void> {
    await this.addrFromInput.sendKeys(addrFrom);
  }

  async getAddrFromInput(): Promise<string> {
    return await this.addrFromInput.getAttribute('value');
  }

  async setPhoneToInput(phoneTo: string): Promise<void> {
    await this.phoneToInput.sendKeys(phoneTo);
  }

  async getPhoneToInput(): Promise<string> {
    return await this.phoneToInput.getAttribute('value');
  }

  async setLatToInput(latTo: string): Promise<void> {
    await this.latToInput.sendKeys(latTo);
  }

  async getLatToInput(): Promise<string> {
    return await this.latToInput.getAttribute('value');
  }

  async setLongToInput(longTo: string): Promise<void> {
    await this.longToInput.sendKeys(longTo);
  }

  async getLongToInput(): Promise<string> {
    return await this.longToInput.getAttribute('value');
  }

  async setAddrToInput(addrTo: string): Promise<void> {
    await this.addrToInput.sendKeys(addrTo);
  }

  async getAddrToInput(): Promise<string> {
    return await this.addrToInput.getAttribute('value');
  }

  async setOrderStatusSelect(orderStatus: string): Promise<void> {
    await this.orderStatusSelect.sendKeys(orderStatus);
  }

  async getOrderStatusSelect(): Promise<string> {
    return await this.orderStatusSelect.element(by.css('option:checked')).getText();
  }

  async orderStatusSelectLastOption(): Promise<void> {
    await this.orderStatusSelect.all(by.tagName('option')).last().click();
  }

  async orderSelectLastOption(): Promise<void> {
    await this.orderSelect.all(by.tagName('option')).last().click();
  }

  async orderSelectOption(option: string): Promise<void> {
    await this.orderSelect.sendKeys(option);
  }

  getOrderSelect(): ElementFinder {
    return this.orderSelect;
  }

  async getOrderSelectedOption(): Promise<string> {
    return await this.orderSelect.element(by.css('option:checked')).getText();
  }

  async customerSelectLastOption(): Promise<void> {
    await this.customerSelect.all(by.tagName('option')).last().click();
  }

  async customerSelectOption(option: string): Promise<void> {
    await this.customerSelect.sendKeys(option);
  }

  getCustomerSelect(): ElementFinder {
    return this.customerSelect;
  }

  async getCustomerSelectedOption(): Promise<string> {
    return await this.customerSelect.element(by.css('option:checked')).getText();
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

export class OrderDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-order-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-order'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
