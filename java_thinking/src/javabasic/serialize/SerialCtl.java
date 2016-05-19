package javabasic.serialize;

//: io/SerialCtl.java
//Controlling serialization by adding your own
//writeObject() and readObject() methods.

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialCtl implements Serializable {
	private String a;
	private transient String b;

	public SerialCtl(String aa, String bb) {
		a = "Not Transient: " + aa;
		b = "Transient: " + bb;
	}

	public String toString() {
		return a + " " + b;
	}

	/**
	 * 自定义该方法，这里要注意方法时private
	 * 在调用ObjectOutputStream.writeObject()时，会检查所传递的Serializable对象，看看是否实现了它自己的writeObject()。
	 * 如果是这样，就跳过正常的序列化过程并调用它的writeObject()
	 *
	 * @param stream
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject(); // 执行默认的writeObject()
		stream.writeObject(b); // transient字段需要明确保存和
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject(); // 执行默认的readObject()
		b = (String) stream.readObject(); // transient字段需要明确恢复
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SerialCtl sc = new SerialCtl("Test1", "Test2");
		System.out.println("Before:\n" + sc);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(buf);
		o.writeObject(sc);
		// Now get it back:
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
		SerialCtl sc2 = (SerialCtl) in.readObject();
		System.out.println("After:\n" + sc2);
	}
}
/*
* Before:
* Not Transient: Test1 Transient: Test2
* After:
* Not Transient: Test1 Transient: Test2
*/
