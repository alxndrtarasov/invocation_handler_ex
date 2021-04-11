package com.timed;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class TimingDynamicInvocationHandler implements InvocationHandler {

    private final Map<String, Method> methods = new HashMap<>();

    private Object target;

    public TimingDynamicInvocationHandler(Object target) {
        this.target = target;

        for (Method method : target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        long start = 0;
        long elapsed = 0;
        TimingDynamicInvocationHandler.class.getClassLoader().loadClass("com.sun.proxy.$Proxy0");
        Field hField = proxy.getClass().getField("h");
        Class<?> internalClass = hField.get(proxy).getClass();
        boolean annotationPresent = internalClass.isAnnotationPresent(Timed.class) || method.isAnnotationPresent(Timed.class);
        if (annotationPresent) {
            start = System.nanoTime();
        }
        Object result = methods.get(method.getName()).invoke(target, args);
        if (annotationPresent) {
            elapsed = System.nanoTime() - start;
        }
        if (annotationPresent) {
            System.out.printf("Executing %s finished in %s ns%n", method.getName(),
                    elapsed);
        }

        return result;
    }
}