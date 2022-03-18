package blackjack.domain.state;

import static blackjack.domain.CardFixtures.JACK_SPACE;
import static blackjack.domain.CardFixtures.KING_SPACE;
import static blackjack.domain.CardFixtures.THREE_SPACE;
import static blackjack.domain.CardFixtures.TWO_SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {

    @DisplayName("버스트 상태에서 draw는 불가능하다. 즉 실행할 경우 예외를 던진다.")
    @Test
    void 버스트_드로우() {
        State bust = new Ready().draw(JACK_SPACE)
                .draw(KING_SPACE)
                .draw(TWO_SPACE);

        assertThatThrownBy(() -> bust.draw(THREE_SPACE))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("버스트 상태에서 stay는 불가능하다. 즉 실행할 경우 예외를 던진다.")
    @Test
    void 버스트_스테이_불가() {
        State bust = new Ready().draw(JACK_SPACE)
                .draw(KING_SPACE)
                .draw(TWO_SPACE);

        assertThatThrownBy(() -> bust.stay())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("버스트 상태는 실행 불가능 상태이므로 실행 가능 유무는 false이다.")
    @Test
    void 버스트_준비상태() {
        State bust = new Ready().draw(JACK_SPACE)
                .draw(KING_SPACE)
                .draw(TWO_SPACE);

        boolean result = bust.isRunning();

        assertThat(result).isFalse();
    }

    @DisplayName("버스트 상태의 끝난 상태 유무는 true이다.")
    @Test
    void 버스트_끝난상태() {
        State bust = new Ready().draw(JACK_SPACE)
                .draw(KING_SPACE)
                .draw(TWO_SPACE);

        boolean result = bust.isFinished();

        assertThat(result).isTrue();
    }
}