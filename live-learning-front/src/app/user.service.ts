import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

	id: string;

  	constructor() { }

  	setId(id) {
  		this.id = id;
  	}

  	getId() {
  		return this.id;
  	}
}