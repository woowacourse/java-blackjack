package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTurnTest {
    @DisplayName("상태를 확인한다. - 블랙잭")
    @Test
    public void blackjack() {
        final State state = FirstTurn.draw(
                new Card(Shape.CLOVER, Value.ACE),
                new Card(Shape.HEART, Value.KING));

        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @DisplayName("상태를 확인한다. - 힛")
    @Test
    public void hit() {
        final State state = FirstTurn.draw(
                new Card(Shape.CLOVER, Value.TEN),
                new Card(Shape.HEART, Value.TWO));

        assertThat(state).isInstanceOf(Hit.class);
    }
}
