package com.jeetprksh.pcconnect.server.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeetprksh.pcconnect.server.entity.User;
import com.jeetprksh.pcconnect.server.service.AuthService;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/*
 * @author Jeet Prakash
 * */
public class WebSocketHandler extends TextWebSocketHandler {

  private final Logger logger = Logger.getLogger(WebSocketHandler.class.getName());

  private final Map<User, WebSocketSession> userSessions = new ConcurrentHashMap<>();
  private final AuthService authService;
  private final ObjectMapper mapper = new ObjectMapper();

  public WebSocketHandler(AuthService authService) {
    this.authService = authService;
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) {
    logger.info("Received message: " + message.getPayload());
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    try {
      String token = session.getHandshakeHeaders().get("token").get(0);
      User user = authService.verifyToken(token);
      userSessions.put(user, session);
      for (User sessionUser : userSessions.keySet()) {
        if (!sessionUser.getId().equalsIgnoreCase(user.getId())) {
          Message message = new Message(MessageType.ONLINE.getType(), user.getId());
          byte[] messageBytes = mapper.writeValueAsString(message).getBytes();
          session.sendMessage(new TextMessage(messageBytes));
        }
      }
      logger.info("Added WebSocket session for " + user);
    } catch (Exception ex) {
      ex.printStackTrace();
      session.close(CloseStatus.BAD_DATA);
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    String token = session.getHandshakeHeaders().get("token").get(0);
    User user = authService.verifyToken(token);
    userSessions.remove(user);
    logger.info("Removed WebSocket session for " + user.getName() + " " + user.getId());
  }
}
