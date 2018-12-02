import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { ConnectPage } from '../pages/home/connect';
import { File } from '@ionic-native/file';

/*
* @author Jeet Prakash
* */
@Component({
  templateUrl: 'app.html'
})
export class App {
  rootPage:any = ConnectPage;

  constructor(platform: Platform,
              statusBar: StatusBar,
              splashScreen: SplashScreen,
              private file: File) {
    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      this.file.createDir(this.file.externalRootDirectory, 'pc-connect', false)
                .then(res => console.log('Root directory created'))
                .catch(err => console.log('Unable to create root directory'));
      statusBar.hide();
      splashScreen.hide();
    });
  }
}
