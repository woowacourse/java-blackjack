package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {
    @DisplayName("베팅_금액이_1,000원_미만이면_예외를_발생한다")
    @ValueSource(ints = {0, 999, 1_000_001})
    @ParameterizedTest
    void should_ThrowException_WhenBettingAmountOutRange() {
        // when
        // then
        assertThatThrownBy(() -> new BettingAmount(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1,000원부터 1,000,000까지 입니다.");
    }
}
