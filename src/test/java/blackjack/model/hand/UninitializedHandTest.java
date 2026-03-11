package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.Collection;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UninitializedHandTest {

    static final Card DEFAULT_CARD = new Card(Rank.TWO, Suit.HEART);

    @Test
    void 처음_생성하면_카드를_지니지_않는다() {
        // given
        Hand hand = new UninitializedHand();

        // when
        Collection<Card> cards = hand.getCards();

        // then
        assertThat(cards.isEmpty()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void 지닌_카드_수가_일정_이하라면_현재_상태를_유지한다(int cardAddCount) {
        Hand hand = new UninitializedHand();
        for (int i = 0; i < cardAddCount; i++) {
            hand = hand.hit(DEFAULT_CARD);
        }

        assertThat(hand).isInstanceOf(UninitializedHand.class);
    }

    @Nested
    class 지닌_카드_수가_일정_이상이라면_다음_상태로_전이한다 {

        @Test
        void 지닌_카드의_가치_합이_21이라면_블랙잭으로_전이한다() {
            Hand hand = new UninitializedHand();
            hand = hand.hit(new Card(Rank.ACE, Suit.HEART));
            hand = hand.hit(new Card(Rank.JACK, Suit.HEART));

            assertThat(hand).isInstanceOf(BlackjackHand.class);
        }

        @Test
        void 지닌_카드의_가치_합이_21_미만이라면_힛으로_전이한다() {
            Hand hand = new UninitializedHand();
            hand = hand.hit(new Card(Rank.ACE, Suit.HEART));
            hand = hand.hit(new Card(Rank.TWO, Suit.HEART));

            assertThat(hand).isInstanceOf(HitHand.class);
        }
    }
}
