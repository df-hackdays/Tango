import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
	router:Router;
	constructor(private _router:Router){
		this.router = _router;
	}

	startStudent() {
		this.router.navigateByUrl('/student');
	}

	startInstructor() {
		this.router.navigateByUrl('/instructor');
	}

  ngOnInit() {
   
  }

}
