package blackjackgame.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjackgame.model.participants.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {

    @DisplayName("0 원 이하의 배팅을 하면 예외 발생")
    @Test
    void testInvalidBettingMoney() {
        assertThatThrownBy(() ->
                new Betting(new Player("lily"), 0)).isInstanceOf(IllegalArgumentException.class);
    }
}
