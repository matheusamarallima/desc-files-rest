package com.files.filesdemo.service;


import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class NotificationService {

    private final SocketIONamespace namespace;

    private final SocketIOServer server;

    public void publish(String message){
        namespace.getBroadcastOperations().sendEvent("notification", message);
    }

    public NotificationService(SocketIOServer server){
        this.server = server;
        this.namespace = server.addNamespace("/ws-listener");
    }

    @PreDestroy
    private void stopSocketIO(){
        this.server.stop();
    }
}
