import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {

  code: string;

  constructor(public navCtrl: NavController,
              public navParams: NavParams) { }

  verify(): void {
    console.log('verify', this.code);
  }

}
