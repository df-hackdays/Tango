import { Component, OnInit } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Message } from '../chat/Message';
import { UserService } from '../user.service'

declare let Stomp: any;

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss']
})
export class StudentComponent implements OnInit {
	private stompClient;
	userService:UserService;
	breakpoint:any;
	payload:any;
	isPairedChat: boolean = false;

  	constructor(userService:UserService) { this.userService = userService; }

  	pairMessages:Message[] = [
  		{
			text: 'Hi you\'ve been invited to a private chat with a peer. Please use this time to discuss and go over the current concept!',
			time: '1234',
			direction: 'left',
			type:'chatbot'
		},
  	]

	messages:Message[] = [
		{
			text: 'Welcome to your CLC Event! Your instructor will be posting live tasks and feedback for you to complete here. Also, please feel free to ask the instructor a question at any time. They will answer it when they get a chance.',
			time: '1234',
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
	    this.stompClient.subscribe('/class', this.onMessageReceived.bind(this));
	 
	    // Tell your username to the server
	    // this.stompClient.send("/app/chat.addUser",
	    //     {},
	    //     JSON.stringify({sender: this.userService.getId(), type: 'JOIN'})
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
	    console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")

	    let pl:any = JSON.parse(payload.body);
	    
	    // if this is abreakpoint payload
	    if(!pl.studentId && !pl[0]) {
	    	this.breakpoint = JSON.parse(payload.body);

		    this.messages.push({
				text: this.breakpoint.question,
				time: '56788',
				direction: 'left',
				type:'chatbot',
				questionTypeEnum: this.breakpoint.questionTypeEnum,
				answer: this.breakpoint.answer,
				options: this.breakpoint.options,
				questionId: this.breakpoint.questionId,
				isGeneralFeedback: this.breakpoint.isGeneralFeedback
			})
	    } else if (pl[0]) {
	    	if (this.userService.getId() === pl[0].tutorId && !this.userService.getRoomId()) {
	    		// start a room with me as tutor
	    		this.stompClient.subscribe('/privateTutoringChat/'+pl[0].roomId, this.onMessageReceived.bind(this));
	    		this.userService.setRoomId(pl[0].roomId);
	    		this.isPairedChat = true;
	    		this.pairMessages[0].text = "Hello, based on your progress you\'ve been chosen to mentor a fellow Canada Learns Code student for the current course concept. Please use this time and private chat to discuss and go over the current concept!"

	    	} else if (this.userService.getId() === pl[0].tutoreeId && !this.userService.getRoomId()) {
	    		// start a room with me as tutoree
	    		this.stompClient.subscribe('/privateTutoringChat/'+pl[0].roomId, this.onMessageReceived.bind(this));
	    		this.userService.setRoomId(pl[0].roomId);
	    		this.pairMessages[0].text = "Hi there, how about pairing up with a fellow colleague, to work on the current course concept. We've identified that this task might be better tackled in teams. Please use this time and private chat to discuss and go over the current course concept!"
	    	}
	    } else {
	    	if(pl.studentId !== this.userService.getId())
	    	this.pairMessages.push({
				text: pl.message,
				time: '56788',
				direction: 'left',
				type:'student'
			})
	    }
	    
	}

	onResponded(m:Message){
		// massage for server
		if(m.mType === "question") {
			m.studentQuestion = m.text;

			m.studentId = this.userService.getId();
			this.stompClient.send("/app/student/send-question-to-lecturer", {}, JSON.stringify(m));
			this.messages.push({
				text: m.text,
				time: '56788',
				direction: 'right',
				type:'self',
				// questionTypeEnum: this.breakpoint.questionTypeEnum,
				// answer: this.breakpoint.answer,
				// options: this.breakpoint.options,
				// questionId: this.breakpoint.questionId,
				// isGeneralFeedback: this.breakpoint.isGeneralFeedback
			})
		} else if (m.mType === "answer") { 
			if(!m.questionId) {
				m.questionId = "1";
			}
			m.answer = m.option;
			const oldType = m.type;
			m.type = m.questionTypeEnum;
			m.studentId = this.userService.getId();
			console.log("responding to class status")
	  		this.stompClient.send("/app/student/send-breakpoint-answer", {}, JSON.stringify(m));
	  		m.type=oldType;
  		}
	}

	onPairResponded(m:Message) {
		m.studentId = this.userService.getId();
		m.roomId = this.userService.getRoomId();
		// TODO stomp send message to pair using roomid and proper stomp object
		this.stompClient.send("/app/student/"+this.userService.getRoomId(), {},
            JSON.stringify({studentId: this.userService.getId(), message: m.text}));
		this.pairMessages.push({
				text: m.text,
				time: '56788',
				direction: 'right',
				type:'self',
				// questionTypeEnum: this.breakpoint.questionTypeEnum,
				// answer: this.breakpoint.answer,
				// options: this.breakpoint.options,
				// questionId: this.breakpoint.questionId,
				// isGeneralFeedback: this.breakpoint.isGeneralFeedback
			})


	}

}
