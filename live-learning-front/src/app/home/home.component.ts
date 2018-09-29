import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
	router:Router;
	userService:UserService;
	constructor(private _router:Router, userService:UserService){
		this.router = _router;
		this.userService = userService;
	}

	startStudent(email) {
		this.userService.setId(email);
		this.router.navigateByUrl('/student');
	}

	startInstructor() {
		this.router.navigateByUrl('/instructor');
	}

  ngOnInit() {
   
  }

}
