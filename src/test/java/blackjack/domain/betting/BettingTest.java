package blackjack.domain.betting;

import blackjack.domain.betting.Betting;
import blackjack.domain.betting.Money;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static blackjack.domain.FixtureCards.ACE_CLUBS;
import static blackjack.domain.FixtureCards.JACK_SPADES;
import static org.assertj.core.api.Assertions.assertThat;

class BettingTest {

    private Betting betting;
    private Money money;
    private Player player;

    @BeforeEach
    void setUp() {
        betting = new Betting();
        money = new Money(1000);
        player = new Player("bob");
        betting.put(player, money);
    }

    @Test
    void putBettingMap() {
        assertThat(betting.get(player)).isEqualTo(new Money(1000));
    }

    @Test
    void ratio() {
        player.startRound(JACK_SPADES, ACE_CLUBS);
        assertThat(betting.revenue(player)).isEqualTo(new Money(1500));
    }
}