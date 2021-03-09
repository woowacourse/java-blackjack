package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StayTest {

    @Test
    @DisplayName("Stay 상태인 경우 카드를 더 뽑을 수 없다.")
    void isFinished() {
        State state = new Stay();
        assertTrue(state.isFinished());
    }

    @Test
    @DisplayName("카드를 뽑을 수 없는 상태 예외처리")
    void cannotDraw() {
        assertThatThrownBy(() -> {
            State state = new Stay();
            state.draw(new Card(Shape.DIAMOND, Denomination.KING));
        }).isInstanceOf(IllegalStateException.class);
    }
}