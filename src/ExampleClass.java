import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Invoke {
}

// Аннотация @Default
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface Default {
    Class<?> value();
}

// Аннотация @ToString
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface ToString {
    enum Value { YES, NO }
    Value value() default Value.YES;
}

// Аннотация @Validate
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface Validate {
    Class<?>[] value();
}

// Аннотация @Two
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Two {
    String first();
    int second();
}

// Аннотация @Cache
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Cache {
    String[] value() default {};
}

@Default(value = Main.class)
@ToString(value = ToString.Value.YES)
@Validate(value = {String.class, Integer.class})
@Two(first = "QQ", second = 2)
@Cache(value = {"toString"})
class ExampleClass implements IExampleClass{
    private String name;
    private int value;

    public ExampleClass(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        System.out.println("toString() выполнился");
        return "Выше имя name='" + name + "', ваше значение value=" + value + "}";
    }
}
