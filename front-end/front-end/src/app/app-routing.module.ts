import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './component/home/home.component';
import {LoginComponent} from './component/login/login.component';
import {ShoeListComponent} from './component/shoe-list/shoe-list.component';
import {ShoeCartComponent} from './component/shoe-cart/shoe-cart.component';
import {ShoeDetailComponent} from './component/shoe-detail/shoe-detail.component';
import {ShoeHistoryComponent} from './component/shoe-history/shoe-history.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'list', component: ShoeListComponent},
  {path: 'cart', component: ShoeCartComponent},
  {path: 'detail/:id', component: ShoeDetailComponent},
  {path: 'history', component: ShoeHistoryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
