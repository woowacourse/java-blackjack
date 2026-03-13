package domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.BlackjackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {

    @Test
    @DisplayName("최소 배팅 금액보다 작으면 예외가 발생한다.")
    void 최소_배팅_금액_검증() {
        // given
        int amount = 500;

        // when & then
        assertThatThrownBy(() -> new Betting(amount))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("배팅 금액은 1000원 단위여야 한다.")
    void 배팅_단위_검증() {
        // given
        int amount = 1500;

        // when & then
        assertThatThrownBy(() -> new Betting(amount))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("정상 배팅 금액이면 객체가 생성된다.")
    void 정상_배팅() {
        // given
        int amount = 3000;

        // when
        Betting betting = new Betting(amount);

        // then
        assertThat(betting.amount()).isEqualTo(3000);
    }
}