package blackjack.participant;

import blackjack.domain.participant.BettingMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("getBettingMoney 테스트")
    @Test
    void getBettingMoneyTest() {
        assertThat(new BettingMoney(2300).getBettingMoney()).isEqualTo(2300);
    }
}
