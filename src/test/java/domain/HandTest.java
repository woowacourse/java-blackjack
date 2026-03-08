package domain;

import org.junit.jupiter.api.Test;

import static domain.constant.Rank.*;
import static domain.constant.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    @Test
    void 추가된_카드를_보관한다() {
        Hand hand = new Hand();

        hand.addCard(new Card(ACE, SPADE));
        hand.addCard(new Card(QUEEN, HEART));

        assertThat(hand.showHand())
                .containsExactly("A스페이드", "Q하트");
    }

    @Test
    void ACE를_제외한_카드들의_기본점수를_합산한다() {
        Hand hand = new Hand();
        hand.addCard(new Card(EIGHT, SPADE));
        hand.addCard(new Card(QUEEN, SPADE));

        int totalScore = hand.calculateScore();

        assertThat(totalScore).isEqualTo(18);
    }
}
