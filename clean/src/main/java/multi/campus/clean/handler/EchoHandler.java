package multi.campus.clean.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String rcvMsg = message.getPayload();
		
		TextMessage sendMsg = new TextMessage(rcvMsg);
		session.sendMessage(sendMsg);
	}
	
}