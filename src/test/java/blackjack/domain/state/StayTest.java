package blackjack.domain.state;

import blackjack.domain.Fixture;
import blackjack.state.Hit;
import blackjack.state.State;
import blackjack.state.StateFactory;
import blackjack.state.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StayTest {

    @DisplayName("stay 상태로 전환되는지 확인")
    @Test
    void isStay() {
        State state = StateFactory.draw(Fixture.CLUBS_KING, Fixture.CLUBS_TWO);

        state = state.stay();

        assertThat(state).isExactlyInstanceOf(Stay.class);
    }
}
