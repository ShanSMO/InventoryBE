package com.ims.inventory.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/send/message")
    public String messageFromClisent(@Payload String message) {
        template.convertAndSend( "/socket", message);
        return message;
    }

    @RequestMapping(value = "test-ws", method = RequestMethod.POST)
    public void testWebSocketRestCall(@RequestBody SocketObject socketObject) {
        template.convertAndSend( "/socket", socketObject);
    }

}
