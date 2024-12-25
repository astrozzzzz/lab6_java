import java.util.Objects;

public class A implements ITA {
    private String stringField;

    public A(String str) {
        this.stringField = str;
    }

    @Override
    public int cacheTest() {
        System.out.println("original method");
        return 42;
    }

    @Override
    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringField);
    }
}
