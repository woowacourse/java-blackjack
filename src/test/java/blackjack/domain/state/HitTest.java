package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class HitTest {

    @Test
    void hit() {
        final Hit hit = new Hit(new Cards(new Card(Shape.DIAMOND, Denomination.ACE)));
        final State state = hit.draw(new Card(Shape.DIAMOND, Denomination.QUEEN));
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void bust() {
        final Hit hit = new Hit(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Denomination.FIVE),
                new Card(Shape.DIAMOND, Denomination.QUEEN))
        ));
        final State state = hit.draw(new Card(Shape.DIAMOND, Denomination.KING));
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    void stay() {
        final Hit hit = new Hit(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Denomination.FIVE),
                new Card(Shape.DIAMOND, Denomination.FOUR))
        ));
        final State state = hit.stay();
        assertThat(state).isInstanceOf(Stay.class);
    }
}
