package domain.state.type;

import static org.assertj.core.api.Assertions.assertThat;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import domain.gamer.Hand;
import domain.state.State;
import org.junit.jupiter.api.Test;

class HittableStateTest {

    @Test
    void 카드를_한_장_받았을_때_버스트라면_버스트_상태_객체_반환() {

        // given
        final Hand hand = new Hand();
        hand.add((new Card(Rank.KING, Shape.CLOVER)));
        hand.add((new Card(Rank.KING, Shape.DIAMOND)));
        final HittableState hittableState = new HittableState(hand, 21);

        // when
        final State state = hittableState.hit(new Card(Rank.KING, Shape.CLOVER));

        // then
        assertThat(state.type()).isEqualTo(StateType.BUST_STATE);
    }


    @Test
    void 초기_카드를_받을_때_블랙잭라면_블랙잭_상태_객체_반환() {

        // given
        final Hand hand = new Hand();
        hand.add((new Card(Rank.KING, Shape.CLOVER)));
        final HittableState hittableState = new HittableState(hand, 21);

        // when
        final State state = hittableState.hit(new Card(Rank.ACE, Shape.DIAMOND));

        // then
        assertThat(state.type()).isEqualTo(StateType.BLACKJACK_STATE);
    }

    @Test
    void 카드를_받을_때_21이면_스테이_상태_반환() {

        // given
        final Hand hand = new Hand();
        hand.add((new Card(Rank.KING, Shape.CLOVER)));
        hand.add((new Card(Rank.KING, Shape.DIAMOND)));
        final HittableState hittableState = new HittableState(hand, 21);

        // when
        final State state = hittableState.hit(new Card(Rank.ACE, Shape.DIAMOND));

        // then
        assertThat(state.type()).isEqualTo(StateType.STAY_STATE);
    }

    @Test
    void 카드를_받나서_더_받을_수_있다면_hittalbe_상태_반환() {

        // given
        final Hand hand = new Hand();
        hand.add((new Card(Rank.FIVE, Shape.CLOVER)));
        hand.add((new Card(Rank.FOUR, Shape.DIAMOND)));
        final HittableState hittableState = new HittableState(hand, 21);

        // when
        final State state = hittableState.hit(new Card(Rank.SIX, Shape.DIAMOND));

        // then
        assertThat(state.type()).isEqualTo(StateType.HITTABLE_STATE);
    }

    @Test
    void 자신의_상태를_반환() {
        // given
        final HittableState hittableState = HittableState.initializePlayerState();

        // when
        // then
        assertThat(hittableState.type()).isEqualTo(StateType.HITTABLE_STATE);
    }

    @Test
    void stay메서드_호출_시_stay상태를_반환() {
        // given
        final HittableState hittableState = HittableState.initializePlayerState();

        // when
        // then
        assertThat(hittableState.stay().type()).isEqualTo(StateType.STAY_STATE);
    }
}
