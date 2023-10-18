import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Room} from "../../models/room.model";
import {RoomService} from "../../services/room.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";


@Component({
  selector: 'app-room-detail',
  templateUrl: './room-detail.component.html',
  styleUrls: ['./room-detail.component.css']
})
export class RoomDetailComponent implements OnInit, OnDestroy {
  roomForm!: FormGroup;
  subscriptions: Subscription[] = [];
  @Input() viewMode = false;
  @Input() currentRoom: Room = {
    title: '',
    description: ''
  };


  message = '';

  constructor(
    private fb: FormBuilder,
    public  roomService: RoomService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      const roomId = Number(this.route.snapshot.params["id"]);
      this.roomService.setRoomId(roomId);
      this.getRoom(roomId);
    }
  }

  getRoom(id: number): void {
    this.subscriptions.push(

    );
    this.roomService.get(id)
      .subscribe({
        next: (data) => {
          this.currentRoom = data;
          this.roomForm = this.fb.group({
            title: [this.currentRoom.title, [Validators.required, Validators.maxLength(50)]],
            description: [this.currentRoom.description, [Validators.required, Validators.maxLength(200)]],
            numberOfSeats: [this.currentRoom.numberOfSeats, [Validators.required, Validators.min(1)]],
            occupation: [this.currentRoom.occupation]
          });
        },
        error: (e) => console.error(e)
      });
  }

  get currentRoomForm(): Room {
    return this.roomForm.value;
  }

  onSubmit(): void {
    if (this.roomForm.valid) {
      this.subscriptions.push(
        this.roomService.roomId$.subscribe(id => {
          this.subscriptions.push(
            this.roomService.update(id, this.currentRoomForm as Room)
              .subscribe({
                next: (res) => {
                  this.message = 'This room was updated successfully!';
                },
                error: (e) => console.error(e)
              })
          );
        })
      );
    }
  }

  deleteRoom(): void {
    this.subscriptions.push(
      this.roomService.roomId$.subscribe(id => {
        this.subscriptions.push(
          this.roomService.delete(id)
            .subscribe({
              next: (res) => {
                this.router.navigate(['/rooms']);
              },
              error: (e) => console.error(e)
            })
        )
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((e)=>{
      e.unsubscribe();
    })
  }

}
