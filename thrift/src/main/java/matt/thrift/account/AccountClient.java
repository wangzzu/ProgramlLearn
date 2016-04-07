package matt.thrift.account;

import java.util.List;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class AccountClient {
	public static String ip = "localhost";
	public static int port = 8090;
	public static int time_out = 30000;

	/**
	 * 使用阻塞式socket
	 * 
	 * @author matt
	 * @since Apr 7, 2016
	 * @throws 无
	 *             void
	 */
	public static void startSimpleClient() {
		TTransport transport = null;
		try {
			// 创建Transport
			transport = new TSocket(ip, port, time_out);
			// 创建TProtocol
			TProtocol protocol = new TBinaryProtocol(transport);
			// 基于TTransport和TProtocol创建Client
			Account.Client client = new Account.Client(protocol);
			transport.open();

			// 正常添加用户
			User user1 = new User(001, "matt1", "123456", Operation.REGISTER);
			client.addUser(user1);
			User user2 = new User(002, "matt2", "123456", Operation.REGISTER);
			client.addUser(user2);
			User user3 = new User(003, "matt3", "123456", Operation.REGISTER);
			client.addUser(user3);
			User user4 = new User(004, "matt4", "123456", Operation.REGISTER);
			client.addUser(user4);
			User user5 = new User(005, "matt5", "123456", Operation.REGISTER);
			client.addUser(user5);

			// 查看全部用户
			List<User> list = client.queryUserList();
			System.out.println("There are " + list.size() + " users in total.");
			for (User user : list) {
				System.out.println(user.userId + " " + user.username + " " + user.password);
			}
			// 查询用户
			User userq1 = client.queryUser(1);
			if (userq1 != null) {
				System.out.println("Query: " + userq1.userId + " " + userq1.username + " " + userq1.password);
			} else {
				System.out.println("The id: 1 does not exist!");
			}
			User userq2 = client.queryUser(8);
			if (userq2 != null) {
				System.out.println("Query: " + userq2.userId + " " + userq2.username + " " + userq2.password);
			} else {
				System.out.println("The id: 8 does not exist!");
			}

			// 登陆用户
			User users = new User(005, "matt5", "123456", Operation.LOGIN);
			client.addUser(users);

			// 添加异常用户
			User user6 = new User(006, "", "123456", Operation.REGISTER);// name=null
			client.addUser(user6);
			User user7 = new User(006, "matt1", "123456", Operation.REGISTER);// name存在
			client.addUser(user7);
			User user8 = new User(005, "matt6", "123456", Operation.REGISTER);// id异常
			client.addUser(user8);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 非阻塞
	 * 
	 * @author matt
	 * @since Apr 7, 2016
	 * @throws 无
	 *             void
	 */
	public static void startNonblockingClient() {
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(ip, port));
			TCompactProtocol protocol = new TCompactProtocol(transport);
			Account.Client client = new Account.Client(protocol);
			transport.open();

			// 正常添加用户
			User user1 = new User(001, "matt1", "123456", Operation.REGISTER);
			client.addUser(user1);
			User user2 = new User(002, "matt2", "123456", Operation.REGISTER);
			client.addUser(user2);
			User user3 = new User(003, "matt3", "123456", Operation.REGISTER);
			client.addUser(user3);
			User user4 = new User(004, "matt4", "123456", Operation.REGISTER);
			client.addUser(user4);
			User user5 = new User(005, "matt5", "123456", Operation.REGISTER);
			client.addUser(user5);

			// 查看全部用户
			List<User> list = client.queryUserList();
			System.out.println("There are " + list.size() + " users in total.");
			for (User user : list) {
				System.out.println(user.userId + " " + user.username + " " + user.password);
			}

			// 查询用户
			User userq1 = client.queryUser(1);
			if (!userq1.username.equals("")) {
				System.out.println("Query: " + userq1.userId + " " + userq1.username + " " + userq1.password);
			} else {
				System.out.println("The id: 1 does not exist!");
			}
			User userq2 = client.queryUser(8);
			if (!userq2.username.equals("")) {
				System.out.println("Query: " + userq2.userId + " " + userq2.username + " " + userq2.password);
			} else {
				System.out.println("The id: 8 does not exist!");
			}

			// 登陆用户
			User users = new User(005, "matt5", "123456", Operation.LOGIN);
			client.addUser(users);

			// 添加异常用户
			User user6 = new User(006, "", "123456", Operation.REGISTER);// name=null
			client.addUser(user6);
			User user7 = new User(006, "matt1", "123456", Operation.REGISTER);// name存在
			client.addUser(user7);
			User user8 = new User(005, "matt6", "123456", Operation.REGISTER);// id异常
			client.addUser(user8);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// AccountClient.startSimpleClient();
		AccountClient.startNonblockingClient();
	}
}
