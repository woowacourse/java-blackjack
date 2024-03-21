package blackjack.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    @DisplayName("금액이 자연수가 아니면 예외를 발생시킨다.")
    void validateNaturalNumberTest(int money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 양수여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 100})
    @DisplayName("금액이 자연수이면 잘 생성된다.")
    void creatSuccessfullyTest(int money) {
        assertDoesNotThrow(() -> new Money(money));
    }
}
