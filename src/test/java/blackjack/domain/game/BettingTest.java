package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingTest {

    @ParameterizedTest
    @ValueSource(longs = {0, -1})
    @DisplayName("배팅 금액 양수 아닌 경우 검증")
    void validatePositiveMoney(long money) {
        assertThatThrownBy(() -> new Betting(money))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("배팅 금액은 양수");
    }

}
