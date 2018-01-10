package com.fnd.blogger.manager.service.impl;

import com.fnd.blogger.manager.service.SchedulerTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务
 */
@Component
public class SchedulerTaskImpl implements SchedulerTask {
    private int count=0;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


   //@Scheduled(cron="*/6 * * * * ?")
    public void process(){
        System.out.println("this is scheduler task runing  "+(count++));
    }


    //@Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }
}
