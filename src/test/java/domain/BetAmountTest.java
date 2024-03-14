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
                .hasMessage("[ERROR] 유효하지 않은 배팅 금액입니다.");
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
