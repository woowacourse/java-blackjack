package domain;

import domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackjackTest2 {
    @DisplayName("21이 넘지 않으면 hit을 유지한다.")
    @Test
    void hitToHit() {
        // given
        State state = new Hit(new Hand(
                new Card(Denomination.FOUR, Suit.CLUBS),
                new Card(Denomination.FOUR, Suit.DIAMOND)));
        // when
        state = state.hit(new Card(Denomination.ACE, Suit.DIAMOND));
        //then
        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("21이 넘으면 Bust.")
    @Test
    void hitToBust() {
        // given
        State state = new Hit(new Hand(
                new Card(Denomination.KING, Suit.CLUBS),
                new Card(Denomination.FOUR, Suit.CLUBS)
        ));
        // when
        state = state.hit(new Card(Denomination.KING, Suit.DIAMOND));
        //then
        assertThat(state).isInstanceOf(Bust.class);
    }

    @DisplayName("Bust는 hit을 할 수 없다.")
    @Test
    void bustHit() {
        // given
        final State state = new Bust(new Hand(
                new Card(Denomination.KING, Suit.CLUBS),
                new Card(Denomination.FOUR, Suit.CLUBS)
        ));

        //then
        assertThatThrownBy(() -> state.hit(new Card(Denomination.KING, Suit.DIAMOND)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Hit은 Stand를 할 수 있다.")
    @Test
    void hitToStand() {
        // given
        State state = new Hit(new Hand(
                new Card(Denomination.KING, Suit.CLUBS),
                new Card(Denomination.FOUR, Suit.CLUBS)
        ));
        // when
        state = state.stand();
        //then
        assertThat(state).isInstanceOf(Stand.class);
    }

    @DisplayName("점수가 21이 되면 블랙잭이 된다.")
    @Test
    void hitToBlackjack() {
        // given
        State state = new Hit(new Hand(
                new Card(Denomination.KING, Suit.CLUBS),
                new Card(Denomination.FOUR, Suit.CLUBS)
        ));
        // when
        state = state.hit(new Card(Denomination.SEVEN, Suit.DIAMOND));
        //then
        assertThat(state).isInstanceOf(Blackjack.class);
    }
}
