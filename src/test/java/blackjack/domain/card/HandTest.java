package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    void 초기_생성시에는_카드가_0장이다() {
        Hand hand = new Hand();
        assertThat(hand.getCount()).isEqualTo(0);
    }

    @Test
    void 카드를_추가할_수_있다() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));
        assertThat(hand.getCount()).isEqualTo(1);
    }

    @Test
    void 총_점수_계산_노멀() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.TWO, CardPattern.DIAMOND));
        hand.addCard(new Card(CardPoint.QUEEN, CardPattern.DIAMOND));
        assertThat(hand.getTotalPoint()).isEqualTo(12);
    }

    @Test
    void 총_점수_계산_에이스_버스트_아님() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));
        hand.addCard(new Card(CardPoint.QUEEN, CardPattern.DIAMOND));
        assertThat(hand.getTotalPoint()).isEqualTo(21);
    }

    @Test
    void 총_점수_계산_에이스_버스트_처리() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));
        hand.addCard(new Card(CardPoint.FIVE, CardPattern.DIAMOND));
        hand.addCard(new Card(CardPoint.QUEEN, CardPattern.DIAMOND));
        assertThat(hand.getTotalPoint()).isEqualTo(16);
    }

    @Test
    void 총_점수_계산_에이스_버스트_처리_에이스_여러개() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));
        hand.addCard(new Card(CardPoint.ACE, CardPattern.CLUB));
        hand.addCard(new Card(CardPoint.ACE, CardPattern.HEART));
        hand.addCard(new Card(CardPoint.FIVE, CardPattern.DIAMOND));
        hand.addCard(new Card(CardPoint.QUEEN, CardPattern.DIAMOND));
        assertThat(hand.getTotalPoint()).isEqualTo(18);
    }

    @Test
    void 버스트인_경우() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.QUEEN, CardPattern.HEART));
        hand.addCard(new Card(CardPoint.FIVE, CardPattern.DIAMOND));
        hand.addCard(new Card(CardPoint.QUEEN, CardPattern.DIAMOND));
        assertTrue(hand.isBust());
    }

    @Test
    void 버스트가_아닌_경우() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.FIVE, CardPattern.DIAMOND));
        hand.addCard(new Card(CardPoint.QUEEN, CardPattern.DIAMOND));
        assertFalse(hand.isBust());
    }

}
