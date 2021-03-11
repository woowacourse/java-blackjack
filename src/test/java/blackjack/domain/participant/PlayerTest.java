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
    void isBlackjack() {
        player.addCard(ACE_CLUBS);
        player.addCard(JACK_SPADES);
        assertTrue(player.isBlackJack());
    }

    @Test
    void noneBlackjack() {
        player.addCard(TWO_DIAMONDS);
        player.addCard(THREE_HEARTS);
        assertFalse(player.isBlackJack());
    }

    @Test
    void compareWithDealerAndWin() {
        player.addCard(JACK_SPADES);
        player.addCard(JACK_SPADES);

        dealer.addCard(TWO_DIAMONDS);
        dealer.addCard(THREE_HEARTS);
        dealer.addCard(ACE_CLUBS);

        player.fight(dealer);
        assertThat(player.getGameResult()
                         .getWinCount()).isEqualTo(1);
    }

    @Test
    void compareWithDealerAndLose() {
        player.addCard(JACK_SPADES);

        dealer.addCard(TWO_DIAMONDS);
        dealer.addCard(THREE_HEARTS);
        dealer.addCard(ACE_CLUBS);

        player.fight(dealer);
        assertThat(player.getGameResult()
                         .getLoseCount()).isEqualTo(1);
    }
}