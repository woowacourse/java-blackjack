package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 결과")
class GameResultTest {

    @DisplayName("게임 결과가 블랙잭인 경우의 수익률을 계산한다.")
    @Test
    void calculateBlackjackProfit() {
        // given
        BigDecimal betting = BigDecimal.valueOf(1000);

        // when
        BigDecimal profit = GameResult.BLACKJACK.calculateProfit(betting);

        // then
        assertThat(profit).isEqualTo(BigDecimal.valueOf(1500.0));
    }

    @DisplayName("게임 결과가 우승인 경우의 수익률을 계산한다.")
    @Test
    void calculateWinProfit() {
        // given
        BigDecimal betting = BigDecimal.valueOf(1000);

        // when
        BigDecimal profit = GameResult.WIN.calculateProfit(betting);

        // then
        assertThat(profit).isEqualTo(BigDecimal.valueOf(1000));
    }

    @DisplayName("게임 결과가 무승부인 경우의 수익률을 계산한다.")
    @Test
    void calculateDrawProfit() {
        // given
        BigDecimal betting = BigDecimal.valueOf(1000);

        // when
        BigDecimal profit = GameResult.DRAW.calculateProfit(betting);

        // then
        assertThat(profit).isEqualTo(BigDecimal.valueOf(0));
    }

    @DisplayName("게임 결과가 패배인 경우의 수익률을 계산한다.")
    @Test
    void calculateLoseProfit() {
        // given
        BigDecimal betting = BigDecimal.valueOf(1000);

        // when
        BigDecimal profit = GameResult.LOSE.calculateProfit(betting);

        // then
        assertThat(profit).isEqualTo(BigDecimal.valueOf(-1000));
    }
}
