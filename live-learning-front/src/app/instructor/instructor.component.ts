import { Component, OnInit } from '@angular/core';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-instructor',
  templateUrl: './instructor.component.html',
  styleUrls: ['./instructor.component.scss']
})
export class InstructorComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  	var sock = new SockJS('http://localhost:9853/ws/');
	 sock.onopen = function() {
	     console.log('open');
	     sock.send('test');
	 };

	 sock.onmessage = function(e) {
	     console.log('message', e.data);
	     sock.close();
	 };

	 sock.onclose = function() {
	     console.log('close');
	 };
  }

}
