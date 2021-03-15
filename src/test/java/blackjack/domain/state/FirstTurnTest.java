package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTurnTest {
    @DisplayName("상태를 생성한다. - Blackjack")
    @Test
    public void generateStateBlackjack() {
        State state = FirstTurn.generateState(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.HEART, Value.JACK)
        )));

        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @DisplayName("상태를 생성한다. - Hit")
    @Test
    public void generateStateHit() {
        State state = FirstTurn.generateState(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.TWO),
                new Card(Shape.HEART, Value.JACK)
        )));

        assertThat(state).isInstanceOf(Hit.class);
    }
}
