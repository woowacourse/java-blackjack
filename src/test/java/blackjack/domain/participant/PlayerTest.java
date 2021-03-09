package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    private static final Card TWO_DIAMOND = new Card(CardNumber.TWO, Shape.DIAMOND);
    private static final Card JACK_SPADE = new Card(CardNumber.JACK, Shape.SPADE);
    private static final Card THREE_HEART = new Card(CardNumber.THREE, Shape.HEART);
    private static final Card ACE_CLOVER = new Card(CardNumber.ACE, Shape.CLOVER);
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("bob");
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
}