package blackjack.domain.state;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_JACK;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StayTest {

    @Test
    @DisplayName("Stay 상태에서 stay 시 예외 발생")
    void stay() {
        State state = new Stay(new Cards(List.of(SPADE_ACE, SPADE_JACK)));

        assertThatThrownBy(state::stay)
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Stay 상태에서 draw 시 예외 발생")
    void draw() {
        State state = new Stay(new Cards(List.of(SPADE_ACE, SPADE_JACK)));

        assertThatThrownBy(() -> state.draw(SPADE_ACE))
            .isInstanceOf(IllegalStateException.class);
    }
}
