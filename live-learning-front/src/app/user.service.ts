import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

	id: string;
	roomId: string = '0';

  	constructor() { }

  	setId(id) {
  		this.id = id;
  	}

  	getId() {
  		return this.id;
  	}

  	setRoomId(roomId) {
  		roomId = roomId;
  	}

  	getRoomId() {
  		return roomId;
  	}
}
