import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Response } from '../model/models';
import { Storage } from "@ionic/storage";

@Injectable()
export class AppServices {

  constructor(private httpClient: HttpClient,
              private storage: Storage) {}

  public checkConnection(serverAddress: string, serverPort: string): Observable<Response> {
    return this.httpClient.get<Response>(`http://${serverAddress}:${serverPort}/test`);
  }

  public async getItems(root: string, path: string): Promise<Response> {
    let baseUrl = await this.storage.get('base-address');
    let url = root ? baseUrl + `/items?root=${root}&path=${path}` : baseUrl + `/items`;
    return this.httpClient.get<Response>(url).toPromise();
  }

  public async downloadItem(root: string, path: string): Promise<Blob> {
    let baseUrl = await this.storage.get('base-address');
    let url = baseUrl + `/item/download?root=${root}&path=${path}&download`;
    return this.httpClient.get(url, {responseType: 'blob'}).toPromise();
  }

}