package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static blackjack.domain.FixtureCards.ACE_CLUBS;
import static blackjack.domain.FixtureCards.JACK_SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void bettingIfBlackjack() {
        player.startRound(JACK_SPADES, ACE_CLUBS);
        assertThat(betting.revenue(player)).isEqualTo(1500);
    }

    @Test
    void bettingIfHit() {
        player.startRound(JACK_SPADES, JACK_SPADES);
        assertThatThrownBy(() -> betting.revenue(player)).isInstanceOf(UnsupportedOperationException.class);
    }


    @Test
    void bettingIfStay() {
        player.startRound(JACK_SPADES, JACK_SPADES);
        player.stay();
        assertThat(betting.revenue(player)).isEqualTo(1000);
    }

    @Test
    void bettingIfBust() {
        player.startRound(JACK_SPADES, JACK_SPADES);
        player.addCard(JACK_SPADES);
        assertThat(betting.revenue(player)).isEqualTo(-1000);
    }
}