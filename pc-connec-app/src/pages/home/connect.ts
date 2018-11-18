import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AppServices } from '../../service/services';
import { LoginPage } from '../login/login';
import { Storage } from '@ionic/storage';

@Component({
  selector: 'connect-home',
  templateUrl: 'connect.html'
})
export class ConnectPage {

  private address: string = '';
  private port: string = '';

  constructor(public nav: NavController,
              private service: AppServices,
              private storage: Storage) { }

  public checkConnection() {
    if (this.address === '' || this.port === '') {
      alert("Address and Port can not be empty");
      return;
    }
    this.service
      .checkConnection(this.address.trim(), this.port.trim())
      .subscribe(response => {
        if (response.status) {
          this.storage.set('base-address', `http://${this.address}:${this.port}`);
          this.nav.push(LoginPage, {});
        }
      },
      error => {
        alert('Unable to connect to the given address');
      });
  }

}
