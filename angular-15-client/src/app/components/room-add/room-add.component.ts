import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Room} from "../../models/room.model";
import {RoomService} from "../../services/room.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-room-add',
  templateUrl: './room-add.component.html',
  styleUrls: ['./room-add.component.css']
})
export class RoomAddComponent implements OnInit, OnDestroy {
  roomForm!: FormGroup;
  subscriptions: Subscription[] = [];
  submitted: boolean = false;

  constructor(private fb: FormBuilder, private roomService: RoomService) {

  }

  ngOnInit(): void {
    this.roomForm = this.fb.group({
      title: ['', [Validators.required, Validators.maxLength(50)]],
      description: ['', [Validators.required, Validators.maxLength(200)]],
      numberOfSeats: [null, [Validators.required, Validators.min(1)]],
      occupation: [false]
    });
  }

  get currentRoomForm() {
    return this.roomForm.value;
  }

  onSubmit(): void {
    if (this.roomForm.valid) {
      this.subscriptions.push(
        this.roomService.create(this.currentRoomForm as Room)
          .subscribe({
            next: (res) => {
              console.log(res);
              this.submitted = true;
            },
            error: (e) => console.error(e)
          })
      );
    }
  }

  newRoom(): void {
    this.submitted = false;
    this.roomForm.reset();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((e)=>{
      e.unsubscribe();
    })
  }

}
