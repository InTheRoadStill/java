package com.example.demo.autoload;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.util.httpUtil;

@Component
@Order(value = 1)
public class ThreadStartService implements ApplicationRunner,Runnable  {
	
	final static int THREAD_NUM = 500;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("get it");
		//loadTask();
	}
	
	public void loadTask() {
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);
		int i = 0;
		ThreadStartService task = new ThreadStartService();
		while(true) {
			if(i >= THREAD_NUM) {
				i = 0;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++;
			executor.submit(task);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long timestamp = System.currentTimeMillis();
		String t1 = String.valueOf(timestamp);
		String respond = httpUtil.sendRequest("http://127.0.0.1:8888/?hello="+t1, "GET");
		System.out.println(respond);
	}
	
}
