package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetMoneyTest {
    @Test
    @DisplayName("양수를 입력 받으면 베팅 금액 객체를 정상적으로 생성한다.")
    void shouldReturnBetMoneyForValidBetAmount() {
        // given
        int betAmount = 1000;

        // when
        BetMoney betMoney = new BetMoney(betAmount);

        // then
        assertThat(betMoney.betAmount()).isEqualTo(betAmount);
    }

    @ParameterizedTest
    @DisplayName("0 이하의 수를 입력 받으면 예외가 발생한다.")
    @ValueSource(ints = {0, -5000})
    void shouldThrowExceptionForInvalidBetAmount(int betAmount) {
        // when & then
        assertThatThrownBy(() -> new BetMoney(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
