import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AppServices } from '../../service/services';
import { Item } from '../../model/models';
import { StreamingMedia, StreamingVideoOptions } from '@ionic-native/streaming-media';
import { File } from '@ionic-native/file';
import { Storage } from '../../service/storage.service';

/*
* @author Jeet Prakash
* */
@IonicPage()
@Component({
  selector: 'page-items',
  templateUrl: 'items.html',
})
export class ItemsPage {

  items: Item[];

  constructor(public navCtrl: NavController,
              public navParams: NavParams,
              private service: AppServices,
              private streamMedia: StreamingMedia,
              private storage: Storage,
              private file: File) {
    this.loadItems(navParams.get('root'), navParams.get('path'));
  }

  loadItems(root: string, path: string) {
    this.service.getItems(root, path).subscribe(res => {
      this.items = res.data as Item[];
    }, err =>{
      alert('Unable to list Items');
    });
  }

  itemTapped(item: Item) {
    if(item.directory) {
      this.navCtrl.push(ItemsPage, {root: item.rootAlias, path: item.path});
    } else {
      let options: StreamingVideoOptions = {
        successCallback: () => { alert('Video played') },
        errorCallback: (e) => { alert('Error streaming' + e) },
        orientation: 'landscape',
        shouldAutoClose: true,
        controls: true
      };
      let baseUrl = this.storage.retrieve('base-url');
      let mediaUrl = baseUrl + `/item/download?root=${item.rootAlias}&path=${item.path}&download`;
      this.streamMedia.playVideo(mediaUrl, options);
    }
  }

  itemDownload(item: Item): void {
    if(item.directory) return;
    this.service
      .downloadItem(item.rootAlias, item.path).subscribe(blob => {
        let fileName = item.path.split('/')[item.path.split('/').length - 1];
        fileName = fileName ? fileName : item.path;
        this.file.writeFile(this.file.externalRootDirectory + '/pc-connect', fileName, blob, {replace: false}).then(res => {
          alert('File saved');
        }).catch(error => {
          error.message === 'PATH_EXISTS_ERR' ?
              alert('You already have a file with same name on this location.') : alert('Can not save the file');
        });
      }, err => alert('Unable to download ' + err));
  }

}
