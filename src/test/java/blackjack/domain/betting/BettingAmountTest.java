package blackjack.domain.betting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BettingAmountTest {

    @ParameterizedTest
    @DisplayName("베팅 금액이 정상인 경우 테스트")
    @ValueSource(ints = {1, 1000, Integer.MAX_VALUE})
    public void generateBettingAmountTest(int input) {
        assertDoesNotThrow(() -> new BettingAmount(input));
    }

    @ParameterizedTest
    @DisplayName("베팅 금액이 0 이하인 경우 예외 처리 테스트")
    @ValueSource(ints = {-1, 0})
    public void generateBettingAmountFailTest(int input) {
        Assertions.assertThatThrownBy(() -> new BettingAmount(input)).isInstanceOf(IllegalArgumentException.class);
    }
}
