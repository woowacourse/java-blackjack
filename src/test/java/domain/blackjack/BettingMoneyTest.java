package domain.blackjack;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BettingMoneyTest {

    static Stream<Arguments> calculateEarningMoneyParameters() {
        return Stream.of(
                Arguments.of(GameResult.WIN, new EarningMoney(1000)),
                Arguments.of(GameResult.LOSE, new EarningMoney(-1000)),
                Arguments.of(GameResult.WIN_BLACK_JACK, new EarningMoney(1500)),
                Arguments.of(GameResult.TIE, new EarningMoney(0))
        );
    }

    @ParameterizedTest
    @MethodSource("calculateEarningMoneyParameters")
    @DisplayName("상금이 잘 계산되는지 검증")
    void calculateEarningMoney(GameResult gameResult, EarningMoney expected) {
        Player player = Player.from("robin", HoldingCards.of(), 1000);
        EarningMoney earningMoney = player.calculateEarningMoney(gameResult);
        Assertions.assertThat(earningMoney)
                .isEqualTo(expected);
    }
}
