package message;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wangmeng on 14/10/2016.
 */
public class MessageUtilTest {
    String message = "Hello World";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testPrintMessage() {
        assertEquals(message, messageUtil.printMessage());
    }

}