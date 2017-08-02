package methodarea;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+HeapDumpOnOutOfMemoryError
 * Created by wangmeng on 9/7/16.
 */
public class JavaMethodAreaOOM {
    static class OOMObject {
    }

    public static void main(final String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, args);
                }
            });
            enhancer.create();
        }
    }
    /**
     * output
     *
     * java.lang.OutOfMemoryError: PermGen space
     * Dumping heap to java_pid10480.hprof ...
     * Heap dump file created [10685765 bytes in 0.086 secs]
     * Exception in thread "main"
     * Exception: java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "main"
     */
}
