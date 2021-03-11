package blackjack.domain;

import blackjack.domain.betting.Betting;
import blackjack.domain.betting.Money;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingTest {
    @Test
    void putBettingMap() {
        Betting betting = new Betting();
        Money money = new Money(1000);

        Player player = new Player("bob");
        betting.put(player, money);
        assertThat(betting.get(player)).isEqualTo(new Money(1000));
    }

    @Test
    void name() {
        Money money = new Money(null);
    }
}