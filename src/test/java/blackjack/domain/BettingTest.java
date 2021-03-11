package blackjack.domain;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingTest {
    @Test
    void putBettingMap() {
        Betting betting = new Betting();
        Player player = new Player("bob");
        betting.put(player, 1000);
        assertThat(betting.get(player)).isEqualTo(1000);
    }
}