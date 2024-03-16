package blackjack.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.participants.Betting;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameOutcomeStatusTest {

    @DisplayName("블랙잭은 1.5배 연산한다.")
    @Test
    void calculateBlackjack() {
        var given = new Betting(3);
        GameOutcomeStatus blackjack = GameOutcomeStatus.BLACKJACK;
        var result = blackjack.calculate(given);

        assertThat(result).isEqualTo(new Profit(4.5));
    }

    @DisplayName("이기면 그대로 얻는다.")
    @Test
    void calculateWin() {
        var given = new Betting(3);
        GameOutcomeStatus blackjack = GameOutcomeStatus.WIN;
        var result = blackjack.calculate(given);

        assertThat(result).isEqualTo(new Profit(3));
    }

    @DisplayName("이기면 그대로 잃는다.")
    @Test
    void calculateLose() {
        var given = new Betting(3);
        GameOutcomeStatus blackjack = GameOutcomeStatus.LOSE;
        var result = blackjack.calculate(given);

        assertThat(result).isEqualTo(new Profit(-3));
    }

    @DisplayName("비기면 아무것도 얻지 못한다.")
    @Test
    void calculatePush() {
        var given = new Betting(3);
        GameOutcomeStatus blackjack = GameOutcomeStatus.PUSH;
        var result = blackjack.calculate(given);

        assertThat(result).isEqualTo(new Profit(0));
    }
}
