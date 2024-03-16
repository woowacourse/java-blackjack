package blackjack.domain.player;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

public class HandTest {
    @Test
    void 가진_패의_숫자의_합계를_구할_수_있다() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(JACK, SPADE));
        hand.add(new Card(QUEEN, SPADE));
        hand.add(new Card(KING, SPADE));

        // when
        int sum = hand.calculateScore();

        // then
        assertThat(sum).isEqualTo(30);
    }

    @Test
    void 에이스_값을_11로_계산했을_때_BUST이면_에이스_값을_1로_계산한다() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(JACK, SPADE));
        hand.add(new Card(QUEEN, SPADE));
        hand.add(new Card(ACE, SPADE));

        // when
        int sum = hand.calculateScore();

        // then
        assertThat(sum).isEqualTo(21);
    }

    @Test
    void 에이스_값을_11로_계산했을_때_BUST가_아니면_에이스_값을_11로_계산한다() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(JACK, SPADE));
        hand.add(new Card(ACE, SPADE));

        // when
        int sum = hand.calculateScore();

        // then
        assertThat(sum).isEqualTo(21);
    }

    @Test
    void 카드가_2장이고_카드_합계가_21인_경우_블랙잭이다() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(JACK, SPADE));
        hand.add(new Card(ACE, SPADE));

        // when
        boolean isBlackjack = hand.isBlackjack();

        // then
        assertThat(isBlackjack).isTrue();
    }

    @Test
    void 카드_합계가_21이어도_카드_개수가_2장이_아닌_경우_블랙잭이_아니다() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(JACK, SPADE));
        hand.add(new Card(QUEEN, SPADE));
        hand.add(new Card(ACE, SPADE));

        // when
        boolean isBlackjack = hand.isBlackjack();

        // then
        assertThat(isBlackjack).isFalse();
    }
}
