package blackjack.domain.participant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static blackjack.domain.FixtureCards.ACE_CLUBS;
import static blackjack.domain.FixtureCards.JACK_SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingTest {

    private Money money;
    private Player player;

    @BeforeEach
    void setUp() {
        money = new Money(1000);
        player = new Player("bob");
        player.bet(money);
    }

    @Test
    void bettingIfBlackjack() {
        player.startRound(JACK_SPADES, ACE_CLUBS);
        assertThat(player.revenue()).isEqualTo(1500);
    }

    @Test
    void bettingIfHit() {
        player.startRound(JACK_SPADES, JACK_SPADES);
        assertThatThrownBy(() -> player.revenue()).isInstanceOf(UnsupportedOperationException.class);
    }


    @Test
    void bettingIfStay() {
        player.startRound(JACK_SPADES, JACK_SPADES);
        player.stay();
        assertThat(player.revenue()).isEqualTo(1000);
    }

    @Test
    void bettingIfBust() {
        player.startRound(JACK_SPADES, JACK_SPADES);
        player.addCard(JACK_SPADES);
        assertThat(player.revenue()).isEqualTo(-1000);
    }
}