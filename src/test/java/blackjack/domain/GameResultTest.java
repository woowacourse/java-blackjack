package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameResultTest {

    @ParameterizedTest
    @DisplayName("결과와 배팅 금액으로 수익금을 계산")
    @MethodSource("gameResultsAndRevenues")
    void calculateRightRevenue(int money, GameResult gameResult, int expectedRevenue) {
        Player player = new Player("dog", money);
        assertThat(gameResult.calculateRevenue(player.getBettingMoney())).isEqualTo(expectedRevenue);
    }

    private static Stream<Arguments> gameResultsAndRevenues() {
        return Stream.of(
                Arguments.of(10000, GameResult.WIN_BLACKJACK, 15000),
                Arguments.of(16000, GameResult.WIN, 16000),
                Arguments.of(20000, GameResult.LOSE, -20000),
                Arguments.of(30000, GameResult.DRAW, 0));
    }

}
