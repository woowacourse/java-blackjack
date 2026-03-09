package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 드로우가_정상적으로_동작하는_경우() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.draw(new Card(Suit.CLUBS, Rank.ACE));

        // then
        assertEquals(1, dealer.getHand().getCards().size());
    }

    @Test
    void 딜러가_Bust인_경우() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.draw(new Card(Suit.CLUBS, Rank.KING));
        dealer.draw(new Card(Suit.CLUBS, Rank.QUEEN));
        dealer.draw(new Card(Suit.CLUBS, Rank.JACK));

        // then
        assertTrue(dealer.isBust());
    }
}
