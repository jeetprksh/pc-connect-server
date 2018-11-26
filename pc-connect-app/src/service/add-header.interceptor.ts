import { HttpEvent } from '@angular/common/http';
import { HttpInterceptor } from '@angular/common/http';
import { HttpHandler } from '@angular/common/http';
import { HttpRequest } from '@angular/common/http';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Storage } from './storage.service';
import 'rxjs/add/operator/do';

export class AddHeaderInterceptor implements HttpInterceptor {

  constructor(private storage: Storage){}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = (this.storage.retrieve('token') != null) ? this.storage.retrieve('token') : '';

    const clonedRequest = req.clone({ headers: req.headers.set('token', token) });

    return next.handle(clonedRequest).do(event => {
      if(event instanceof HttpResponse && event.body.message === 'Invalid Token') {
        this.storage.clear();
        alert('Looks like your verification code expired, you would need to login again');
        location.reload();
      }
    });
  }
}
