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
        Hand hand = new Hand();
        hand.add(new Card(JACK, SPADE));
        hand.add(new Card(QUEEN, SPADE));
        hand.add(new Card(KING, SPADE));

        int sum = hand.getScore();

        assertThat(sum).isEqualTo(30);
    }

    @Test
    void 에이스_카드가_4개인_경우에_합계를_구할_수_있다() {
        Hand hand = new Hand();
        hand.add(new Card(ACE, SPADE));
        hand.add(new Card(ACE, SPADE));
        hand.add(new Card(ACE, SPADE));
        hand.add(new Card(ACE, SPADE));

        int sum = hand.getScore();

        assertThat(sum).isEqualTo(14);
    }

    @Test
    void 에이스_카드를_포함해서_합계_21인_경우에_정확하게_계산할_수_있다() {
        Hand hand = new Hand();
        hand.add(new Card(ACE, SPADE));
        hand.add(new Card(KING, SPADE));

        int sum = hand.getScore();

        assertThat(sum).isEqualTo(21);
    }

    @Test
    void 패가_오직_두_장_있다면_참을_반환한다() {
        Hand hand = new Hand();
        hand.add(new Card(ACE, SPADE));
        hand.add(new Card(KING, SPADE));

        assertThat(hand.hasExactlyTwoCards()).isTrue();
    }

    @Test
    void 패가_세_장_있다면_거짓을_반환한다() {
        Hand hand = new Hand();
        hand.add(new Card(ACE, SPADE));
        hand.add(new Card(KING, SPADE));
        hand.add(new Card(QUEEN, SPADE));

        assertThat(hand.hasExactlyTwoCards()).isFalse();
    }

    @Test
    void 첫_번째_카드를_반환한다() {
        Hand hand = new Hand();
        hand.add(new Card(ACE, SPADE));
        hand.add(new Card(KING, SPADE));

        Card firstCard = hand.firstCard();

        assertThat(firstCard.getNumber()).isEqualTo(ACE);
        assertThat(firstCard.getShape()).isEqualTo(SPADE);
    }
}
