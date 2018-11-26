import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ItemsPage } from '../items/items';

/*
* @author Jeet Prakash
* */
@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {

  code: string;

  constructor(public nav: NavController,
              public navParams: NavParams) { }

  verify(): void {
    console.log('verify', this.code);
    this.nav.push(ItemsPage, { root: undefined, path: undefined });
  }

}
