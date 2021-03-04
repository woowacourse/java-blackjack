package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.GameNumber;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    private static final Card TWO_DIAMOND = new Card(GameNumber.TWO, Shape.DIAMOND);
    private static final Card JACK_SPADE = new Card(GameNumber.JACK, Shape.SPADE);
    private static final Card THREE_HEART = new Card(GameNumber.THREE, Shape.HEART);
    private static final Card ACE_CLOVER = new Card(GameNumber.ACE, Shape.CLOVER);
    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player("bob");
        dealer = new Dealer();
    }

    @Test
    void isBlackjack() {
        player.addCard(ACE_CLOVER);
        player.addCard(JACK_SPADE);
        assertTrue(player.isBlackJack());
    }

    @Test
    void noneBlackjack() {
        player.addCard(TWO_DIAMOND);
        player.addCard(THREE_HEART);
        assertFalse(player.isBlackJack());
    }

    @Test
    void compareWithDealerAndWin() {
        player.addCard(JACK_SPADE);
        player.addCard(JACK_SPADE);

        dealer.addCard(TWO_DIAMOND);
        dealer.addCard(THREE_HEART);
        dealer.addCard(ACE_CLOVER);

        player.fight(dealer);
        assertThat(player.getWinCount()).isEqualTo(1);
    }

    @Test
    void compareWithDealerAndLose() {
        player.addCard(JACK_SPADE);

        dealer.addCard(TWO_DIAMOND);
        dealer.addCard(THREE_HEART);
        dealer.addCard(ACE_CLOVER);

        player.fight(dealer);
        assertThat(player.getLoseCount()).isEqualTo(1);
    }
}