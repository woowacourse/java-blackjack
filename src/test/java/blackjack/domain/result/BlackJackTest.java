package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackTest {

    @Test
    @DisplayName("베팅 금액을 1.5배로 계산해준다.")
    void calculateBetByBlackJack() {
        BlackJack blackJack = new BlackJack();
        assertThat(blackJack.calculateBet(1000)).isEqualTo(1500);
    }
}
