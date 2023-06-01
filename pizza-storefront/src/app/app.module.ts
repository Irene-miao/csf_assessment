import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { OrdersComponent } from './components/orders.component';

import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http'

const appRoutes: Routes = [
  { path: '',  component: MainComponent, title: 'Main'},
  { path: 'orders/:email', component: OrdersComponent, title: 'Orders'},
  { path: '**', redirectTo: '/', pathMatch: 'full'}
]





@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    OrdersComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, { useHash: true})
  ],

  providers: [ ],
  bootstrap: [AppComponent]
})
export class AppModule { }
