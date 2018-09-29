import { Component, OnInit, Input } from '@angular/core';
import { Message } from './Message';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})

export class ChatComponent implements OnInit {
	@Input() messages: Message[];
	constructor() { }

	ngOnInit() {

	}

}
