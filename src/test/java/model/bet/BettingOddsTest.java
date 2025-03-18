package model.bet;

import static org.assertj.core.api.Assertions.assertThat;

import model.result.BettingOdds;
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

    @DisplayName("베팅 금액에 배당률을 곱하여 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "WIN_ODDS, 1000, 1000",
            "LOSE_ODDS, 5000, -5000",
            "DRAW_ODDS, 30000, 0",
            "BLACKJACK_WIN_ODDS, 10000, 15000"
    })
    void multipleTest(final BettingOdds bettingOdds, int bet, double expected) {
        assertThat(bettingOdds.multiple(bet)).isEqualTo(expected);
    }
}
