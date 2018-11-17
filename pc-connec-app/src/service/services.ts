import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Response } from '../model/models';

@Injectable()
export class AppServices {

  constructor(private httpClient: HttpClient) {}

  public checkConnection(serverAddress: string, serverPort: string): Observable<Response> {
    return this.httpClient.get<Response>(`http://${serverAddress}:${serverPort}/test`);
  }
}