package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.TestUtil.createSpadesCard;

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
    void 딜러가_Bust인_경우_isBust가_True를_리턴한다() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.draw(new Card(Suit.CLUBS, Rank.KING));
        dealer.draw(new Card(Suit.CLUBS, Rank.QUEEN));
        dealer.draw(new Card(Suit.CLUBS, Rank.JACK));

        // then
        assertTrue(dealer.isBust());
    }

    @Test
    void 딜러가_Bust가_아닌_경우_isBust가_False를_리턴한다() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.draw(new Card(Suit.CLUBS, Rank.KING));
        dealer.draw(new Card(Suit.CLUBS, Rank.QUEEN));

        // then
        assertFalse(dealer.isBust());
    }

    @Test
    void 딜러가_블랙잭이면_isBlackjack이_true를_반환한다() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.draw(createSpadesCard(Rank.ACE));
        dealer.draw(createSpadesCard(Rank.QUEEN));

        // then
        assertTrue(dealer.isBlackjack());
    }

    @Test
    void 딜러가_블랙잭이면_isBlackjack이_false를_반환한다() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.draw(createSpadesCard(Rank.ACE));

        // then
        assertFalse(dealer.isBlackjack());
    }
}
