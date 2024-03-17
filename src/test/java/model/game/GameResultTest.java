package model.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import model.betting.Bet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {

    @DisplayName("게임 결과에 따라 수익금을 반환한다")
    @ParameterizedTest
    @MethodSource("provideGameResultWithBetAmountAndProfit")
    void testCalculateProfit(GameResult gameResult, int amount, int profit) {
        Bet bet = new Bet(amount);
        assertThat(gameResult.calculateProfit(bet)).isEqualTo(profit);
    }

    public static Stream<Arguments> provideGameResultWithBetAmountAndProfit() {
        return Stream.of(
            Arguments.of(GameResult.BLACKJACK_WIN, 10000, 15000),
            Arguments.of(GameResult.WIN, 10000, 10000),
            Arguments.of(GameResult.LOOSE, 10000, -10000),
            Arguments.of(GameResult.PUSH, 10000, 10000)
        );
    }
}
