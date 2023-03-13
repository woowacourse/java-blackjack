package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {

    @DisplayName("베팅 금액이 음수이거나 1억원 이상이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 100_000_001})
    void 베팅_금액_천원_이하_예외_발생(int inputBettingAmount) {
        assertThatThrownBy(() -> new BettingAmount(inputBettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 0원 이상 1억원 이하여야 합니다.");
    }

    @DisplayName("베팅 금액을 반환한다.")
    @Test
    void 베팅_금액_반환() {
        int bettingAmountInput = 7_000;
        BettingAmount bettingAmount = new BettingAmount(bettingAmountInput);
        assertThat(bettingAmount.getValue()).isEqualTo(bettingAmountInput);
    }
}
