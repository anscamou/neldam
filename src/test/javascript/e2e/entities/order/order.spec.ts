import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrderComponentsPage, OrderDeleteDialog, OrderUpdatePage } from './order.page-object';

const expect = chai.expect;

describe('Order e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let orderComponentsPage: OrderComponentsPage;
  let orderUpdatePage: OrderUpdatePage;
  let orderDeleteDialog: OrderDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Orders', async () => {
    await navBarPage.goToEntity('order');
    orderComponentsPage = new OrderComponentsPage();
    await browser.wait(ec.visibilityOf(orderComponentsPage.title), 5000);
    expect(await orderComponentsPage.getTitle()).to.eq('neldamApp.order.home.title');
    await browser.wait(ec.or(ec.visibilityOf(orderComponentsPage.entities), ec.visibilityOf(orderComponentsPage.noResult)), 1000);
  });

  it('should load create Order page', async () => {
    await orderComponentsPage.clickOnCreateButton();
    orderUpdatePage = new OrderUpdatePage();
    expect(await orderUpdatePage.getPageTitle()).to.eq('neldamApp.order.home.createOrEditLabel');
    await orderUpdatePage.cancel();
  });

  it('should create and save Orders', async () => {
    const nbButtonsBeforeCreate = await orderComponentsPage.countDeleteButtons();

    await orderComponentsPage.clickOnCreateButton();

    await promise.all([
      orderUpdatePage.setDescriptionInput('description'),
      orderUpdatePage.setLatFromInput('5'),
      orderUpdatePage.setLongFromInput('5'),
      orderUpdatePage.setAddrFromInput('addrFrom'),
      orderUpdatePage.setPhoneToInput('phoneTo'),
      orderUpdatePage.setLatToInput('5'),
      orderUpdatePage.setLongToInput('5'),
      orderUpdatePage.setAddrToInput('addrTo'),
      orderUpdatePage.orderStatusSelectLastOption(),
      orderUpdatePage.paymentSelectLastOption(),
      orderUpdatePage.orderSelectLastOption(),
    ]);

    expect(await orderUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await orderUpdatePage.getLatFromInput()).to.eq('5', 'Expected latFrom value to be equals to 5');
    expect(await orderUpdatePage.getLongFromInput()).to.eq('5', 'Expected longFrom value to be equals to 5');
    expect(await orderUpdatePage.getAddrFromInput()).to.eq('addrFrom', 'Expected AddrFrom value to be equals to addrFrom');
    expect(await orderUpdatePage.getPhoneToInput()).to.eq('phoneTo', 'Expected PhoneTo value to be equals to phoneTo');
    expect(await orderUpdatePage.getLatToInput()).to.eq('5', 'Expected latTo value to be equals to 5');
    expect(await orderUpdatePage.getLongToInput()).to.eq('5', 'Expected longTo value to be equals to 5');
    expect(await orderUpdatePage.getAddrToInput()).to.eq('addrTo', 'Expected AddrTo value to be equals to addrTo');

    await orderUpdatePage.save();
    expect(await orderUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await orderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Order', async () => {
    const nbButtonsBeforeDelete = await orderComponentsPage.countDeleteButtons();
    await orderComponentsPage.clickOnLastDeleteButton();

    orderDeleteDialog = new OrderDeleteDialog();
    expect(await orderDeleteDialog.getDialogTitle()).to.eq('neldamApp.order.delete.question');
    await orderDeleteDialog.clickOnConfirmButton();

    expect(await orderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
