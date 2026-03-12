package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoneyTest {

    @ParameterizedTest
    @CsvSource(value = {"10000,2,20000", "10,2,20", "1,1.5,2", "1,1.49,1"})
    void 곱셈기능이_제대로_동작한다(long amount, double factor, long expectedAmount) {
        // given
        Money money = new Money(amount);

        // when, then
        assertEquals(new Money(expectedAmount), money.multiply(factor));
    }
}
