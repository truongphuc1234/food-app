import {Food} from './food.model';
import {Account} from './account.model';

export interface FoodComment {
  commentId?: number;
  commentContent?: string;
  commentImage?: string;
  food?: Food;
  account?: Account;
  commentTime?: Date;
}
