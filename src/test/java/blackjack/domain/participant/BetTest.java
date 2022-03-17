package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BetTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100, -1000})
    @DisplayName("베팅 금액 음수 또는 0일 경우 예외 처리")
    void validateBettingMoney(final int amount) {

        assertThatThrownBy(() -> new Bet(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액 1원 이상이어야 합니다.");
    }
}
