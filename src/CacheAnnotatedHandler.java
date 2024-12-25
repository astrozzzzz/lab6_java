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



public class CacheAnnotatedHandler<T> implements InvocationHandler {
    private final T originalObject;
    private final Map<Method, Object> methodCache = new HashMap<>();
    private final Set<String> methodsToCache;

    public CacheAnnotatedHandler(T originalObject, Set<String> methodsToCache) {
        this.originalObject = originalObject;
        this.methodsToCache = methodsToCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (methodsToCache != null && !methodsToCache.contains(method.getName())) {
            return method.invoke(originalObject, args);
        }

        if (args == null || args.length == 0) {
            if (methodCache.containsKey(method)) {
                return methodCache.get(method);
            } else {
                Object result = method.invoke(originalObject);
                methodCache.put(method, result);
                return result;
            }
        } else {
            return method.invoke(originalObject, args);
        }
    }
}
