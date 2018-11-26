import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Response } from '../model/models';
import { Storage } from "./storage.service";

/*
* @author Jeet Prakash
* */
@Injectable()
export class AppServices {

  constructor(private httpClient: HttpClient,
              private storage: Storage) {}

  public checkConnection(serverAddress: string, serverPort: string): Observable<Response> {
    return this.httpClient.get<Response>(`http://${serverAddress}:${serverPort}/connect/test`);
  }

  public verifyUser(name: string, encoded: string): Observable<Response> {
    let baseUrl = this.storage.retrieve('base-address');
    let url = baseUrl + `/user/code/verify?name=${name}&encoded=${encoded}`;
    return this.httpClient.get<Response>(url);
  }

  public getItems(root: string, path: string): Observable<Response> {
    let baseUrl = this.storage.retrieve('base-address');
    let url = root ? baseUrl + `/items?root=${root}&path=${path}` : baseUrl + `/items`;
    return this.httpClient.get<Response>(url);
  }

  public downloadItem(root: string, path: string): Observable<Blob> {
    let baseUrl = this.storage.retrieve('base-address');
    let url = baseUrl + `/item/download?root=${root}&path=${path}&download`;
    return this.httpClient.get(url, {responseType: 'blob'});
  }

}