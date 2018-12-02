import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ItemsPage } from '../items/items';
import { AppServices } from '../../service/services';
import { Storage } from '../../service/storage.service';
import { Token } from '../../model/models';

/*
* @author Jeet Prakash
* */
@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {

  name: string;
  code: string;

  constructor(public nav: NavController,
              public navParams: NavParams,
              private service: AppServices,
              private storage: Storage) { }

  verify(): void {
    let name = this.name.trim();
    let encodedCode = btoa(this.code.trim());
    this.service.verifyUser(name, encodedCode).subscribe(response => {
      if (response.status){
        let token = response.data as Token;
        this.storage.store('token', token.token);
        this.nav.push(ItemsPage, { root: undefined, path: undefined });
      } else {
        alert(response.message);
      }
    }, err => alert('Can not verify code. ' + err));
  }

}
