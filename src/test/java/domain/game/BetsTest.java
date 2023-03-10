package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.participant.Player;
import org.junit.jupiter.api.Test;

class BetsTest {

    @Test
    void addBet() {
        Player player = new Player("깃짱");
        Money money = new Money(1000);

        Bets bets = new Bets();
        assertDoesNotThrow(() -> bets.addBet(player, money));
 /*       assertThat(bets.getBets())
                .containsKey(player)
                .containsValue(money);*/
    }
}
