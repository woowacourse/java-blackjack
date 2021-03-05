package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    private static final Card TWO_DIAMONDS = new Card(Number.TWO, Suit.DIAMONDS);
    private static final Card JACK_SPADES = new Card(Number.JACK, Suit.SPADES);
    private static final Card THREE_HEARTS = new Card(Number.THREE, Suit.HEARTS);
    private static final Card ACE_CLUBS = new Card(Number.ACE, Suit.CLUBS);
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
        assertThat(player.getWinCount()).isEqualTo(1);
    }

    @Test
    void compareWithDealerAndLose() {
        player.addCard(JACK_SPADES);

        dealer.addCard(TWO_DIAMONDS);
        dealer.addCard(THREE_HEARTS);
        dealer.addCard(ACE_CLUBS);

        player.fight(dealer);
        assertThat(player.getLoseCount()).isEqualTo(1);
    }
}