package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    void 손패의_합_계산() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.THREE));
        hand.addCard(new Card(Suit.SPADES, Rank.FIVE));
        hand.addCard(new Card(Suit.SPADES, Rank.JACK));

        // when, then
        assertEquals(18, hand.getSum());
    }

    @Test
    void ACE가_1로_계산되는_경우_손패의_합_계산() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.THREE));
        hand.addCard(new Card(Suit.SPADES, Rank.QUEEN));

        // when, then
        assertEquals(14, hand.getSum());
    }

    @Test
    void ACE가_11로_계산되는_경우_손패의_합_계산() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.THREE));
        hand.addCard(new Card(Suit.SPADES, Rank.FOUR));

        // when, then
        assertEquals(18, hand.getSum());
    }

    @Test
    void 손패에_정상적으로_카드가_추가되는_경우() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));

        // when, then
        assertTrue(hand.getCards().contains(new Card(Suit.SPADES, Rank.ACE)));
    }

    @Test
    void 손패가_Bust인_경우_isBust가_True를_리턴한다() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        hand.addCard(new Card(Suit.SPADES, Rank.QUEEN));
        hand.addCard(new Card(Suit.SPADES, Rank.TWO));

        // when, then
        assertTrue(hand.isBust());
    }

    @Test
    void 손패가_Bust가_아닌_경우_isBust가_False를_리턴한다() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        hand.addCard(new Card(Suit.SPADES, Rank.TWO));
        hand.addCard(new Card(Suit.SPADES, Rank.NINE));

        // when, then
        assertFalse(hand.isBust());
    }
}
