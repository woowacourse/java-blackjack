package domain.result;

import domain.user.Money;
import domain.user.Profit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {
    @ParameterizedTest
    @MethodSource({"getCasesForTesingCalculateProfit"})
    @DisplayName("#calculateProfit() : should return profit")
    void calculateProfit(Result result, double rate) {
        Money money = Money.of(1000);

        Profit profit = result.calculateProfit(money);
        assertThat(profit).isEqualTo(new Profit(money.multiply(rate)));

    }

    private static Stream<Arguments> getCasesForTesingCalculateProfit() {
        return Stream.of(
                Arguments.of(Result.PLAYER_WIN_WITHOUT_BLACKJACk, 1),
                Arguments.of(Result.PLAYER_WIN_WITH_BLACKJACK, 1.5),
                Arguments.of(Result.DRAW, 0),
                Arguments.of(Result.DEALER_WIN, 1)
        );
    }
}