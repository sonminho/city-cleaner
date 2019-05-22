package multi.campus.clean.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import multi.campus.clean.domain.HandleMsg;

public class EchoHandler extends TextWebSocketHandler {
	Gson gson = new Gson();
	HashMap<String, WebSocketSession> map = new HashMap<>();
	WebSocketSession carSession;
	String carIp;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String rcvMsg = message.getPayload();
		System.out.println("페이로드 > " + rcvMsg);
		HandleMsg handleMsg = gson.fromJson(rcvMsg, HandleMsg.class);
		String msgType = handleMsg.getType();
		
		if (msgType.equals("browser")) {
			if (map.get(session.getId()) == null)
				map.put(session.getId(), session);
		} else if (msgType.equals("initializingCar")) {
			carSession = session;
			System.out.println("차량의 정보를 얻습니다." + session);

			carIp = handleMsg.getMessage();

			if (carSession != null)
				carSession.sendMessage(new TextMessage("서버와 파이가 연결되었습니다!!"));
		} else if (msgType.equals("driving")) {
			System.out.println("차량 수동 조작 > " + rcvMsg);
			
			if(carSession != null) {
				carSession.sendMessage(new TextMessage(rcvMsg));
			} else {
				carSession = null;
			}
		} else if (msgType.equals("binData") || msgType.equals("collectedData")) {
			System.out.println("bin or collectedData ");
			TextMessage sendMsg = new TextMessage(rcvMsg);

			Iterator<String> keys = map.keySet().iterator();
			ArrayList<String> removeList = new ArrayList<>();

			while (keys.hasNext()) {
				String key = keys.next();
				WebSocketSession ws = map.get(key);

				// 연결되어 있는 브라우저 소켓
				if (ws.isOpen()) {
					ws.sendMessage(sendMsg);
				} else {
					//System.out.println("닫힐 소켓 > " + ws.getId());
					removeList.add(ws.getId());
				}
			}

			for (String removeWebSocketId : removeList) {
				System.out.println("소켓을 닫습니다 > " + removeWebSocketId);
				map.remove(removeWebSocketId);
			}
		} else if (msgType.equals("collectingList")) {
			System.out.println("수집 리스트 > " + rcvMsg);
			
			if (carSession != null) {
				System.out.println("차에게 다음 메시지를 보냄 > " + handleMsg);
				carSession.sendMessage(new TextMessage(rcvMsg));
			} else {
				carSession = null;
			}
		} else if (msgType.equals("termination")) {
			System.out.println("웹 소켓 종료, 차량 세션을 초기화합니다.");
			
			carSession = null;
		} else if (msgType.equals("state")) {			
			System.out.println("차량 모드를 "+ handleMsg.getMode() +"로 전환합니다.");
			
			if (carSession.isOpen()) {
				System.out.println("차에게 다음 메시지를 보냄 > " + handleMsg);
				carSession.sendMessage(new TextMessage(rcvMsg));
			} else {
				System.out.println("차에게 다음 메시지를 안!보냄 > " + handleMsg);
				carSession.close();
				carSession = null;				
			}
		}
	}
}