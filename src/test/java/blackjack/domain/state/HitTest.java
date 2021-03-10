package blackjack.domain.state;

import blackjack.domain.Fixture;
import blackjack.state.Hit;
import blackjack.state.State;
import blackjack.state.StateFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HitTest {

    @DisplayName("초기 Hit상태 확인")
    @Test
    void initialHitCheck() {
        State state = StateFactory.draw(Fixture.CLUBS_KING, Fixture.CLUBS_TWO);

        assertThat(state).isExactlyInstanceOf(Hit.class);
    }
}
