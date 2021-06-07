import {FoodStatus} from './food-status.model';
import {Category} from './category.model';
import {FoodSummary} from './review-info.model';

export interface Food {
  foodId: number;
  foodName?: string;
  foodPrice?: number;
  foodStatus?: FoodStatus;
  foodTimePost?: Date;
  foodImage?: string;
  foodDescription?: string;
  foodUpdateTime?: Date;
  discount?: number;
  averageRating?: number;
}

export interface FoodDetail {
  food: Food;
  categories: Category[];
  foodSummary: FoodSummary;
}
