package blackjack.domain.state;

import static blackjack.domain.CardFixtures.ACE_SPACE;
import static blackjack.domain.CardFixtures.JACK_SPACE;
import static blackjack.domain.CardFixtures.TWO_SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @DisplayName("Card를 한장만 가지고 있으면 Ready 상태를 유지한다.")
    @Test
    void 레디_카드_한장() {
        State ready = new Ready();

        State result = ready.draw(JACK_SPACE);

        assertThat(result).isInstanceOf(Ready.class);
    }

    @DisplayName("Card를 두장 가지고 있으면 준비가 완료되어 Hit 상태로 변경된다.")
    @Test
    void 레디_카드_두장() {
        State ready = new Ready();

        State result = ready.draw(JACK_SPACE)
                .draw(TWO_SPACE);

        assertThat(result).isInstanceOf(Hit.class);
    }

    @DisplayName("Card 두장의 합이 21이면 블랙잭 상태로 변경된다.")
    @Test
    void 레디_카드_두장_블랙잭() {
        State ready = new Ready();

        State result = ready.draw(JACK_SPACE)
                .draw(ACE_SPACE);

        assertThat(result).isInstanceOf(Blackjack.class);
    }

    @DisplayName("레디 상태는 stay할 수 없다. 즉 실행할 경우 예외를 던진다.")
    @Test
    void 레디_스테이_불가() {
        State ready = new Ready();

        assertThatThrownBy(() -> ready.stay())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("레디 상태의 끝난 상태 유무는 false이다.")
    @Test
    void 레디_끝난상태() {
        State ready = new Ready();

        boolean result = ready.isFinished();

        assertThat(result).isFalse();
    }
}