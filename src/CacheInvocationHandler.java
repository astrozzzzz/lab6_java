import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CacheInvocationHandler<T> implements InvocationHandler {
    private final T originalObject;
    private final Map<Method, Object> methodCache = new HashMap<>();
    private int lastObjectStateHash;

    public CacheInvocationHandler(T originalObject) {
        this.originalObject = originalObject;
        this.lastObjectStateHash = computeObjectStateHash();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Кэшируем только методы без параметров
        if (args == null || args.length == 0) {
            int currentObjectStateHash = computeObjectStateHash();

            if (methodCache.containsKey(method) && lastObjectStateHash == currentObjectStateHash) {
                // Возвращаем кэшированное значение
                return methodCache.get(method);
            } else {
                // Вызываем оригинальный метод и сохраняем результат в кэше
                Object result = method.invoke(originalObject);
                methodCache.put(method, result);
                lastObjectStateHash = currentObjectStateHash;
                return result;
            }
        } else {
            // Если метод имеет параметры, вызываем его напрямую
            return method.invoke(originalObject, args);
        }
    }

    private int computeObjectStateHash() {
        return Objects.hash(originalObject);
    }
}