package blackjack.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultStatusTest {

    @DisplayName("블랙잭은 1.5배 연산한다.")
    @Test
    void calculateBlackjack() {
        var given = 3;
        PlayerResultStatus blackjack = PlayerResultStatus.BLACKJACK;
        var result = blackjack.calculate(given);

        assertThat(result).isEqualTo(4.5);
    }

    @DisplayName("이기면 그대로 얻는다.")
    @Test
    void calculateWin() {
        var given = 3;
        PlayerResultStatus blackjack = PlayerResultStatus.WIN;
        var result = blackjack.calculate(given);

        assertThat(result).isEqualTo(3);
    }

    @DisplayName("이기면 그대로 잃는다.")
    @Test
    void calculateLose() {
        var given = 3;
        PlayerResultStatus blackjack = PlayerResultStatus.LOSE;
        var result = blackjack.calculate(given);

        assertThat(result).isEqualTo(-3);
    }

    @DisplayName("비기면 아무것도 얻지 못한다.")
    @Test
    void calculatePush() {
        var given = 3;
        PlayerResultStatus blackjack = PlayerResultStatus.PUSH;
        var result = blackjack.calculate(given);

        assertThat(result).isEqualTo(0);
    }
}
