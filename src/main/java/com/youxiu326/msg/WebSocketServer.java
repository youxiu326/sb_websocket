package com.youxiu326.msg;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

/**
 * @author lihui
 */
@ServerEndpoint(value = "/girl/{uid}")
@Component
public class WebSocketServer {

	/**
	 * 当前在线数量
	 */
	private static int onlineCount = 0;
	
	/**
	 * 存放客户端对象
	 */
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
	
	/**
	 * 客户端连接的会话。这里发送消息给客户端
	 */
	private Session session;

	private String uid;
	
	/**
	 * 建立连接 成功后会执行
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session,@PathParam("uid") String uid) {
		this.session = session;
        this.uid = uid;
		//加入客户端
        webSocketSet.add(this);     
        addOnlineCount();           //在线数加1
        System.err.println("用户加入！当前人数为："+getOnlineCount());
        try {
        	 sendMessage("连接成功",uid);
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	/**
	 * 链接关闭
	 */
	@OnClose
    public void onClose() {
        webSocketSet.remove(this); 
        subOnlineCount();  
        System.err.println("用户退出！当前人数为："+getOnlineCount());
	}

    /**
     * 收到消息
     * @param message
     * @param session
     */
	@OnMessage
    public void onMessage(String message, Session session,@PathParam("uid") String uid) throws IOException {
    	System.err.println(uid+"客户端发送消息："+message);
    	session.getBasicRemote().sendText(uid + message);
        // WebSocketServer socketServer = webSocketSet.stream().filter(it -> it.uid.equals(uid)).map(it -> it).findFirst().orElse(null);

    }
	@OnError
    public void onError(Session session, Throwable error) {
		System.err.println("ERROR");
        error.printStackTrace();
    }
	
    /**
     * 服务器主动推送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message,@PathParam("uid") String uid) throws IOException {
        WebSocketServer socketServer = webSocketSet.stream().filter(it -> it.uid.equals(uid)).map(it -> it).findFirst().orElse(null);
        synchronized (socketServer.session) {
            socketServer.session.getBasicRemote().sendText(message);
        }
    }
    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("uid") String uid) throws IOException {
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message,uid);
            } catch (IOException e) {
                continue;
            }
        }
    }
 
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
 
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
