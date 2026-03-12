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

    @Test
    void ACE가_핸드에_있고_기본점수합계에_10점을_더해도_21점을_넘지_않으면_ACE_1장을_11점으로_계산한다() {
        Hand hand = new Hand();
        hand.addCard(new Card(ACE, SPADE));
        hand.addCard(new Card(SEVEN, SPADE));

        int totalScore = hand.calculateScore();

        assertThat(totalScore).isEqualTo(18);
    }

    @Test
    void ACE가_핸드에_있고_기본점수합계에_10점을_더해서_21점을_넘으면_ACE는_1점으로_계산한다() {
        Hand hand = new Hand();
        hand.addCard(new Card(ACE, SPADE));
        hand.addCard(new Card(ACE, HEART));
        hand.addCard(new Card(JACK, SPADE));

        int totalScore = hand.calculateScore();

        assertThat(totalScore).isEqualTo(12);
    }

    @Test
    void 합계_점수가_21점을_초과하면_버스트이다() {
        Hand hand = new Hand();
        hand.addCard(new Card(TEN, SPADE));
        hand.addCard(new Card(JACK, HEART));
        hand.addCard(new Card(JACK, SPADE));

        assertThat(hand.isBust()).isTrue();
    }

    @Test
    void 합계_점수가_21점_이하이면_버스트가_아니다() {
        Hand hand = new Hand();
        hand.addCard(new Card(ACE, SPADE));
        hand.addCard(new Card(KING, HEART));

        assertThat(hand.isBust()).isFalse();
    }

    @Test
    void 첫_번째_카드를_확인한다() {
        Hand hand = new Hand();
        hand.addCard(new Card(ACE, SPADE));
        hand.addCard(new Card(KING, HEART));

        String firstCard = hand.getFirstCard();

        assertThat(firstCard).isEqualTo("A스페이드");
    }

    @Test
    void 핸드에_존재하는_카드의_숫자를_확인한다() {
        Hand hand = new Hand();
        hand.addCard(new Card(ACE, SPADE));
        hand.addCard(new Card(KING, HEART));
        hand.addCard(new Card(SEVEN, DIAMOND));

        int handSize = hand.size();

        assertThat(handSize).isEqualTo(3);
    }
}
