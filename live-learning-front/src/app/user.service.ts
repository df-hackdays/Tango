import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

	id: string;
	roomId: string;

  	constructor() { }

  	setId(id) {
  		this.id = id;
  	}

  	getId() {
  		return this.id;
  	}

  	setRoomId(roomId) {
  		this.roomId = roomId;
  	}

  	getRoomId() {
  		return this.roomId;
  	}
}
