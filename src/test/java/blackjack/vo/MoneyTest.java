package blackjack.vo;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
    @DisplayName("사용자로부터 입력받는 금액은 음수일 수 없다")
    @Test
    void validateNegativeNumber() {
        int betAmount = -1000;

        assertThatThrownBy(() -> Money.fromInput(betAmount)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자로부터 입력받는 금액은 1000단위로 입력 가능하다")
    @ParameterizedTest
    @ValueSource(ints = {1000, 20000})
    void validateMultiplesOf1000(int betAmount) {

        assertThatCode(() -> Money.fromInput(betAmount)).doesNotThrowAnyException();
    }

    @DisplayName("사용자로부터 입력받는 금액이 1000단위가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {500, 1001})
    void validateNotMultiplesOf1000(int betAmount) {

        assertThatThrownBy(() -> Money.fromInput(betAmount)).isInstanceOf(IllegalArgumentException.class);
    }
}
