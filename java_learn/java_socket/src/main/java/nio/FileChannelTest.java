package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wangmeng on 9/18/16.
 */
public class FileChannelTest {
    public static void main(String[] args) {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("/Users/matt/data.txt", "rw");
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file.");
            e.printStackTrace();
        }
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = 0;
        try {
            bytesRead = inChannel.read(buf);
        } catch (IOException e) {
            System.out.println("Read failed from the channel.");
            e.printStackTrace();
        }
        while (bytesRead != -1) {

            System.out.println("\n********* Read ********* " + bytesRead);
            buf.flip(); // make buffer ready for reading

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear(); // clean the buffer, and make buffer ready for writing
            try {
                bytesRead = inChannel.read(buf);
            } catch (IOException e) {
                System.out.println("Read failed from the channel.");
                e.printStackTrace();
            }
        }
        try {
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
