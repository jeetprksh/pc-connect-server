import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS }   from '@angular/common/http';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { App } from './app.component';
import { ConnectPage } from '../pages/home/connect';
import { AppServices } from '../service/services';
import { LoginPage } from '../pages/login/login';
import { ItemsPage } from '../pages/items/items';
import { StreamingMedia } from '@ionic-native/streaming-media';
import { File } from '@ionic-native/file';
import { Storage } from '../service/storage.service';
import { AddHeaderInterceptor } from '../service/add-header.interceptor';

/*
* @author Jeet Prakash
* */
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
    IonicModule.forRoot(App)
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
    StreamingMedia,
    Storage,
    File,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AddHeaderInterceptor,
      multi: true,
      deps: [Storage]
    },
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
