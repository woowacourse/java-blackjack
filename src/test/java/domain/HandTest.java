package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    void 손패에_있는_카드들의_점수_합을_정확히_계산한다() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.NUM3));
        hand.addCard(new Card(Suit.SPADES, Rank.NUM5));
        hand.addCard(new Card(Suit.SPADES, Rank.JACK));

        // when, then
        assertEquals(18, hand.calculateScore());
    }

    @Test
    void 점수_합이_21을_초과할_상황이면_ACE를_1점으로_계산한다() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.NUM3));
        hand.addCard(new Card(Suit.SPADES, Rank.QUEEN));

        // when, then
        assertEquals(14, hand.calculateScore());
    }

    @Test
    void 점수_합이_21_이하라면_ACE를_11점으로_계산한다() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.NUM3));
        hand.addCard(new Card(Suit.SPADES, Rank.NUM4));

        // when, then
        assertEquals(18, hand.calculateScore());
    }

    @Test
    void 손패에_새로운_카드를_추가하면_내부_목록에_정상적으로_포함된다() {
        //given
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));

        // when, then
        assertTrue(hand.getCards().contains(new Card(Suit.SPADES, Rank.ACE)));
    }

    @Test
    void 첫번째_카드를_반환한다() {
        Hand hand = new Hand();
        Card first = new Card(Suit.SPADES, Rank.ACE);
        hand.addCard(first);
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));

        assertEquals(first, hand.getFirstCard());
    }
}