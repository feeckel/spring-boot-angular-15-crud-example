import {Component, OnDestroy, OnInit} from '@angular/core';
import {Room} from "../../models/room.model";
import {RoomService} from "../../services/room.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit, OnDestroy{

  subscriptions: Subscription[] = [];

  rooms?: Room[];
  currentIndex = -1;
  currentRoom: Room = {
    title: "",
    description: ""
  };

  constructor(private roomService: RoomService) { }

  ngOnInit(): void {
    this.retrieveRooms();
  }

  retrieveRooms(): void {
    this.subscriptions.push(
      this.roomService.getAll()
        .subscribe({
          next: (data) => {
            this.rooms = data;
          },
          error: (e) => console.error(e.message)
        })
    );
  }
  setActiveRoom(room: Room, index: number): void {
    this.currentRoom = room;
    this.currentIndex = index;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((e)=>{
      e.unsubscribe();
    })
  }


}
