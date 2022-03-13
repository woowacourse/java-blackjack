package blackjack.domain.state.finished;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {

    final Cards cards = new Cards(new ArrayList<>());
    final State state = new Stay(cards);

    @Test
    @DisplayName("턴이 종료된 상태에서 카드를 더 받으려 하면 예외를 발생시킨다.")
    void drawException() {
        assertThatThrownBy(() -> state.draw(Card.of(CardPattern.SPADE, CardNumber.TWO)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("턴이 종료 된 플레이어는 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("턴이 종료된 상태에서 stay를 호출하면 예외를 발생시킨다.")
    void stay() {
        assertThatThrownBy(state::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("턴이 종료 된 플레이어는 다시 턴을 종료할 수 없습니다.");
    }

    @Test
    @DisplayName("턴이 종료되었는지 반환한다.")
    void isFinished() {
        assertThat(state.isFinished()).isTrue();
    }
}
