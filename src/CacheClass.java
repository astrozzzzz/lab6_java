import java.lang.reflect.Proxy;

public class CacheClass {
    public static <T> T cache(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }

        Class<?> objClass = obj.getClass();

        // Проверяем, реализует ли объект интерфейсы
        Class<?>[] interfaces = objClass.getInterfaces();
        if (interfaces.length == 0) {
            throw new IllegalArgumentException("Object must implement at least one interface");
        }

        // Создаём прокси для объектов, реализующих интерфейсы
        return (T) Proxy.newProxyInstance(
                objClass.getClassLoader(),
                interfaces,
                new CacheInvocationHandler<>(obj)
        );
    }
}
