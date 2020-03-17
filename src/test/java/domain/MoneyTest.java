package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @DisplayName("money 객체를 잘 생성하는지 테스트")
    @Test
    void constructorTest() {
        Assertions.assertThat(new Money(10000)).isInstanceOf(Money.class);
    }

    @DisplayName("money 객체 오류 테스트")
    @Test
    void throw_constructorTest() {
        Assertions.assertThatThrownBy(()->new Money(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}
