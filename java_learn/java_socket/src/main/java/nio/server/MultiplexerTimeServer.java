package nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by matt on 02/08/2017.
 */
public class MultiplexerTimeServer implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;

    /**
     * 初始化多路复用器,绑定监听端口
     *
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();//note: 创建多路复用器 selector
            serverSocketChannel = ServerSocketChannel.open(); //note: 打开 ServerSocketChannel
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024); //note: 绑定端口
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //note: 并将 selector 注册到  ServerSocketChannel,并监控 SelectionKey.OP_ACCEPT 操作位
            System.out.println("The time server is start in port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);//note: selector 轮询,每隔1s 被唤醒一次
                Set<SelectionKey> selectionKeys = selector.selectedKeys();//note: 返回处于就绪状态的 Channel 的 SelectionKey 集合
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);//note: 处理就绪的 client
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }

            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        //note: selector 关闭后,所有注册在上面的 channel 和 pipe 等资源都会被自动去注册关闭,所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            //note:  当有新的客户端接入时,处理新的接入请求,完成 TCP 三次握手,建立物理链接
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept(); //note: 通过 accept 方法接收客户端的请求并创建 SocketChannel 实例
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);//note: 将新接入的 client 注册到 Reactor 线程的多路复用器上,监听读操作,读取 client 的请求
            }
            if (key.isReadable()) {
                //note: read the data
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);//note: 异步读取 client 请求消息到缓冲区
                if (readBytes > 0) {//note: 解码
                    readBuffer.flip();//note: 把 buffer 从写模式调整为读模式,并将缓冲区的 limit 设置为 position,position 设置为0
                    byte[] bytes = new byte[readBuffer.remaining()];//note: remaining, 返回未读的元素个数
                    readBuffer.get(bytes);//note: 将缓冲区可读的字节数组复制到新创建的字节数组中
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server order: " + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
                } else if (readBytes < 0) {
                    //note: close the channel
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel sc, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);//note: 将消息发送给 client
            System.out.println("response succeed." + response);
            //note: 如果发送区的 TCP 缓冲区满,会导致写半包,此时,需要注册监听写操作位,循环写,直到整包消息写入 TCP 缓冲区。
        }
    }

}
