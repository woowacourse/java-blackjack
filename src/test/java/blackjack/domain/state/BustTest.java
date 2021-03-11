package blackjack.domain.state;

import blackjack.domain.Fixture;
import blackjack.state.Bust;
import blackjack.state.Cards;
import blackjack.state.Hit;
import blackjack.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BustTest {

    @DisplayName("21이상일 경우 Bust")
    @Test
    void isBust() {
        Cards cards = new Cards(new ArrayList<>(Arrays.asList(Fixture.CLUBS_KING, Fixture.CLUBS_TEN)));

        State state = new Hit(cards);

        State state2 = state.draw(Fixture.CLUBS_TWO);

        assertThat(state2).isInstanceOf(Bust.class);
    }
}
