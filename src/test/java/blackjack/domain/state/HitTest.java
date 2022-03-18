package blackjack.domain.state;

import static blackjack.domain.CardFixtures.JACK_SPACE;
import static blackjack.domain.CardsFixtures.FIVE_HIT;
import static blackjack.domain.CardsFixtures.TWELVE_HIT;
import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("히트 상태에서 준비 상태 유무는 false이다.")
    @Test
    void 히트_준비상태() {
        State hit = new Hit(TWELVE_HIT);

        boolean result = hit.isReady();

        assertThat(result).isFalse();
    }

    @DisplayName("히트 상태에서 끝난 상태 유무는 false이다.")
    @Test
    void 히트_끝난상태() {
        State hit = new Hit(TWELVE_HIT);

        boolean result = hit.isFinished();

        assertThat(result).isFalse();
    }
}