package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultStatusTest {

    @Test
    @DisplayName("승리일 경우 상금 반환")
    void calculate_win_profit() {
        // given
        BigDecimal input = new BigDecimal("10000");

        // when
        BigDecimal result = GameResultStatus.WIN.calculateProfit(input);

        // then
        assertThat(result).isEqualByComparingTo(new BigDecimal("10000"));
    }

    @Test
    @DisplayName("블랙잭 승리일 경우 상금 반환")
    void calculate_blackjack_win_profit() {
        // given
        BigDecimal input = new BigDecimal("10000");

        // when
        BigDecimal result = GameResultStatus.BLACKJACK_WIN.calculateProfit(input);

        // then
        assertThat(result).isEqualByComparingTo(new BigDecimal("15000"));
    }

    @Test
    @DisplayName("패배일 경우 상금 반환")
    void calculate_lose_profit() {
        // given
        BigDecimal input = new BigDecimal("10000");

        // when
        BigDecimal result = GameResultStatus.LOSE.calculateProfit(input);

        // then
        assertThat(result).isEqualByComparingTo(new BigDecimal("-10000"));
    }

    @Test
    @DisplayName("무승부일 경우 상금 반환")
    void calculate_draw_profit() {
        // given
        BigDecimal input = new BigDecimal("10000");

        // when
        BigDecimal result = GameResultStatus.DRAW.calculateProfit(input);

        // then
        assertThat(result).isEqualByComparingTo(BigDecimal.ZERO);
    }
}
