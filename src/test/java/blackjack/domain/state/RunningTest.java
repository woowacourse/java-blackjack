package blackjack.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.state.CardFactory.SPADE_TEN;
import static blackjack.domain.state.CardFactory.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;

class RunningTest {
    @DisplayName("가지고 있는 카드가 블랙젝이나 버스터가 아니면 Running상태를 반환한다.")
    @Test
    void runningTest() {
        State state = StateFactory.draw(SPADE_TWO, SPADE_TEN);
        State draw = state.draw(SPADE_TWO);

        assertThat(draw).isInstanceOf(Running.class);
        assertThat(draw.isFinish()).isEqualTo(false);
    }
}
