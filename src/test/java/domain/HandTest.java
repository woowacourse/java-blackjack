package domain;

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

        assertEquals(12, hand.getResult());
    }
}
