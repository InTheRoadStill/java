package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class test {

	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		// TODO Auto-generated method stub
		 ExecutorService exe=Executors.newFixedThreadPool(2);
		 test t = new test();
		 Task task=t.new Task();
		 Task task2=t.new Task();
		 Future<Integer> result=exe.submit(task);
		 FutureTask<Integer> task1 = new FutureTask<Integer>(task2);
		 new Thread(task1, "子线程").start();
		 exe.shutdown();
		 try {
			 while(!result.isDone()) {
				 System.out.println("waiting ...");
				 Thread.sleep(1000);
			 }
			System.out.println(result.get());
			System.out.println(task1.get());
			System.out.println("result is:" + (result.get() + (int)task1.get()));
			
			JSONObject jb = new JSONObject();
			jb.put("task1", result.get());
			jb.put("task2", task1.get());
			String json = JSON.toJSONString(jb);
			System.out.println(json);
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			System.out.println(df1.format(new Date()));// new Date()为获取当前系统时间
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class Task implements Callable<Integer>{
		public Integer call() throws Exception {
			System.out.println(Thread.currentThread().getName() + "begin ....");
			String respond = http.sendRequest("http://192.168.4.220/test/", "GET");
			System.out.println(respond);
			Map json = (Map) JSONObject.parse(respond);
			System.out.println(json.get("age").getClass());  
	        for (Object map : json.entrySet()){
	            System.out.println(((Map.Entry)map).getKey()+"  "+((Map.Entry)map).getValue());  
	        }
			int result = 0;
			try {
				if(json.get("age").getClass().equals(String.class)) {
					result = Integer.parseInt((String) json.get("age"));
				}else {
					result = (int) json.get("age");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				System.out.println("finally");
			}
			return result;
		}
	}
}
