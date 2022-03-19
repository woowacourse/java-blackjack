package blackjack.domain.state;

import static blackjack.domain.CardFixtures.JACK_SPACE;
import static blackjack.domain.CardsFixtures.FIVE_HIT;
import static blackjack.domain.CardsFixtures.TWELVE_HIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {

    @DisplayName("히트 상태에서 draw 힌 뒤 카드의 합이 21 이하이면 hit 상태이다.")
    @Test
    void 히트_에서_히트() {
        State hit = new Hit(FIVE_HIT);

        State result = hit.draw(JACK_SPACE);

        assertThat(result).isInstanceOf(Hit.class);
    }

    @DisplayName("히트 상태에서 draw 한 뒤 카드의 합이 21을 초과하면 bust 상태이다.")
    @Test
    void 히트_에서_버스트() {
        State hit = new Hit(TWELVE_HIT);

        State result = hit.draw(JACK_SPACE);

        assertThat(result).isInstanceOf(Bust.class);
    }

    @DisplayName("히트 상태에서 stay를 실행할 경우 스테이 상태가 된다.")
    @Test
    void 히트_에서_스테이() {
        State hit = new Hit(TWELVE_HIT);

        assertThat(hit.stay()).isInstanceOf(Stay.class);
    }

    @DisplayName("히트 상태는 실행 가능 상태이므로 실행 가능 유무는 true이다.")
    @Test
    void 히트_준비상태() {
        State hit = new Hit(TWELVE_HIT);

        boolean result = hit.isRunning();

        assertThat(result).isTrue();
    }

    @DisplayName("히트 상태에서 끝난 상태 유무는 false이다.")
    @Test
    void 히트_끝난상태() {
        State hit = new Hit(TWELVE_HIT);

        boolean result = hit.isFinished();

        assertThat(result).isFalse();
    }

    @DisplayName("히트 상태에서 earning rate를 구할 수 없다. 즉 예외를 던진다.")
    @Test
    void 히트_수익율_불가() {
        assertThatThrownBy(() -> new Hit(TWELVE_HIT).earningRate(new Ready()))
                .isInstanceOf(IllegalStateException.class);
    }
}