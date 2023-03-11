package blackjack.domain.bet;

import blackjack.domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BetManagerTest {

    @Test
    void should_Return_SameValue_When_add_Money() {
        BetManager betManager = new BetManager();
        Player playerA = new Player("a");
        betManager.add(playerA, new Money(10000));
        Assertions.assertThat(betManager.getBetMoney(playerA).getValue()).isEqualTo(10000);
    }
}
