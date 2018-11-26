import { Injectable } from "@angular/core";


@Injectable()
export class Storage {

  constructor() {}

  public retrieve(key: string): string {
    return localStorage.getItem(key);
  }

  public store(key: string, value: string): void {
    localStorage.setItem(key, value);
  }

  public clear(): void {
    localStorage.clear();
  }
}