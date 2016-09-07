package stack;

/**
 * VM Args: -Xss256k
 *
 * Created by wangmeng on 9/7/16.
 */
public class StackTest1 {
    private int stackLength=1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable{
        StackTest1 stackTest1=new StackTest1();
        try {
            stackTest1.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length: "+stackTest1.stackLength);
            throw e;
        }
    }
    /**
     * Exception in thread "main" java.lang.StackOverflowError
     *  at stack.StackTest1.stackLeak(StackTest1.java:9)
     * stack length: 1868
     *  at stack.StackTest1.stackLeak(StackTest1.java:10)
     *  at stack.StackTest1.stackLeak(StackTest1.java:10)
     *  ...
     */
}
