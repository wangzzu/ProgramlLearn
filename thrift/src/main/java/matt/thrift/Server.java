package matt.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年11月13日
 */
public class Server {

	public static int port = 8090;

	/**
	 * 简单服务器类型 阻塞单线程 步骤 创建TProcessor 创建TServerTransport 创建TProtocol 创建TServer
	 * 启动Server
	 */
	public static void startSimpleServer() {
		// 创建processor
		TProcessor tprocessor = new ThriftService.Processor<ThriftService.Iface>(new ThriftServiceImpl());
		try {
			// 创建transport 阻塞通信
			TServerSocket serverTransport = new TServerSocket(port);
			// 创建protocol
			TBinaryProtocol.Factory protocol = new TBinaryProtocol.Factory();
			// 将processor transport protocol设入到服务器server中
			TServer.Args args = new TServer.Args(serverTransport);
			args.processor(tprocessor);
			args.protocolFactory(protocol);
			// 定义服务器类型 设定参数
			TServer server = new TSimpleServer(args);
			// 开启服务
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 多线程服务器 阻塞多线程
	 */
	public static void startThreadPoolServer() {
		// 创建processor
		TProcessor tprocessor = new ThriftService.Processor<ThriftService.Iface>(new ThriftServiceImpl());
		try {
			// 创建transport 阻塞通信
			TServerSocket serverTransport = new TServerSocket(port);
			// 创建protocol 数据传输协议
			TBinaryProtocol.Factory protocol = new TBinaryProtocol.Factory();
			TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
			args.processor(tprocessor);
			args.protocolFactory(protocol);
			// 创建服务器类型 多线程
			TServer server = new TThreadPoolServer(args);
			// 开启服务
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 非阻塞I/O
	 */
	public static void startTNonblockingServer() {
		// 创建processor
		TProcessor tprocessor = new ThriftService.Processor<ThriftService.Iface>(new ThriftServiceImpl());
		try {
			// 创建transport 非阻塞 nonblocking
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
			// 创建protocol 数据传输协议
			TCompactProtocol.Factory protocol = new TCompactProtocol.Factory();
			// 创建transport 数据传输方式 非阻塞需要用这种方式传输
			TFramedTransport.Factory transport = new TFramedTransport.Factory();
			TNonblockingServer.Args args = new TNonblockingServer.Args(serverTransport);
			args.processor(tprocessor);
			args.transportFactory(transport);
			args.protocolFactory(protocol);
			// 创建服务器 类型是非阻塞
			TServer server = new TNonblockingServer(args);
			// 开启服务
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 半同步半异步的非阻塞I/O
	 */
	public static void startTHsHaServer() {
		// 创建processor
		TProcessor tprocessor = new ThriftService.Processor<ThriftService.Iface>(new ThriftServiceImpl());
		try {
			// 创建transport 非阻塞
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
			// 非阻塞需要的传输方式
			TFramedTransport.Factory transport = new TFramedTransport.Factory();
			// 数据传输协议
			TCompactProtocol.Factory protocol = new TCompactProtocol.Factory();
			// 创建半同步半异步服务
			THsHaServer.Args args = new THsHaServer.Args(serverTransport);
			args.processor(tprocessor);
			args.transportFactory(transport);
			args.protocolFactory(protocol);
			// 创建 服务类型
			TServer server = new THsHaServer(args);
			// 开启服务
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		// 开启简单服务器
		// Server.startSimpleServer();
		// 开启多线程服务器
		// Server.startThreadPoolServer();
		// Server.startTNonblockingServer();
		// Server.startTHsHaServer();
		Server.startTNonblockingServer();
	}
}
