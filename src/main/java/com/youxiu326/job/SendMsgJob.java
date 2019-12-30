package com.youxiu326.job;

import com.youxiu326.msg.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: lihui
 * @date: 2018年12月4日
 */
@Component
@EnableScheduling
public class SendMsgJob {

    private Logger log = LoggerFactory.getLogger(SendMsgJob.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    /**
     * 每3秒钟执行一次 发送当前格式化时间消息
     */
    @Scheduled(cron="0/3 * * * * ? ")
    public void StoreBasketCheck() {
        try {
            log.info("服务器端发送消息->{}",sdf.format(new Date()));
            WebSocketServer.sendInfo(sdf.format(new Date()),"first");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
} 