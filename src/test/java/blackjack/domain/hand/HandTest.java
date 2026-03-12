package blackjack.domain.hand;

import blackjack.domain.card.Card;
import blackjack.domain.card.Figure;
import blackjack.domain.card.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    @Test
    @DisplayName("카드가 2장이고 ACE와 KING을 가지면 블랙잭이다")
    void blackjackTest() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(Figure.CLOVER, Number.ACE));
        hand.add(new Card(Figure.CLOVER, Number.KING));

        // when & then
        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("카드가 3장이고 합계가 21이면 블랙잭이 아니다")
    void isNotBlackjackWhenScoreIs21AndThreeCards() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(Figure.CLOVER, Number.ACE));
        hand.add(new Card(Figure.CLOVER, Number.JACK));
        hand.add(new Card(Figure.CLOVER, Number.KING));

        // when & then
        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("ACE를 11점으로 계산했을 때 21을 초과하면 1점으로 계산한다")
    void translateAce11To1When21isOver() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(Figure.CLOVER, Number.ACE));
        hand.add(new Card(Figure.CLOVER, Number.KING));

        // when
        Score score1 = hand.calculateScore();
        hand.add(new Card(Figure.CLOVER, Number.NINE));
        Score score2 = hand.calculateScore();

        // then
        assertThat(score1.getScore()).isEqualTo(21);
        assertThat(score2.getScore()).isEqualTo(20);
    }
}
