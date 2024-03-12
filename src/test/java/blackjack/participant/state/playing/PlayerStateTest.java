package blackjack.participant.state.playing;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import blackjack.participant.Hand;
import blackjack.participant.state.GameState;
import blackjack.participant.state.terminated.BustedState;
import blackjack.participant.state.terminated.StandState;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerStateTest {

    @Test
    @DisplayName("카드를 뽑아 버스트가 되는 경우, BustedState로 전이한다.")
    void bustedTransitionTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.SPADE, Number.TEN)
        );
        PlayerState state = new PlayerState(new Hand(cards));
        // when
        GameState nextState = state.drawCard(new Card(Shape.SPADE, Number.TWO));
        // then
        assertThat(nextState).isInstanceOf(BustedState.class);
    }

    @Test
    @DisplayName("카드를 뽑아도 더 뽑을 수 있는 경우, 상태를 유지한다.")
    void keepStateOnDrawTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.SPADE, Number.TEN)
        );
        PlayerState state = new PlayerState(new Hand(cards));
        // when
        GameState nextState = state.drawCard(new Card(Shape.SPADE, Number.ACE));
        // then
        assertThat(nextState).isInstanceOf(PlayerState.class);
    }

    @Test
    @DisplayName("카드를 뽑지 않기로 하는 경우, Stand 상태로 전이한다.")
    void standTransitionTest() {
        // given
        GameState state = new PlayerState();
        // when
        state = state.stand();
        // then
        assertThat(state).isInstanceOf(StandState.class);
    }
}
