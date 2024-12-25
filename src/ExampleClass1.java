@Cache(value = {"toString", "computeValue"})
public class ExampleClass1 implements IExampleClass1 {
    private final String name;
    private final int value;

    public ExampleClass1(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        System.out.println("toString() вычисляется...");
        return "ExampleClass{name='" + name + "', value=" + value + "}";
    }

    @Override
    public int computeValue() {
        System.out.println("computeValue() вычисляется...");
        return value * 2;
    }
}
