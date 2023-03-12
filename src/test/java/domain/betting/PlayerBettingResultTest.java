package domain.betting;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBettingResultTest {

    @Test
    @DisplayName("배팅 결과가 WIN이면 최종 수익이 받은 배팅 금액으로 계산된다.")
    void calculateFinalProfit1() {
        Assertions.assertThat(PlayerBettingResult.WIN.calculateFinalProfit(1000))
                .isEqualTo(1000);
    }

    @Test
    @DisplayName("배팅 결과가 LOSE면 최종 수익이 받은 배팅 금액의 부호만 바꿔서 계산된다.")
    void calculateFinalProfit2() {
        Assertions.assertThat(PlayerBettingResult.LOSE.calculateFinalProfit(1000))
                .isEqualTo(-1000);
    }

    @Test
    @DisplayName("배팅 결과가 BLACKJACK이면 최종 수익이 받은 배팅 금액의 1.5배로 계산된다.")
    void calculateFinalProfit3() {
        Assertions.assertThat(PlayerBettingResult.BLACKJACK.calculateFinalProfit(1000))
                .isEqualTo(1000 * 1.5);
    }
}
