package blackjack.domain.player;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

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
}
