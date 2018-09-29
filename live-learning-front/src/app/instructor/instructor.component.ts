import { Component, OnInit } from '@angular/core';
import * as SockJS from 'sockjs-client';

declare let Stomp: any;

@Component({
  selector: 'app-instructor',
  templateUrl: './instructor.component.html',
  styleUrls: ['./instructor.component.scss']
})
export class InstructorComponent implements OnInit {

	private stompClient;

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
  	this.stompClient.send("/app/lecturer/send-feedback-breakpoint", {}, "");
  }

  	onConnected() {
    // Subscribe to the Public Topic
    	let stompClient = this as any;
	    stompClient.subscribe('/class', this.onMessageReceived);
	 
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
	    // var message = JSON.parse(payload.body);
	 
	    // var messageElement = document.createElement('li');
	 
	    // if(message.type === 'JOIN') {
	    //     messageElement.classList.add('event-message');
	    //     message.content = message.sender + ' joined!';
	    // } else if (message.type === 'LEAVE') {
	    //     messageElement.classList.add('event-message');
	    //     message.content = message.sender + ' left!';
	    // } else {
	    //     messageElement.classList.add('chat-message');  
	    //     var usernameElement = document.createElement('strong');
	    //     usernameElement.classList.add('nickname');
	    //     var usernameText = document.createTextNode(message.sender);
	    //     var usernameText = document.createTextNode(message.sender);
	    //     usernameElement.appendChild(usernameText);
	    //     messageElement.appendChild(usernameElement);
	    // }
	 
	    // var textElement = document.createElement('span');
	    // var messageText = document.createTextNode(message.content);
	    // textElement.appendChild(messageText);
	 
	    // messageElement.appendChild(textElement);
	 
	    // messageArea.appendChild(messageElement);
	    // messageArea.scrollTop = messageArea.scrollHeight;
	}

}
