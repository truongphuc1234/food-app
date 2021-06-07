export interface FoodQueryDTO {
  size: number;
  page: number;
  categories?: Array<number>;
  statuses?: Array<number>;
  name?: string;
  highPrice?: number;
  lowPrice?: number;
  lowRating?: number;
  highRating?:number;
}
