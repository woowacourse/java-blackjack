package domain;

import domain.gamer.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("Money 는  int 를 매개 변수로 받는다.")
    @Test
    void createMoneyTest() {
        Assertions.assertThatCode(() -> new Money(10000)).doesNotThrowAnyException();
    }

    @DisplayName("돈은 0보다 큰 자연수 여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -9999})
    void validateMoneyRange(int money) {
        Assertions.assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("돈은 0보다 큰 자연수여야 합니다.");
    }
}
