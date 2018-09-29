import { Component, OnInit } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Message } from '../chat/Message';
declare let Stomp: any;

@Component({
  selector: 'app-instructor',
  templateUrl: './instructor.component.html',
  styleUrls: ['./instructor.component.scss']
})
export class InstructorComponent implements OnInit {

	private stompClient;

	messages:Message[] = [
		{
			text: 'Welcome to LiveLearn! Please press the button when you are done teaching a concept!',
			time: '1234',
			direction: 'left',
			type:'chatbot'
		}
		
	];

  constructor() { }



  ngOnInit() {
  	var ws = new SockJS('http://localhost:9853/ws/');
  	this.stompClient = Stomp.over(ws);

  	this.stompClient.connect({}, () => this.onConnected(), this.onError);


	 // sock.onopen = function() {
	 //     console.log('open');
	 //     sock.send('test');
	 // };

	 // sock.onmessage = function(e) {
	 //     console.log('message', e.data);
	 //     sock.close();
	 // };

	 // sock.onclose = function() {
	 //     console.log('close');
	 // };
  }

  askClassStatus(){
  	console.log("asking class status")
  	this.stompClient.send("/app/lecturer/send-breakpoint", {}, "");
  	this.messages.push({
		text: "You have asked your class for their level of understanding, please wait while their responses are gathered.",
		time: '56788',
		direction: 'left',
		type:'chatbot-issue',
	})
  }

  	onConnected() {
    // Subscribe to the Public Topic
	    this.stompClient.subscribe('/lecturer', this.onMessageReceived.bind(this));
	 
	    // Tell your username to the server
	    // stompClient.send("/app/chat.addUser",
	    //     {},
	    //     JSON.stringify({sender: 'instructor', type: 'JOIN'})
	    // )
 
    	// this.connectingElement.classList.add('hidden');
	}
	 
	 
	onError(error) {

	}
	 
	 
	sendMessage(event) {

	}
	 
	 
	onMessageReceived(payload) {
		
		let pl:any = JSON.parse(payload.body);
		if(pl.studentQuestion) {
			this.messages.push({
				text: "Your student " + pl.studentId + " has raised a question: " + pl.studentQuestion,
				time: '56788',
				direction: 'left',
				type:'chatbot-issue',
			})
		} else if (pl.answer && pl.isGenericQuestion) {
	   		//debugger;
		    this.messages.push({
				text: "Your student " + pl.studentId + " has a " + pl.answer + "/5 understanding of this concept",
				time: '56788',
				direction: 'left',
				type:'chatbot',
			})
		} else if (pl.answer) {
	   		//debugger;
		    this.messages.push({
				text: "Your student " + pl.studentId + " has answered this question " + (pl.isCorrectAnswer ? "correctly" : "incorrectly"),
				time: '56788',
				direction: 'left',
				type:'chatbot',
			})
		}
	    

	}

}
