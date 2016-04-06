package matt.thrift;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年11月13日
 */
public class Client implements Runnable {

	public static String ip = "localhost";
	public static int port = 8090;
	public static int time_out = 30000;

	/**
	 * 客户端设置 创建Transport 创建TProtocol 基于TTransport和TProtocol创建Client
	 * 调用Client的相应方法
	 */
	public static void startSimpleClient() {
		TTransport transport = null;
		try {
			// 创建Transport
			transport = new TSocket(ip, port, time_out);
			// 创建TProtocol
			TProtocol protocol = new TBinaryProtocol(transport);
			// 基于TTransport和TProtocol创建Client
			ThriftService.Client client = new ThriftService.Client(protocol);
			transport.open();
			// 调用client方法
			List<User> list = client.queryUserList();
			for (User user : list) {
				System.out.println(user.userId + " " + user.username + " " + user.password);
			}
			Map<String, String> map = client.queryUserNamePass();
			System.out.println(map);
			User user = client.queryUser(10);
			System.out.println(user.userId + " " + user.username + " " + user.password);
			Map<Integer, User> map_u = client.queryUserMap();
			System.out.println(map_u);
			User uu = new User();
			uu.userId = 1111;
			uu.username = "mmbbmmbb";
			uu.password = "ppbbppbb";
			client.addUser(uu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 调用阻塞服务器的客户端
	 */
	public static void startNonblockingClient() {
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(ip, port));
			TCompactProtocol protocol = new TCompactProtocol(transport);
			ThriftService.Client client = new ThriftService.Client(protocol);
			transport.open();
			// 调用client方法
			List<User> list = client.queryUserList();
			for (User user : list) {
				System.out.println(user.userId + " " + user.username + " " + user.password);
			}
			Map<String, String> map = client.queryUserNamePass();
			System.out.println(map);
			User user = client.queryUser(10);
			System.out.println(user.userId + " " + user.username + " " + user.password);
			Map<Integer, User> map_u = client.queryUserMap();
			System.out.println(map_u);
			User uu = new User();
			uu.userId = 1111;
			uu.username = "mmbbmmbb";
			uu.password = "ppbbppbb";
			client.addUser(uu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void startAsynClient() {
		try {
			TAsyncClientManager clientManager = new TAsyncClientManager();
			TNonblockingTransport transport = new TNonblockingSocket(ip, port, time_out);
			TProtocolFactory tprotocol = new TCompactProtocol.Factory();
			ThriftService.AsyncClient asyncClient = new ThriftService.AsyncClient(tprotocol, clientManager, transport);
			System.out.println("Client start ...");
			CountDownLatch latch = new CountDownLatch(1);
			AsynCallback callBack = new AsynCallback(latch);
			System.out.println("call method queryUser start ...");
			asyncClient.queryUser(100, callBack);
			System.out.println("call method queryUser end");
			boolean wait = latch.await(30, TimeUnit.SECONDS);
			System.out.println("latch.await =:" + wait);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Client.startSimpleClient();
	}

	public static void main(String args[]) {
		// 调用简单服务器
		// Client.startSimpleClient();
		/*
		 * Client c1 = new Client(); Client c2 = new Client();
		 * 
		 * new Thread(c1).start(); new Thread(c2).start();
		 */

		// Client.startNonblockingClient();
		// Client.startNonblockingClient();
		Client.startAsynClient();
	}
}
