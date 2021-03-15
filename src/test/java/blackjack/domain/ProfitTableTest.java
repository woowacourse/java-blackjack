package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTableTest {

    private static Stream<Arguments> resultTypeAndMoneyProvider() {
        return Stream.of(
                Arguments.of(ResultType.BLACKJACK_WIN, Money.of(10000), 15000.d),
                Arguments.of(ResultType.WIN, Money.of(10000), 10000.d),
                Arguments.of(ResultType.DRAW, Money.of(10000), 0.d),
                Arguments.of(ResultType.LOSE, Money.of(10000), -10000.d)
        );
    }

    @DisplayName("매치 결과에 따라 수익을 계산한다.")
    @ParameterizedTest
    @MethodSource("resultTypeAndMoneyProvider")
    void translateBettingMoneyTest(ResultType resultType, Money money, double profit) {
        assertThat(ProfitTable.translateBettingMoney(resultType, money)).isEqualTo(profit);
    }
}