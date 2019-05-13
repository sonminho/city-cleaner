package multi.campus.clean.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
	
	HashMap<String, WebSocketSession> map = new HashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String rcvMsg = message.getPayload();
		System.out.println("다음 메시지를 수신함 > " + rcvMsg);
		System.out.println("세션 > " + session);
		
		if (rcvMsg.equals("browser")) {
			System.out.println("브라우저 세션을 추가합니다." + session);

			if (map.get(session.getId()) == null)
				map.put(session.getId(), session);
		}
		
		if (rcvMsg.equals("termination")) {
			System.out.println("브라우저 세션을 종료합니다." + session);
			//map.remove(session.getId());
		}

		System.out.println("브라우저 사이즈 > " + map.size());
		TextMessage sendMsg = new TextMessage(rcvMsg);

		Iterator<String> keys = map.keySet().iterator();
		
		ArrayList<String> removeList = new ArrayList<>();
		
		while (keys.hasNext()) {
			String key = keys.next();
			WebSocketSession ws = map.get(key);
			
			if(ws.isOpen()) {
				ws.sendMessage(sendMsg);
			} else {
				System.out.println("닫힐 소켓 > " + ws.getId());
				removeList.add(ws.getId());
			}
		}
		
		for(String removeWebSocketId : removeList) {
			System.out.println("소켓을 닫습니다 > " + removeWebSocketId);
			map.remove(removeWebSocketId);
		}
	}

}