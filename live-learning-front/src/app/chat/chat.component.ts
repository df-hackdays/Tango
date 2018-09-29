import { Component, OnInit, OnChanges, Input, EventEmitter, Output } from '@angular/core';
import { Message } from './Message';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})

export class ChatComponent implements OnInit, OnChanges {
	@Input() messages: Message[];
	@Output() messageResponse = new EventEmitter<Message>();

	constructor() { }

	ngOnInit() {
		
	}

	ngOnChanges() {
		
	}

	answerMessage(message, answer) {
		message.option = answer;
		this.messageResponse.emit(message);
	}

}
