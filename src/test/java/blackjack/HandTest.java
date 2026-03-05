package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

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
    void 총_점수_계산() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.TWO, CardPattern.DIAMOND));
        hand.addCard(new Card(CardPoint.QUEEN, CardPattern.DIAMOND));
        assertThat(hand.getTotalPoint()).isEqualTo(12);
    }
}
