package matt.thrift.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;

/**
 * 实现具体的接口
 * 
 * @author matt
 * @since Apr 7, 2016
 */
public class AccountService implements Account.Iface {
	private static Map<String, String> namePw = new HashMap<>();
	private static Map<String, Integer> nameId = new HashMap<>();
	private static Map<String, Operation> nameOp = new HashMap<>();
	private static Map<Integer, String> idname = new HashMap<>();

	/**
	 * 向服务器添加用户
	 * 
	 * @author matt
	 * @since Apr 7, 2016
	 * @throws 无
	 * @see matt.thrift.account.Account.Iface#addUser(matt.thrift.account.User)
	 *
	 */
	@Override
	public void addUser(User user) throws TException {
		int id = user.getUserId();
		String name = user.getUsername();
		String pass = user.getPassword();
		Operation op = user.getOp();

		if (name == null || name.length() == 0) {
			throw new InvalidOperation(100, "The name should not be empty!");
		}
		if (namePw.containsKey(name)) {
			throw new InvalidOperation(101, "The name has been used, please change the name!");
		}
		if (nameId.containsValue(id) || id <= 0) {
			throw new InvalidOperation(102, "The id has been used or the id is invalid, please change the id!");
		}

		if (op == Operation.LOGIN) {
			String password = namePw.get(name);
			if (password != null && password.equals(pass)) {
				System.out.println("Login success!! Hello " + name);
			} else {
				System.out.println("Login failed!! please check your username and password");
			}
		} else if (op == Operation.REGISTER) {
			if (namePw.containsKey(name)) {
				System.out.println("The username " + name + " has been registered, please change one.");
			} else {
				namePw.put(name, pass);
				nameId.put(name, id);
				nameOp.put(name, op);
				idname.put(id, name);
				System.out.println("Register success!! Hello " + name);
			}
		} else {
			throw new InvalidOperation(103, "unknown operation: " + op.getValue());
		}
	}

	/**
	 * 根据id查询用户
	 * 
	 * @author matt
	 * @since Apr 7, 2016
	 * @throws 无
	 * @see matt.thrift.account.Account.Iface#queryUser(int)
	 *
	 */
	@Override
	public User queryUser(int id) throws TException {
		System.out.println(id);
		if (idname.containsKey(id)) {
			User user = new User();
			user.userId = id;
			String name = idname.get(id);
			user.username = name;
			user.password = namePw.get(name);
			user.op = nameOp.get(name);
			return user;
		} else {
			System.out.println("The id: " + id + " does not exist!");
			User user = new User();
			user.userId = 0;
			user.username = "";
			user.password = "123456";
			user.op = Operation.LOGIN;
			return user;
		}
	}

	/**
	 * 返回服务器端所有用户的列表
	 * 
	 * @author matt
	 * @since Apr 7, 2016
	 * @throws 无
	 * @see matt.thrift.account.Account.Iface#queryUserList()
	 *
	 */
	@Override
	public List<User> queryUserList() throws TException {
		List<User> list = new ArrayList<User>();
		for (String name : namePw.keySet()) {
			User user = new User();
			user.userId = nameId.get(name);
			user.username = name;
			user.password = namePw.get(name);
			user.op = nameOp.get(name);
			list.add(user);
		}
		return list;
	}
}
