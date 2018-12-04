package com.huarui.msg;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author lihui
 *  参考博客->  https://www.cnblogs.com/bianzy/p/5822426.html
 */
@Component
public class MessageConsumerHeartBeat {

	private final Logger log = LoggerFactory.getLogger(MessageConsumerHeartBeat.class);

	public void receiveMessage(String message,String chanel) {
		log.info("--------------收到消息：{}",message);
		try {
			WebSocketServer.sendInfo(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
