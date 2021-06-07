import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {FoodListComponent} from './module/user/food/food-list/food-list.component';
import {FoodDetailComponent} from './module/user/food/food-detail/food-detail.component';
import {LoginComponent} from './module/board/login/login.component';


const routes: Routes = [
  {
    path: 'food-list', component: FoodListComponent
  },
  {
    path: 'food-detail/:id', component: FoodDetailComponent
  },
  {
    path: 'login', component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
