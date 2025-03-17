package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participants.BettingAmount;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BettingResultAmountTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("게임 결과에 따라 배팅 결과를 계산하여 저장한다.")
    void calculateBettingResultAmount(BettingAmount bettingAmount, GameResult gameResult, int expected) {
        // when
        BettingResultAmount bettingResultAmount = new BettingResultAmount(bettingAmount, gameResult);
        // then
        int resultMoney = bettingResultAmount.getMoney();
        assertThat(resultMoney).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateBettingResultAmount() {
        return Stream.of(
                Arguments.of(new BettingAmount(10000), GameResult.WIN, 10000),
                Arguments.of(new BettingAmount(10000), GameResult.LOSE, -10000),
                Arguments.of(new BettingAmount(10000), GameResult.DRAW, 0),
                Arguments.of(new BettingAmount(20000), GameResult.WIN, 20000),
                Arguments.of(new BettingAmount(20000), GameResult.LOSE, -20000),
                Arguments.of(new BettingAmount(20000), GameResult.DRAW, 0)
        );
    }
}
