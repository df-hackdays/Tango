import { Component, OnInit, OnChanges, Input, EventEmitter, Output } from '@angular/core';
import { Message } from './Message';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})

export class ChatComponent implements OnInit, OnChanges {
	@Input() messages: Message[];
	@Input() isPairChat: boolean;
	@Output() messageResponse = new EventEmitter<Message>();

	constructor() { }

	ngOnInit() {
		
	}

	ngOnChanges() {
		
	}

	sendMessage(message) {
		let m:any ={};
		m.mType = "question";
		m.text = message;
		this.messageResponse.emit(m);
	}

	answerMessage(message, answer) {

		if(message.option === message.answer && message.questionTypeEnum && !message.isGeneralFeedback) {
			message.isCorrectAnswer = true;
		}

		if(message.isGeneralFeedback) {
			message.isGenericQuestion;
		}

		message.option = answer;

		message.mType = "answer";
		this.messageResponse.emit(message);
	}

}
