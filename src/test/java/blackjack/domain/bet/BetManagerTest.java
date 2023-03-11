package blackjack.domain.bet;

import blackjack.domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BetManagerTest {

    @Test
    void addTest() {
        BetManager betManager = new BetManager();
        Player playerA = new Player("a");
        betManager.add(playerA, new Money(10000));
        Assertions.assertThat(betManager.getBetMoney(playerA).getValue()).isEqualTo(10000);
    }
}
