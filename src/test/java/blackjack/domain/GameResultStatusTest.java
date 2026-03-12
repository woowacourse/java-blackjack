package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultStatusTest {

    @Test
    @DisplayName("승리일 경우 상금 반환")
    void calculate_win_profit() {
        // given
        int input = 10000;

        // when
        int result = GameResultStatus.WIN.calculateProfit(input);

        //then
        assertThat(10000).isEqualTo(result);
    }

    @Test
    @DisplayName("블랙잭 승리일 경우 상금 반환")
    void calculate_blackjack_win_profit() {
        // given
        int input = 10000;

        // when
        int result = GameResultStatus.BLACKJACK_WIN.calculateProfit(input);

        //then
        assertThat(15000).isEqualTo(result);
    }

    @Test
    @DisplayName("패배일 경우 상금 반환")
    void calculate_lose_profit() {
        // given
        int input = 10000;

        // when
        int result = GameResultStatus.LOSE.calculateProfit(input);

        //then
        assertThat(-10000).isEqualTo(result);
    }

    @Test
    @DisplayName("무승부일 경우 상금 반환")
    void calculate_draw_profit() {
        // given
        int input = 10000;

        // when
        int result = GameResultStatus.DRAW.calculateProfit(input);

        // then
        assertThat(0).isEqualTo(result);
    }
}
