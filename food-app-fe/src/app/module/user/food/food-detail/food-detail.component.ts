import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FoodBackendService} from '../../../../service/food/food-backend.service';
import {Food, FoodDetail} from '../../../../model/food.model';
import {TokenStorageService} from '../../../../service/authorization/token-storage';
import {RatingBackendService} from '../../../../service/food/rating-backend.service';
import {Rating} from '../../../../model/rating.model';
import {Account} from '../../../../model/account.model';
import {MatDialog} from '@angular/material/dialog';
import {CustomerBackendService} from '../../../../service/customer/customer-backend.service';
import {FormBuilder, FormControl} from '@angular/forms';
import {AngularFireStorage} from '@angular/fire/storage';
import {finalize, ignoreElements, last, mergeMap} from 'rxjs/operators';
import {concat, Observable} from 'rxjs';
import {FirebaseService} from '../../../../service/firebase/firebase.service';
import {CommentBackendService} from '../../../../service/food/comment-backend.service';
import {FoodComment} from '../../../../model/food-comment.model';

@Component({
  selector: 'app-food-detail',
  templateUrl: './food-detail.component.html',
  styleUrls: ['./food-detail.component.scss']
})
export class FoodDetailComponent implements OnInit {
  public foodId: number;
  public foodDetail: FoodDetail;
  public isRatingOpen = false;
  public isAddCartOpen = false;
  public rating = 0;
  public quantity = 0;
  public account: Account;
  public commentForm: FormControl;

  public imageCommentFile: File = null;
  public imageCommentUrl: string = null;
  public commentList: Array<FoodComment>;

  constructor(public activatedRoute: ActivatedRoute,
              public foodBackendService: FoodBackendService,
              public tokenStorageService: TokenStorageService,
              public ratingBackendService: RatingBackendService,
              public formBuilder: FormBuilder,
              public firebaseService: FirebaseService,
              public commentBackendService: CommentBackendService) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(param => {
      this.foodId = param.id;
    });

    this.account = this.tokenStorageService.getAccount();

    this.foodBackendService.getFoodDetail(this.foodId).subscribe(data => {
      this.foodDetail = data;
    });

    this.commentForm = this.formBuilder.control('');


    this.commentBackendService.getCommentById(this.foodId).subscribe(data => {
      this.commentList = data;
    });
  }

  setRating(e) {
    this.rating = e.target.dataset.index;
  }

  submitRating() {
    const formRating: Rating = {
      account: {
        accountId: this.tokenStorageService.getAccount().accountId
      },
      ratingLevel: this.rating,
      food: {
        foodId: this.foodId
      }
    };
    this.ratingBackendService.saveRating(formRating).subscribe(data => {
      this.foodDetail.foodSummary.ratingAverage = data.ratingAverage;
      this.foodDetail.foodSummary.totalRating = data.totalRating;
    });
    this.isRatingOpen = false;
  }


  showImage(event) {
    if (event.target.files && event.target.files[0]) {
      const file = event.target.files[0].name;
      if (file.match(/.*\.(png|jpeg|jpg|PNG|JPEG|JPG)$/)) {
        const reader = new FileReader();
        reader.onload = (e: any) => this.imageCommentUrl = e.target.result;
        reader.readAsDataURL(event.target.files[0]);
        this.imageCommentFile = event.target.files[0];
        return;
      }
    }
  }

  submitComment() {
    if (this.imageCommentFile){
      concat(this.firebaseService.saveImage(this.imageCommentFile, 'comment').pipe(ignoreElements()),
        this.firebaseService.getDownloadUrl(this.imageCommentFile.name, 'comment'))
        .subscribe(url => {
          this.imageCommentUrl = url;
          console.log(this.setComment());
          this.commentBackendService.saveComment(this.setComment()).subscribe(data => {
            this.commentList = data;
          });
        });
    } else {
      this.commentBackendService.saveComment(this.setComment()).subscribe(data => {
        this.commentList = data;
      });
    }
  }

  private setComment(): FoodComment {
    return {
      commentContent: this.commentForm.value,
      commentImage: this.imageCommentUrl ? this.imageCommentUrl : null,
      food: {
        foodId: this.foodId
      },
      account: {
        accountId: this.account.accountId
      },
      commentTime: new Date()
    };
  }

}
