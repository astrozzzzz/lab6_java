import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.System.out;


public class Main {
    private static boolean checkType(String x, String typeName) {
        if (typeName == "double") {
            try {
                double value = Double.parseDouble(x);
                return true;
            } catch (NumberFormatException e) {
                out.println("Ошибка: введено не число типа double");
            }
        }
        if (typeName == "int") {
            try {
                double value = Integer.parseInt(x);
                return true;
            } catch (NumberFormatException e) {
                out.println("Ошибка: введено не число типа int");
            }
        }
        return false;
    }

    @Invoke
    public static void invokeChecker() {
        out.println("Вызван какой то метод с Invoke");
    }

    public static void main(String[] args) {

        out.print("Введите номер задания и номер задачи через пробел: ");
        Scanner scanner = new Scanner(System.in);
        String task = scanner.nextLine();
        String[] parts = task.split(" ");
        if (!checkType(parts[0], "int") || !checkType(parts[1], "int")) {
            out.println("Неправильно введён номер задачи");
            return;
        }
        int num1 = Integer.parseInt(parts[0]);
        int num2 = Integer.parseInt(parts[1]);
        if (num1 == 1 && num2 == 3) {
            // Пример использования
            ITA original = new A("first");
            ITA cached = CacheClass.cache(original);

            out.println(cached.cacheTest()); // Вывод: original method 42
            out.println(cached.cacheTest()); // Вывод: 42 (кэшированное значение)

            cached.setStringField("second");
            out.println(cached.cacheTest()); // Вывод: original method 42
        }
        else if (num1 == 2 && num2 >= 1 && num2 <= 6) {
            // Пример использования аннотации @Invoke
            invokeChecker();

            // Работа с аннотациями класса ExampleClass
            Class<?> cl = ExampleClass.class;

            // 1. Проверка и вывод аннотации @Default
            Default defaultAnnotation = cl.getAnnotation(Default.class);
            if (defaultAnnotation != null) {
                out.println("Аннотация @Default: " + defaultAnnotation.value());
            }

            // 2. Проверка и вывод аннотации @ToString
            ToString toStringAnnotation = cl.getAnnotation(ToString.class);
            if (toStringAnnotation != null) {
                out.println("Аннотация @ToString: " + toStringAnnotation.value());
            }

            // 3. Проверка и вывод аннотации @Validate
            Validate validateAnnotation = cl.getAnnotation(Validate.class);
            if (validateAnnotation != null) {
                out.println("Аннотация @Validate: " + Arrays.toString(validateAnnotation.value()));
            }

            // 4. Проверка и вывод аннотации @Two
            Two twoAnnotation = cl.getAnnotation(Two.class);
            if (twoAnnotation != null) {
                out.println("Аннотация @Two: first=" + twoAnnotation.first() + ", second=" + twoAnnotation.second());
            }

            // 5. Проверка и вывод аннотации @Cache
            Cache cacheAnnotation = cl.getAnnotation(Cache.class);
            if (cacheAnnotation != null) {
                out.println("Аннотация @Cache: " + Arrays.toString(cacheAnnotation.value()));
            }
        }
        else if (num1 == 3 && num2 == 3) {
            // Создаем объекты
            IExampleClass1 example1 = new ExampleClass1("test1", 123);
            IExampleClass2 example2 = new ExampleClass2(456);

            // Передаем оба объекта в cache
            Object[] cachedObjects = {CacheAnnotatedClass.cache(example1), CacheAnnotatedClass.cache(example2)};

            // Работаем с кэшированным объектом ExampleClass1
            IExampleClass1 cachedExample1 = (IExampleClass1) cachedObjects[0];
            out.println("Первый вызов toString() для ExampleClass1:");
            out.println(cachedExample1.toString());
            out.println();

            out.println("Повторный вызов toString() для ExampleClass1:");
            out.println(cachedExample1.toString());
            out.println();

            out.println("Вызов computeValue() для ExampleClass1:");
            out.println(cachedExample1.computeValue());
            out.println();

            // Работаем с кэшированным объектом ExampleClass2
            IExampleClass2 cachedExample2 = (IExampleClass2) cachedObjects[1];
            out.println("Первый вызов calculate() для ExampleClass2:");
            out.println(cachedExample2.calculate());
            out.println();

            out.println("Повторный вызов calculate() для ExampleClass2:");
            out.println(cachedExample2.calculate());

        }
        else {
            out.println("Неправильный номер варианта");
        }
    }
}

