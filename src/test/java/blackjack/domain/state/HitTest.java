package blackjack.domain.state;

import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardPattern.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {

    final List<Card> cards = new ArrayList<>(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN)));
    State state = State.create(new Cards(cards));

    @Test
    @DisplayName("새 카드를 넣어, 카드의 합이 21이 넘게 되면 Bust를 반환한다.")
    void drawBust() {
        final State state = this.state.draw(Card.of(SPADE, JACK));
        assertThat(state.getClass()).isEqualTo(Bust.class);
    }

    @Test
    @DisplayName("stay를 호출하면 Stay 상태를 반환한다.")
    void stay() {
        final State state = this.state.stay();
        assertThat(state.getClass()).isEqualTo(Stay.class);
    }

    @Test
    @DisplayName("턴이 종료된 상태인지 반환한다.")
    void isFinished() {
        assertThat(state.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Running 상태에서 비교 결과를 반환하려고 하면 예외를 발생시킨다.")
    void compareException() {
        final Cards cards = new Cards(new ArrayList<>());
        final State running = new Hit(cards);
        final State another = new Stay(cards);

        assertThatThrownBy(() -> running.compare(another))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("턴이 종료되지 않아, 승무패 비교가 불가능합니다.");
    }
}
