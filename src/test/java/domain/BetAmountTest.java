package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @DisplayName("금액이 양수가 아니면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    void notPositive(int amount) {
        Assertions.assertThatThrownBy(() -> BetAmount.from(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 양수만 입력가능합니다.");
    }

    @DisplayName("금액이 10의 배수가 아니면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {1_001, 10_001, 9})
    void notMultiplesOfTen(int amount) {
        Assertions.assertThatThrownBy(() -> BetAmount.from(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 10의 배수만 입력가능합니다.");
    }

    @DisplayName("금액이 양수이고 10의 배수이면 BetAmount를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1_000})
    void from(int amount) {
        Assertions.assertThatCode(() -> BetAmount.from(amount))
                .doesNotThrowAnyException();
    }

    @DisplayName("배팅 금액의 1.5배를 반환한다.")
    @Test
    void multiply() {
        // given
        final BetAmount betAmount = BetAmount.from(10_000);

        // when
        final BetAmount newBetAmount = betAmount.multiply(1.5);

        // then
        Assertions.assertThat(newBetAmount.getAmount()).isEqualTo((int) (10_000 * 1.5));
    }
}
