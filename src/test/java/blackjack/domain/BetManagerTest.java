package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BetManagerTest {

    @Test
    void addTest() {
        BetManager betManager = new BetManager();
        Player playerA = new Player("a");
        betManager.add(playerA, 10000);
        Assertions.assertThat(betManager.getBetManager().get(playerA)).isEqualTo(10000);
    }
}
