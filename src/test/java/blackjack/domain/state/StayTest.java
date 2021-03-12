package blackjack.domain.state;

import blackjack.domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.state.CardFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StayTest {
    @DisplayName("hit상태일 때 stay를 하면, Stay상태(Finish 상태)를 반환한다.")
    @Test
    void hitStayTest() {
        State state = StateFactory.draw(SPADE_JACK, SPADE_TEN);
        State stay = state.stay();

        assertThat(stay).isInstanceOf(Finish.class);
        assertThat(stay).isInstanceOf(Stay.class);
    }

    @DisplayName("hit상태가 아닐때(블랙젝, 버스터) stay를 하면, 에러가 발생한다.")
    @Test
    void notHitStayTest() {
        State state = StateFactory.draw(SPADE_TWO, SPADE_TEN);
        State draw = state.draw(SPADE_TEN);

        assertThatThrownBy(() -> draw.stay())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("stay상태 일때 카드를 추가하려 하면, 에러가 발생한다.")
    @Test
    void alreadyStayExceptionTest() {
        State state = StateFactory.draw(SPADE_TWO, SPADE_TEN);
        Player player = new Player(state, "pobi");

        State stay = player.getState().stay();
        player.changeState(stay);

        assertThatThrownBy(() -> player.getState().stay())
                .isInstanceOf(UnsupportedOperationException.class);

    }
}
