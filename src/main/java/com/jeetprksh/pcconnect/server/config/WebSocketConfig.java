package com.jeetprksh.pcconnect.server.config;

import com.jeetprksh.pcconnect.server.service.AuthService;
import com.jeetprksh.pcconnect.server.websocket.WebSocketHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  @Autowired private AuthService authService;

  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(new WebSocketHandler(authService), "/websocket").setAllowedOrigins("*");
  }

}
