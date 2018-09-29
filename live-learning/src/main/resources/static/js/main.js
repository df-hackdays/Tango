'use strict';
 
 
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('#connecting');
 
var stompClient = null;
var username = null;
  
 
function connect() {
    username = document.querySelector('#username').innerText.trim();
      
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
 
    stompClient.connect({}, onConnected, onError);
}
 
// Connect to WebSocket Server.
connect();
 
function onConnected() {
    // Subscribe to the Public Topic
  //  stompClient.subscribe('/topic/publicChatRoom', onMessageReceived);
    stompClient.subscribe('/class', onBreakpointReceived)
    stompClient.subscribe('/lecturer', onBreakpointReceived)
    stompClient.subscribe('/privateTutoringChat/123123', onBreakpointReceived)
    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    );
   // @Id
   // String id;
   // String questionId;
   // String studentId;
  //  T answer;

    stompClient.send("/app/student/123123", {},
        JSON.stringify({studentId: username, questionId: '123', answer: "stuffffffff"}));
    stompClient.send("/app/student/123124", {},
            JSON.stringify({studentId: username, questionId: '123', answer: "stuffffffff"}));
    connectingElement.classList.add('hidden');
}
 
 
function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}
 
 
function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}
 
function onBreakpointReceived(payload)
{
    var breakpoint = JSON.parse(payload.body);
    console.log("lol");
    alert(JSON.stringify(breakpoint));
}
function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
 
    var messageElement = document.createElement('li');
 
    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');  
        var usernameElement = document.createElement('strong');
        usernameElement.classList.add('nickname');
        var usernameText = document.createTextNode(message.sender);
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }
 
    var textElement = document.createElement('span');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
 
    messageElement.appendChild(textElement);
 
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}
  
  
messageForm.addEventListener('submit', sendMessage, true);