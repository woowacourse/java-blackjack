package blackjack.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.state.CardFactory.*;
import static org.assertj.core.api.Assertions.assertThat;

class FinishTest {

    @DisplayName("첫카드가 블랙젝이면 finish상태를 반환한다.")
    @Test
    void blackJackFinishTest() {
        State state = StateFactory.draw(SPADE_ACE, SPADE_TEN);

        assertThat(state).isInstanceOf(Finish.class);
        assertThat(state.isFinish()).isEqualTo(true);
    }

    @DisplayName("현재 가지고 있는 카드가 버스터면 finish상태를 반환한다.")
    @Test
    void busterFinishTest() {
        State state = StateFactory.draw(SPADE_TWO, SPADE_TEN);
        State draw = state.draw(SPADE_TEN);

        assertThat(draw).isInstanceOf(Finish.class);
        assertThat(draw.isFinish()).isEqualTo(true);
    }
}
