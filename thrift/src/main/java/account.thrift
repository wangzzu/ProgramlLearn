namespace java matt.thrift.account

enum Operation{
	LOGIN=1,
	REGISTER=2
}

struct User{
	1: required i32 userId,
	2: required string username,
	3: required string password,
	4: Operation op
}

exception InvalidOperation{
	1: i32 code,
	2: string reason
}

service ThriftService{
	void addUser(1:User user)
	User queryUser(1:i32 id)
	list<User> queryUserList()
	map<string, string> queryUserNamePass()
	map<i32, User> queryUserMap()
	string doAction(1:User user) throws (1: InvalidOperation e)
}