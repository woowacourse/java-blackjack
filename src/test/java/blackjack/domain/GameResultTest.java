package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("게임 결과")
class GameResultTest {

    @DisplayName("게임 결과에 따라 받을 금액을 반환한다.")
    @Test
    void getProfit() {
        int batting = 1000;

        assertAll(
                () -> assertThat(GameResult.WIN.getProfit(batting, true)).isEqualTo(1500),
                () -> assertThat(GameResult.WIN.getProfit(batting, false)).isEqualTo(1000),
                () -> assertThat(GameResult.DRAW.getProfit(batting, true)).isEqualTo(0),
                () -> assertThat(GameResult.LOSE.getProfit(batting, true)).isEqualTo(-1000)
        );
    }
}