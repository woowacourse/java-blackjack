package domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutcomeTest {

    @DisplayName("승리하면 베팅 금액만큼 수익이다")
    @Test
    void 승리하면_베팅_금액만큼_수익이다() {
        assertThat(Outcome.WIN.calculateProfit(10_000)).isEqualTo(10_000);
    }

    @DisplayName("블랙잭 승리하면 1.5배 수익이다")
    @Test
    void 블랙잭_승리하면_1점5배_수익이다() {
        assertThat(Outcome.BLACKJACK_WIN.calculateProfit(10_000)).isEqualTo(15_000);
    }

    @DisplayName("패배하면 베팅 금액만큼 손해다")
    @Test
    void 패배하면_베팅_금액만큼_손해다() {
        assertThat(Outcome.LOSE.calculateProfit(10_000)).isEqualTo(-10_000);
    }

    @DisplayName("무승부면 수익이 0이다")
    @Test
    void 무승부면_수익이_0이다() {
        assertThat(Outcome.TIE.calculateProfit(10_000)).isEqualTo(0);
    }

    @DisplayName("홀수 금액의 블랙잭 승리는 소수점을 버린다")
    @Test
    void 홀수_금액의_블랙잭_승리는_소수점을_버린다() {
        assertThat(Outcome.BLACKJACK_WIN.calculateProfit(999)).isEqualTo(1_498);
    }
}
