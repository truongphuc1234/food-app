import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AngularFireStorage} from '@angular/fire/storage';

@Injectable({
  providedIn: 'root'
})
export class FirebaseService {

  constructor(public fireStorage: AngularFireStorage) {
  }

  saveImage(image, folder): Observable<any> {
      return this.fireStorage.upload(folder + image.name, image).snapshotChanges();
  }

  getDownloadUrl(path: string, folder): Observable<any> {
    return this.fireStorage.ref(folder + path).getDownloadURL();
  }
}
