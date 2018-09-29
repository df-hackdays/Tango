import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavBarService {

	isVisible: boolean;

  constructor() { 
  	this.isVisible = true;
  }

  setVisible(isVisible): void {
  	this.isVisible = isVisible;
  }

  getVisible(): boolean {
  	return this.isVisible;
  }

}
