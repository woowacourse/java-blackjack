package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BetTest {

    @ParameterizedTest
    @ValueSource(doubles = {-1, -100, -1000})
    @DisplayName("베팅 금액 음수일 경우 예외 처리")
    void validateBettingMoney(final double amount) {

        assertThatThrownBy(() -> new Bet(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 음수일 수 없습니다.");
    }
}
