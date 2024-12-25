@Cache
public class ExampleClass2 implements IExampleClass2 {
    private final int number;

    public ExampleClass2(int number) {
        this.number = number;
    }

    @Override
    public int calculate() {
        System.out.println("calculate() вычисляется...");
        return number * 2;
    }

    @Override
    public int multiply(int factor) {
        System.out.println("multiply() вычисляется...");
        return number * factor;
    }
}
