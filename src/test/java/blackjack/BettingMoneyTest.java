package blackjack;

import blackjack.domain.participant.BettingMoney;
import blackjack.domain.participant.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingMoneyTest {
    @DisplayName("BettingMoney 생성 테스트")
    @Test
    void createBettingMoneyTest() {
        Assertions.assertDoesNotThrow(
                () -> new BettingMoney(2300)
        );

        assertThatThrownBy(
                () -> new BettingMoney(-1)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
