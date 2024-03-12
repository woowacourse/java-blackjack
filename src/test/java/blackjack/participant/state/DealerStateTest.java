package blackjack.participant.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import blackjack.participant.Hand;
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
}
