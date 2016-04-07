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

service Account{
	void addUser(1:User user) throws (1: InvalidOperation e)
	User queryUser(1:i32 id)
	list<User> queryUserList()
}