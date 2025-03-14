package model.bet;

import static org.assertj.core.api.Assertions.assertThat;

import model.result.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BettingOddsTest {

    @DisplayName("GameResult에 따른 베팅 배당률을 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "WIN, WIN_ODDS",
            "LOSE, LOSE_ODDS",
            "DRAW, DRAW_ODDS",
            "BLACKJACK_WIN, BLACKJACK_WIN_ODDS"
    })
    void fromTest(final GameResult gameResult, final BettingOdds expected) {
        assertThat(BettingOdds.from(gameResult)).isEqualTo(expected);
    }
}
