package blackjack.domain.participant.attribute;

import blackjack.domain.result.ResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @DisplayName("게임 결과에 따른 수익 계산 확인")
    @ParameterizedTest
    @CsvSource(value = {"BLACKJACK, 1500", "WIN, 1000", "DRAW, 0", "LOSE, -1000"})
    void name(ResultType type, int expectedProfit) {
        Money money = new Money(1000);
        double actualProfit = money.computeProfit(type);
        assertThat(actualProfit).isEqualTo(expectedProfit);
    }
}
