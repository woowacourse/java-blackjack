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
        int betting = 1000;

        assertAll(
                () -> assertThat(GameResult.WIN.getProfit(betting, true)).isEqualTo(1500),
                () -> assertThat(GameResult.WIN.getProfit(betting, false)).isEqualTo(1000),
                () -> assertThat(GameResult.DRAW.getProfit(betting, true)).isEqualTo(0),
                () -> assertThat(GameResult.LOSE.getProfit(betting, true)).isEqualTo(-1000)
        );
    }
}