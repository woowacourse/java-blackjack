package blackjack.domain.rule.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Hands;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustStateTest {

    final State state = new BustState(new Hands(List.of(
            Card.of(CardNumber.TEN, CardShape.SPADE),
            Card.of(CardNumber.QUEEN, CardShape.SPADE),
            Card.of(CardNumber.TWO, CardShape.SPADE))));

    @DisplayName("Burst 상태에서 카드를 뽑을 수 없다.")
    @Test
    void draw() {
        final Card card = Card.of(CardNumber.ACE, CardShape.DIA);
        assertThatThrownBy(() -> state.draw(card))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Burst 상태에서 stand할 수 없다..")
    @Test
    void stand() {
        assertThatThrownBy(() -> state.stand())
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
