package blackjack.gamer;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {
    @DisplayName("베팅 금액은 1000원 이상이어야한다.")
    @Test
    void validateBettingMinimumAmount() {
        // given
        int bettingAmount = 999;

        // when // then
        assertThatCode(() -> new Betting(bettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}
