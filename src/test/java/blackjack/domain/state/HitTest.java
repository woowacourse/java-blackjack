package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HitTest {
    @DisplayName("상태를 확인한다. - 힛")
    @Test
    public void hit() {
        final Hit hit = new Hit(
                new Card(Shape.CLOVER, Value.ACE),
                new Card(Shape.HEART, Value.TWO));
        final State state = hit.draw(new Card(Shape.DIAMOND, Value.TEN));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("상태를 확인한다. - 버스트")
    @Test
    public void bust() {
        final Hit hit = new Hit(
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.TWO));
        final State state = hit.draw(new Card(Shape.DIAMOND, Value.TEN));

        assertThat(state).isInstanceOf(Bust.class);
    }

    @DisplayName("상태를 확인한다. - 스테이")
    @Test
    public void stay() {
        final Hit hit = new Hit(
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.TWO));
        final State state = hit.stay();

        assertThat(state).isInstanceOf(Stay.class);
    }
}
