package blackjack.model.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class HitTest {

    private static final Collection<Card> DEFAULT_HITTABLE_CARDS = List.of();

    @Test
    void 카드를_추가해도_버스트가_아니라면_상태를_유지한다() {
        // given
        Hand hand = new Hand(DEFAULT_HITTABLE_CARDS);
        BlackjackState state = new Hit(hand);

        // when
        state = state.hit(new Card(Rank.ACE, Suit.HEART));

        // then
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void 카드를_추가해_버스트가_된다면_전이한다() {
        // given
        Hand hand = new Hand(DEFAULT_HITTABLE_CARDS);
        BlackjackState state = new Hit(hand);

        // when
        state = state.hit(new Card(Rank.JACK, Suit.HEART));
        state = state.hit(new Card(Rank.QUEEN, Suit.HEART));
        state = state.hit(new Card(Rank.KING, Suit.HEART));

        // then
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    void 카드를_추가해_블랙잭이_된다면_전이한다() {
        // given
        Hand hand = new Hand(DEFAULT_HITTABLE_CARDS);
        BlackjackState state = new Hit(hand);

        // when
        state = state.hit(new Card(Rank.TEN, Suit.HEART));
        state = state.hit(new Card(Rank.ACE, Suit.HEART));

        // then
        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    void 현재_상태에_대한_수익률을_제공한다() {
        // given
        Hand hand = new Hand(DEFAULT_HITTABLE_CARDS);
        Hit hit = new Hit(hand);

        // when
        double earningRate = hit.getEarningRate();

        // then
        assertThat(earningRate).isEqualTo(1);
    }
}
