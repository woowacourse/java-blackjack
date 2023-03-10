package blackjack.domain;

import blackjack.domain.Bet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BetTest {

    @DisplayName("Bet이 정상적으로 생성된다.")
    @Test
    void createBet() {
        // given
        int input = 1000;

        // when & then
        assertDoesNotThrow(() -> new Bet(input));
    }

    @ParameterizedTest(name = "양수가 아닐 경우 예외가 발생한다.")
    @ValueSource(ints = {-1000, 0})
    void validatePositiveNumber(int input) {
        // when & then
        assertThatThrownBy(() -> new Bet(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 양수여야 합니다.");
    }
}
