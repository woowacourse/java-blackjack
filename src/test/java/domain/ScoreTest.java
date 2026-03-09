package domain;

import domain.card.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScoreTest {

    @Test
    @DisplayName("Ace 없는 Hand에 대해서 점수를 합산한다.")
    void Ace없는_Hand_점수_합계() {
        // given
        List<Card> cards = List.of(new Card(Rank.TWO, Suit.DIAMOND), new Card(Rank.JACK, Suit.DIAMOND));
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        Assertions.assertEquals(12, Score.calculate(hand));
    }

    @Test
    @DisplayName("합이 21이하인 경우 ACE를 11로 계산한다.")
    void Ace_21이하_버스트_처리 () {
        // given
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.JACK, Suit.DIAMOND));
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        Assertions.assertEquals(21, Score.calculate(hand));
    }

    @Test
    @DisplayName("합이 21초과인 경우 ACE를 1로 계산한다.")
    void Ace_21초과_버스트_처리() {
        // given
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.ACE, Suit.HEART));
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        Assertions.assertEquals(12, Score.calculate(hand));
    }
}
