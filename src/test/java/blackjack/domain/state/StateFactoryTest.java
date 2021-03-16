package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StateFactoryTest {

    @Test
    void blackjack() {
        final State state = StateFactory.draw(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Denomination.ACE),
                new Card(Shape.DIAMOND, Denomination.QUEEN))
                ));
        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    void hit() {
        final State state = StateFactory.draw(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Denomination.ACE),
                new Card(Shape.DIAMOND, Denomination.TWO))
        ));
        assertThat(state).isInstanceOf(Hit.class);
    }
}
