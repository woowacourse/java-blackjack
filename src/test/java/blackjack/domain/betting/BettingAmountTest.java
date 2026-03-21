package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999, 1_000_001})
    void 베팅_금액은_최소_1_000에서_최대_1_000_000여야한다(int bettingAmount) {
        // when & then
        assertThatThrownBy(() -> new BettingAmount(bettingAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
