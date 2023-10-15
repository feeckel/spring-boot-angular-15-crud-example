import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RoomListComponent} from "./components/room-list/room-list.component";
import {RoomDetailComponent} from "./components/room-detail/room-detail.component";
import {RoomAddComponent} from "./components/room-add/room-add.component";

const routes: Routes = [
  { path: '', redirectTo: 'rooms', pathMatch: 'full' },
  { path: 'rooms', component: RoomListComponent },
  { path: 'rooms/:id', component: RoomDetailComponent },
  { path: 'add', component: RoomAddComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
