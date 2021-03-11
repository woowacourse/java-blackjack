package blackjack.domain.participant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static blackjack.domain.FixtureCards.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player("bob");
        dealer = new Dealer();
    }

    @Test
    void compareWithDealerAndWin() {
        player.startRound(JACK_SPADES, JACK_SPADES);
        dealer.startRound(TWO_DIAMONDS,THREE_HEARTS);
        dealer.addCard(ACE_CLUBS);

        player.fight(dealer);
        assertThat(player.getGameResult()
                         .getWinCount()).isEqualTo(1);
    }

    @Test
    void compareWithDealerAndLose() {
        player.startRound(TWO_DIAMONDS, JACK_SPADES);

        dealer.startRound(TWO_DIAMONDS,THREE_HEARTS);
        dealer.addCard(ACE_CLUBS);

        player.fight(dealer);
        assertThat(player.getGameResult()
                         .getLoseCount()).isEqualTo(1);
    }
}