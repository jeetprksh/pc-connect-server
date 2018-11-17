import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AppServices } from '../../service/services';
import { LoginPage } from '../login/login';

@Component({
  selector: 'connect-home',
  templateUrl: 'connect.html'
})
export class ConnectPage {

  private address: string = '';
  private port: string = '';

  constructor(public navCtrl: NavController,
              private service: AppServices) { }

  public checkConnection() {
    if (this.address === '' || this.port === '') {
      alert("Address and Port can not be empty");
      return;
    }
    this.service
      .checkConnection(this.address.trim(), this.port.trim())
      .subscribe(response => {
        if (response.status){
          this.navCtrl.push(LoginPage, {});
        }
      },
      error => {
        alert('Unable to connect error');
      });
  }

}
