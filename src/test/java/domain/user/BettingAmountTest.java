package domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {

    @DisplayName("베팅 금액이 1000원 이하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 999, 100_000_001})
    void 베팅_금액_천원_이하_예외_발생(int inputBettingAmount) {
        assertThatThrownBy(() -> new BettingAmount(inputBettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1,000원 이상 1억원 이하여야 합니다.");
    }
}