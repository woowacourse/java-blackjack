package domain.player;

import domain.deck.Card;
import domain.deck.Deck;
import domain.deck.Rank;
import domain.deck.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DealerTest {

    Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @DisplayName("딜러의 점수가 16이하이면 더 뽑아야 한다.")
    @Test
    void shouldDealerDrawTest() {
        Card firstCard = Card.of(Suit.CLOVER, Rank.FOUR);
        Card secondCard = Card.of(Suit.CLOVER, Rank.KING);

        dealer.hit(firstCard);
        dealer.hit(secondCard);

        assertTrue(dealer.isDealerHit());
    }

    @DisplayName("딜러의 점수가 17이상이면 더 뽑으면 안된다.")
    @Test
    void shouldNotDealerDrawTest() {
        Card firstCard = Card.of(Suit.CLOVER, Rank.ACE);
        Card secondCard = Card.of(Suit.CLOVER, Rank.KING);

        dealer.hit(firstCard);
        dealer.hit(secondCard);

        assertFalse(dealer.isDealerHit());
    }

    @Test
    void hitTwiceTest() {
        Deck deck = new Deck();
        dealer.hitTwice(deck);
        assertTrue(dealer.getCards().size() == 2);
    }
}
