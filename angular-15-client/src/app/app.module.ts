import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RoomListComponent } from './components/room-list/room-list.component';
import { RoomDetailComponent } from './components/room-detail/room-detail.component';
import { RoomAddComponent } from './components/room-add/room-add.component';

@NgModule({
  declarations: [
    AppComponent,
    RoomListComponent,
    RoomDetailComponent,
    RoomAddComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
