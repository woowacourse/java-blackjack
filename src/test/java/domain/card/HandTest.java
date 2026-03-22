package domain.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {
    @Test
    @DisplayName("Hand는 카드 추가 시 결과 점수가 증가한다")
    void addCardIncreasesScore() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.SPADE, Rank.TEN));
        hand.addCard(new Card(Suit.HEART, Rank.TWO));

        assertEquals(12, hand.getScore());
    }

    @Test
    @DisplayName("ACE 점수는 정상적으로 계산할 수 있다")
    void addCardIncreasesScore2() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.SPADE, Rank.ACE)); // 11
        hand.addCard(new Card(Suit.HEART, Rank.KING)); // 10 -> 21
        hand.addCard(new Card(Suit.HEART, Rank.FIVE)); // 5 -> 16 (ACE 보정)
        hand.addCard(new Card(Suit.SPADE, Rank.ACE)); // 1 -> 17 (ACE 보정)
        hand.addCard(new Card(Suit.CLUB, Rank.ACE)); // 1 -> 18 (ACE 보정)
        hand.addCard(new Card(Suit.CLUB, Rank.KING)); // 1 -> 28

        assertEquals(28, hand.getScore());
    }
}
