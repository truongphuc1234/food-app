import {Food} from './food.model';
import {Account} from './account.model';

export class Rating {
  ratingId?: number;
  ratingLevel?: number;
  food?: Food;
  account?: Account;
}
