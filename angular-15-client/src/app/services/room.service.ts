import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import { Room } from '../models/room.model';

const baseUrl = 'http://localhost:8080/api/rooms';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private roomIdSubject = new BehaviorSubject<number >(0);
  roomId$ = this.roomIdSubject.asObservable();

  setRoomId(id: number) {
    this.roomIdSubject.next(id);
  }

  constructor(private http: HttpClient) { }

  getAll(): Observable<Room[]> {
    return this.http.get<Room[]>(baseUrl);
  }

  get(id: number): Observable<Room> {
    return this.http.get<Room>(`${baseUrl}/${id}`);
  }

  create(room: Room): Observable<Room> {
    return this.http.post<Room>(baseUrl, room);
  }

  update(id: number, room: Room): Observable<Room> {
    return this.http.put<Room>(`${baseUrl}/${id}`, room);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
}
