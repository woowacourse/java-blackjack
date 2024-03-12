package blackjack.participant.state.terminated;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import blackjack.participant.Hand;
import blackjack.participant.state.GameState;
import blackjack.participant.state.playing.PlayerState;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TerminatedStateTest {

    private GameState createTerminatedState() {
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.SPADE, Number.TEN)
        );
        GameState state = new PlayerState(new Hand(cards));
        return state.drawCard(new Card(Shape.SPADE, Number.TWO));
    }

    @Test
    @DisplayName("종료 상태에서는 드로우를 할 수 없다.")
    void unsupportedOperationOnDrawTest() {
        // given
        GameState state = createTerminatedState();
        Card card = new Card(Shape.SPADE, Number.TEN);
        // when, then
        assertThatThrownBy(() -> state.drawCard(card))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("[ERROR] 현재 상태에서는 드로우 여부를 결정할 수 없습니다.");
    }

    @Test
    @DisplayName("종료 상태에서는 스탠드를 할 수 없다.")
    void unsupportedOperationOnStandTest() {
        // given
        GameState state = createTerminatedState();
        // when, then
        assertThatThrownBy(state::stand)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("[ERROR] 현재 상태에서는 스탠드 여부를 결정할 수 없습니다.");
    }
}
