package blackjack.domain.state;

import static blackjack.domain.CardFixtures.TWO_SPACE;
import static blackjack.domain.CardsFixtures.BLACKJACK;
import static blackjack.domain.CardsFixtures.HIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @DisplayName("블랙잭 상태는 두장의 카드가 21점인 경우 정상 생성된다.")
    @Test
    void 블랙잭_생성() {
        assertDoesNotThrow(() -> new Blackjack(BLACKJACK));
    }

    @DisplayName("블랙잭 상태가 아닌 카드가 전달되면 예외를 던진다.")
    @Test
    void 블랙잭_생성_실패() {
        assertThatThrownBy(() -> new Blackjack(HIT))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("블랙잭 상태에서 추가적인 카드 뽑기는 불가능하다. 즉 실행할 경우 예외를 던진다.")
    @Test
    void 블랙잭_카드_뽑기_예외() {
        State blackjack = new Blackjack(BLACKJACK);

        assertThatThrownBy(() -> blackjack.draw(TWO_SPACE))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("블랙잭 상태에서 stay할 수 없다. 즉 실행할 경우 예외를 던진다.")
    @Test
    void 블랙잭_스테이_불가능() {
        State blackjack = new Blackjack(BLACKJACK);

        assertThatThrownBy(() -> blackjack.stay())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("블랙잭 상태는 실행 불가능 상태이므로 실행 가능 유무는 false이다.")
    @Test
    void 블랙잭_준비상태() {
        State blackjack = new Blackjack(BLACKJACK);

        boolean result = blackjack.isRunning();

        assertThat(result).isFalse();
    }

    @DisplayName("블랙잭 상태의 끝난 상태 유무는 true이다.")
    @Test
    void 블랙잭_끝난상태() {
        State blackjack = new Blackjack(BLACKJACK);

        boolean result = blackjack.isFinished();

        assertThat(result).isTrue();
    }
}