import { Component, OnInit } from '@angular/core';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss']
})
export class StudentComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  	var sock = SockJS('http://localhost:9853/ws/');
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
