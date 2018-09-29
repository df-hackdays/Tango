import { Component, OnInit } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Message } from '../chat/Message';

declare let Stomp: any;

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss']
})
export class StudentComponent implements OnInit {
	private stompClient;

  constructor() { }


	messages:Message[] = [
		{
			text: 'first from',
			time: '1234',
			direction: 'left',
			type:'chatbot'
		},
		{
			text: 'second fromadsfad  sfadsfasdf to',
			time: '4567',
			direction: 'right',
			type:'self'
		},
		{
			text: 'third froasdfasdfadsfasdfasdfadsfadsf daf asdf adf asdf m from',
			time: '6987',
			direction: 'left',
			type:'instructor'
		},
		{
			text: 'fourth fro sdafsdfa sdf m to',
			time: '7658',
			direction: 'right',
			type:'self'
		},
		{
			text: 'fifth from',
			time: '56788',
			direction: 'left',
			type:'chatbot'
		}
		
	];

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

  	onConnected() {
    // Subscribe to the Public Topic
	    this.stompClient.subscribe('/class', this.onMessageReceived);
	 
	    // Tell your username to the server
	    // stompClient.send("/app/chat.addUser",
	    //     {},
	    //     JSON.stringify({sender: 'instructor', type: 'JOIN'})
	    // )
 
    	// this.connectingElement.classList.add('hidden');
	}
	 
	 
	onError(error) {
	    // connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
	    // connectingElement.style.color = 'red';
	}
	 
	 
	sendMessage(event) {
	    // var messageContent = messageInput.value.trim();
	    // if(messageContent && stompClient) {
	    //     var chatMessage = {
	    //         sender: username,
	    //         content: messageInput.value,
	    //         type: 'CHAT'
	    //     };
	    //     stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
	    //     messageInput.value = '';
	    // }
	    // event.preventDefault();
	}
	 
	 
	onMessageReceived(payload) {
		debugger;
	    console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
	}

}
