import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AppServices } from '../../service/services';
import { Item } from '../../model/models';

@IonicPage()
@Component({
  selector: 'page-items',
  templateUrl: 'items.html',
})
export class ItemsPage {

  items: Item[];

  constructor(public navCtrl: NavController,
              public navParams: NavParams,
              private service: AppServices) {
    this.loadItems(navParams.get('root'), navParams.get('path'));
  }

  loadItems(root: string, path: string) {
    this.service.getItems(root, path).then(res => {
      console.log(res);
      this.items = res.data as Item[];
    }, err =>{
      alert('Unable to list Items');
    });
  }

  itemTapped(item: Item): void {
    if(!item.directory) return;
    this.navCtrl.push(ItemsPage, {root: item.rootAlias, path: item.path});
  }

  itemDownload(item: Item): void {
    if(item.directory) return;
    console.log(item);
    this.service
      .downloadItem(item.rootAlias, item.path)
      .then(blob => console.log(blob))
      .catch(err => console.log(err));
  }

}
