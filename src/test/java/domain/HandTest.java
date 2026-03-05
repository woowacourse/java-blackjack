package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    void 손패의_합_계산() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.NUM3));
        hand.addCard(new Card(Suit.SPADES, Rank.NUM5));
        hand.addCard(new Card(Suit.SPADES, Rank.JACK));

        // when, then
        assertEquals(18, hand.getSum());
    }

    @Test
    void ACE가_1로_계산되는_경우_손패의_합_계산() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.NUM3));
        hand.addCard(new Card(Suit.SPADES, Rank.QUEEN));

        // when, then
        assertEquals(14, hand.getSum());
    }

    @Test
    void ACE가_11로_계산되는_경우_손패의_합_계산() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.NUM3));
        hand.addCard(new Card(Suit.SPADES, Rank.NUM4));

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
    void 손패가_Burst인_경우() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        hand.addCard(new Card(Suit.SPADES, Rank.QUEEN));
        hand.addCard(new Card(Suit.SPADES, Rank.NUM2));

        // when, then
        assertTrue(hand.isBurst());
    }
    @Test
    void 손패가_Burst가_아닌_경우() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        hand.addCard(new Card(Suit.SPADES, Rank.NUM2));
        hand.addCard(new Card(Suit.SPADES, Rank.NUM9));

        // when, then
        assertFalse(hand.isBurst());
    }
}