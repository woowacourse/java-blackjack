package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {
    @DisplayName("베팅_금액이_1,000원_미만이면_예외를_발생한다")
    @ValueSource(ints = {0, 999, 1_000_001})
    @ParameterizedTest
    void should_ThrowException_WhenBettingAmountOutRange(int amount) {
        // when
        // then
        assertThatThrownBy(() -> new BettingAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1000원부터 1000000원까지 입니다.");
    }

    @DisplayName("주어진_비율을_곱해_정수_우승_금액을_계산한다")
    @Test
    void multiply() {
        // given
        BettingAmount bettingAmount = new BettingAmount(10_000);

        // when
        int result = bettingAmount.multiply(1.5);

        // then
        assertThat(result).isEqualTo(15_000);
    }

    @DisplayName("주어진_금액에서_베팅_금액을_뺄셈해서_반환한다")
    @Test
    void subtractFrom() {
        // given
        BettingAmount bettingAmount = new BettingAmount(10_000);

        // when
        int result = bettingAmount.subtractFrom(20_000);

        // then
        assertThat(result).isEqualTo(10_000);
    }
}
