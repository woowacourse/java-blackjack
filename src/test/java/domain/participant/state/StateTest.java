package domain.participant.state;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Hand;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StateTest {
    @DisplayName("21이 넘지 않으면 hit을 유지한다.")
    @Test
    void hitToHit() {
        // given
        State state = new Hit(new Hand(
                new Card(Denomination.FOUR, Suit.CLUBS),
                new Card(Denomination.FOUR, Suit.DIAMOND)));
        // when
        state = state.draw(new Card(Denomination.ACE, Suit.DIAMOND));
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
        state = state.draw(new Card(Denomination.KING, Suit.DIAMOND));
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
        assertThatThrownBy(() -> state.draw(new Card(Denomination.KING, Suit.DIAMOND)))
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

    @DisplayName("Ready 상태에서 두장을 뽑아 21보다 작으면 Hit")
    @Test
    void readyToHit() {
        // given
        State state = new Ready(new Hand(
                new Card(Denomination.KING, Suit.CLUBS),
                new Card(Denomination.FOUR, Suit.CLUBS)
        ));
        // when
        state = state.draw(new Card(Denomination.ACE, Suit.CLUBS));
        //then
        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("Ready 상태에서 두장을 뽑아 21이면 Blackjack")
    @Test
    void readyToBlackjack() {
        // given
        State state = new Ready(new Hand());
        // when
        state = state.draw(new Card(Denomination.KING, Suit.CLUBS));
        state = state.draw(new Card(Denomination.ACE, Suit.CLUBS));
        //then
        assertThat(state).isInstanceOf(Blackjack.class);
    }
}
