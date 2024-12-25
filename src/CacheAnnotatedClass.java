import java.lang.reflect.InvocationHandler;
import java.util.*;
import java.lang.reflect.Proxy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.System.out;


public class CacheAnnotatedClass {
    public static <T> T cache(T... objects) {
        for (T obj : objects) {
            if (obj == null) {
                continue;
            }

            Class<?> objClass = obj.getClass();

            // Проверяем наличие аннотации @Cache
            Cache cacheAnnotation = objClass.getAnnotation(Cache.class);
            if (cacheAnnotation == null) {
                System.out.println("Объект класса " + objClass.getName() + " не аннотирован @Cache, кэширование пропущено.");
                continue;
            }

            String[] methodsToCache = cacheAnnotation.value();

            if (methodsToCache.length == 0) {
                // Если аннотация @Cache без параметров, кэшируем все методы без параметров
                System.out.println("Кэшируем все методы без параметров для класса " + objClass.getName());
                return (T) Proxy.newProxyInstance(
                        objClass.getClassLoader(),
                        objClass.getInterfaces(),
                        new CacheAnnotatedHandler<>(obj, null)
                );
            } else {
                // Если указан набор методов, кэшируем только их
                System.out.println("Кэшируем указанные методы: " + Arrays.toString(methodsToCache) + " для класса " + objClass.getName());
                return (T) Proxy.newProxyInstance(
                        objClass.getClassLoader(),
                        objClass.getInterfaces(),
                        new CacheAnnotatedHandler<>(obj, new HashSet<>(Arrays.asList(methodsToCache)))
                );
            }
        }
        return null;
    }
}
