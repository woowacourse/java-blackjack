package blackjack.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    @DisplayName("금액이 양수가 아니면 예외를 발생시킨다.")
    void validateNaturalTest(int money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 양수여야 합니다.");
    }
}
