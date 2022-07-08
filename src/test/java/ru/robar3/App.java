package ru.robar3;

import ru.robar3.annotation.AfterSuite;
import ru.robar3.annotation.BeforeSuite;
import ru.robar3.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {

    public static void main(String[] args) {
        start(PrintTest.class);


    }

    public static <T> T start(Class<T> clazz) {
        T instance;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
            final Method[] methods = clazz.getDeclaredMethods();
            Method beforeSuite = null;
            Method afterSuite=null;
            int beforeSuiteCount = 0;
            int afterSuiteCount = 0;
            for (Method method : methods) {
                if (method.isAnnotationPresent(BeforeSuite.class)){
                    beforeSuite=method;
                    beforeSuiteCount++;
                    if (beforeSuiteCount>1){
                        throw new RuntimeException();
                    }
                }
                if (method.isAnnotationPresent(AfterSuite.class)) {
                    afterSuite = method;
                    afterSuiteCount++;
                    if (afterSuiteCount>1) {
                        throw new RuntimeException();
                    }
                }
            }
            beforeSuite.invoke(instance);
            for (int i = 1; i <= 10; i++) {
                for (Method method:methods) {
                    if (method.isAnnotationPresent(Test.class)){
                        int priority  =method.getAnnotation(Test.class).priority();
                        if (priority==i){
                            method.invoke(instance);
                        }
                    }
                }
            }
            afterSuite.invoke(instance);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public void runPriorityMethods(Method[] methods){


    }
}
