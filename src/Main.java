import com.timed.Doer;
import com.timed.SimpleDoer;
import com.timed.TimingDynamicInvocationHandler;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Doer doer = (Doer) Proxy.newProxyInstance(
                Main.class.getClassLoader(), new Class[]{Doer.class},
                new TimingDynamicInvocationHandler(new SimpleDoer()));
        doer.doSth();
        doer.doSthElse();
    }

}
