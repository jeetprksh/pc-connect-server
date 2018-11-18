import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule }   from '@angular/common/http';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { App } from './app.component';
import { ConnectPage } from '../pages/home/connect';
import { AppServices } from '../service/services';
import { LoginPage } from '../pages/login/login';
import { ItemsPage } from '../pages/items/items';
import { IonicStorageModule } from '@ionic/storage';

@NgModule({
  declarations: [
    App,
    ConnectPage,
    LoginPage,
    ItemsPage
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(App),
    IonicStorageModule.forRoot({
      name: 'db',
      driverOrder: ['localstorage', 'websql']
    })
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    App,
    ConnectPage,
    LoginPage,
    ItemsPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    AppServices,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
