package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("Money 는  int 를 매개 변수로 받는다.")
    @Test
    void createMoneyTest() {
        Assertions.assertThatCode(() -> new Money(10000)).doesNotThrowAnyException();
    }
}
