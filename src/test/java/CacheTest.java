import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// Тестовый класс
class CacheTest {

    // Описание: Тест проверяет, что метод cache корректно кэширует результаты для аннотированных классов.

    @Test
    @DisplayName("Тест кэширования метода toString() в ExampleClass1")
    void testCacheForExampleClass1() {
        // Arrange: создаем объект ExampleClass1
        IExampleClass1 example = new ExampleClass1("test1", 123);
        IExampleClass1 cachedExample = CacheAnnotatedClass.cache(example);

        // Act & Assert:
        // Первый вызов toString() должен сгенерировать результат
        String firstCall = cachedExample.toString();
        assertNotNull(firstCall, "Первый вызов toString() не должен возвращать null");

        // Повторный вызов должен вернуть результат из кэша
        String secondCall = cachedExample.toString();
        assertEquals(firstCall, secondCall, "Результаты вызовов toString() должны совпадать из-за кэширования");

        // Проверяем кэширование другого метода computeValue()
        int firstCompute = cachedExample.computeValue();
        int secondCompute = cachedExample.computeValue();
        assertEquals(firstCompute, secondCompute, "Результаты computeValue() должны быть кэшированы");
    }

    @Test
    @DisplayName("Тест кэширования метода calculate() в ExampleClass2")
    void testCacheForExampleClass2() {
        // Arrange: создаем объект ExampleClass2
        IExampleClass2 example = new ExampleClass2(42);
        IExampleClass2 cachedExample = CacheAnnotatedClass.cache(example);

        // Act & Assert:
        // Первый вызов calculate() должен сгенерировать результат
        int firstCall = cachedExample.calculate();
        assertEquals(84, firstCall, "Первый вызов calculate() должен вернуть корректный результат");

        // Повторный вызов должен вернуть результат из кэша
        int secondCall = cachedExample.calculate();
        assertEquals(firstCall, secondCall, "Результаты вызовов calculate() должны совпадать из-за кэширования");

        // Метод multiply() не должен быть кэширован
        int firstMultiply = cachedExample.multiply(3);
        int secondMultiply = cachedExample.multiply(3);
        assertNotEquals(firstMultiply, secondMultiply, "Метод multiply() не должен быть кэширован");
    }
}
