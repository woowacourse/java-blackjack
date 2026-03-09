package domain;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class HandTest {

    @Test
    @DisplayName("점수 합산이 21 초과이면 버스트로 판단한다.")
    void Ace_21초과_버스트_판단() {
        // given
        List<Card> cards = List.of(new Card(Rank.EIGHT, Suit.DIAMOND), new Card(Rank.KING, Suit.HEART), new Card(Rank.JACK, Suit.DIAMOND));
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        Assertions.assertTrue(hand.isBust());
    }

    @Test
    @DisplayName("Hand의 패가 블랙잭인지 판단한다.")
    void 블랙잭_판단() {
        // given
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.KING, Suit.HEART));
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        Assertions.assertTrue(hand.isBlackJack());
    }

    @Test
    @DisplayName("3장 이상의 패로 점수 21을 만든 경우 블랙잭이 아니라고 판단한다.")
    void 블랙잭_3장_판단() {
        // given
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.EIGHT, Suit.HEART), new Card(Rank.TWO, Suit.DIAMOND));
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        Assertions.assertFalse(hand.isBlackJack());
    }

    @Test
    @DisplayName("Ace가 Hand에 있지만 점수가 21이 되지 않는 경우 블랙잭이 아니라고 판단한다.")
    void 블랙잭_점수_판단() {
        // given
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.EIGHT, Suit.HEART));
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        Assertions.assertFalse(hand.isBlackJack());
    }
}
