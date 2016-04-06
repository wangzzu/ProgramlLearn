package matt.thrift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年11月13日
 */
public class ThriftServiceImpl implements ThriftService.Iface {

	public void addUser(User user) throws org.apache.thrift.TException {
		System.out.println(user.userId + "  " + user.username + "  " + user.password);
	}

	public User queryUser(int id) throws org.apache.thrift.TException {
		System.out.println(id);
		User user = new User();
		user.userId = 100;
		user.username = "FFF";
		user.password = "NNN";
		return user;
	}

	public List<User> queryUserList() throws org.apache.thrift.TException {
		User user = new User();
		user.userId = 100;
		user.username = "FFF";
		user.password = "NNN";
		User user2 = new User();
		user2.userId = 102;
		user2.username = "FFF2";
		user2.password = "NNN2";
		List<User> list = new ArrayList<User>();
		list.add(user2);
		list.add(user);
		return list;
	}

	public Map<String, String> queryUserNamePass() throws org.apache.thrift.TException {
		User user = new User();
		user.userId = 100;
		user.username = "FFF";
		user.password = "NNN";
		Map<String, String> map = new HashMap<String, String>();
		map.put("password", user.password);
		map.put("useranme", user.username);
		return map;
	}

	public Map<Integer, User> queryUserMap() throws org.apache.thrift.TException {
		User user = new User();
		user.userId = 100;
		user.username = "FFF";
		user.password = "NNN";
		User user2 = new User();
		user2.userId = 102;
		user2.username = "FFF2";
		user2.password = "NNN2";
		Map<Integer, User> map = new HashMap<Integer, User>();
		map.put(user.userId, user);
		map.put(user2.userId, user2);
		return map;
	}

}
