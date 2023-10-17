import {Component, Input, OnInit} from '@angular/core';
import {Room} from "../../models/room.model";
import {RoomService} from "../../services/room.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-room-detail',
  templateUrl: './room-detail.component.html',
  styleUrls: ['./room-detail.component.css']
})
export class RoomDetailComponent implements OnInit {
  roomForm!: FormGroup;
  @Input() viewMode = false;

  @Input() currentRoom: Room = {
    title: '',
    description: ''
  };

  message = '';

  constructor(
    private fb: FormBuilder,
    private roomService: RoomService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getRoom(this.route.snapshot.params["id"]);
    }
    this.roomForm = this.fb.group({
      title: ['', [Validators.required, Validators.maxLength(50)]],
      description: ['', [Validators.required, Validators.maxLength(200)]],
      numberOfSeats: [null, [Validators.required, Validators.min(1)]],
      occupation: [false]
    });
  }

  getRoom(id: number): void {
    this.roomService.get(id)
      .subscribe({
        next: (data) => {
          this.currentRoom = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  get currentRoomForm() {
    return this.roomForm.value;
  }

  onSubmit(): void {
    if (this.roomForm.valid) {
      console.log("Form Submitted!", this.roomForm.value);
      this.roomService.update(this.currentRoom.id?this.currentRoom.id:0, this.currentRoomForm as Room)
        .subscribe({
          next: (res) => {
            this.message =  'This room was updated successfully!';
          },
          error: (e) => console.error(e)
        });
    }
  }

  deleteRoom(): void {
    this.roomService.delete(this.currentRoom.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/rooms']);
        },
        error: (e) => console.error(e)
      });
  }

}
