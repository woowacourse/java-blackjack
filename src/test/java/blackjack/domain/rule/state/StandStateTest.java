package blackjack.domain.rule.state;

import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardShape.DIA;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Hands;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StandStateTest {

    @DisplayName("Stand 상태에서 카드를 뽑을 수 없다.")
    @Test
    void draw() {
        final StandState state = new StandState(new Hands(List.of(Card.of(TEN, DIA), Card.of(JACK, DIA))));
        final Card card = Card.of(CardNumber.ACE, DIA);

        assertThatThrownBy(() -> state.draw(card))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Stand 상태에서 stand를 호출할 수 없다.")
    @Test
    void stand() {
        final StandState state = new StandState(new Hands(List.of(Card.of(TEN, DIA), Card.of(JACK, DIA))));

        assertThatThrownBy(state::stand)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
