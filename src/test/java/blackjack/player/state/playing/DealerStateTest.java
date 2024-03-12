package blackjack.player.state.playing;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import blackjack.player.Hand;
import blackjack.player.state.GameState;
import blackjack.player.state.terminated.BustedState;
import blackjack.player.state.terminated.StandState;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerStateTest {

    @Test
    @DisplayName("딜러의 점수가 16점 이하이면, 자신의 상태를 유지한다.")
    void keepDealerStateTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.JACK)
        );
        Card card = new Card(Shape.DIAMOND, Number.SIX);
        Hand hand = new Hand(cards);
        DealerState dealerState = new DealerState(hand);
        // when
        GameState state = dealerState.drawCard(card);
        // then
        assertThat(state).isInstanceOf(DealerState.class);
    }

    @Test
    @DisplayName("딜러가 뽑았을 때 16점 초과이면, 자신의 상태를 Stand로 전이한다.")
    void transitionToStandStateTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.JACK)
        );
        Card card = new Card(Shape.DIAMOND, Number.SEVEN);
        Hand hand = new Hand(cards);
        DealerState dealerState = new DealerState(hand);
        // when
        GameState state = dealerState.drawCard(card);
        // then
        assertThat(state).isInstanceOf(StandState.class);
    }

    @Test
    @DisplayName("stand로 전이할 때, 점수를 올바르게 계산한다.")
    void standTransitionScoreTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.JACK)
        );
        Card card = new Card(Shape.DIAMOND, Number.SEVEN);
        Hand hand = new Hand(cards);
        DealerState dealerState = new DealerState(hand);
        // when
        GameState state = dealerState.drawCard(card);
        // then
        assertThat(state.isTerminated()).isTrue();
        assertThat(state.getScore().toInt()).isEqualTo(17);
    }

    @Test
    @DisplayName("딜러가 세 번째 카드를 뽑아 21점을 넘어가면, Busted로 전이된다.")
    void dealerBustedTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.DIAMOND, Number.SIX)
        );
        Card card = new Card(Shape.HEART, Number.SIX);
        Hand hand = new Hand(cards);
        DealerState dealerState = new DealerState(hand);
        // when
        GameState state = dealerState.drawCard(card);
        // then
        assertThat(state).isInstanceOf(BustedState.class);
    }
}
