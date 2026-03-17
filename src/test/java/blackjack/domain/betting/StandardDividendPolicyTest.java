package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.GameResult;
import blackjack.domain.participant.BettingAmount;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StandardDividendPolicyTest {

    @ParameterizedTest
    @CsvSource(value = {
            "BLACKJACK_WIN, 15_000",
            "WIN, 10_000",
            "LOSE, -10_000",
            "PUSH, 0",
    })
    void 배당금_정책에_맞는_수익금을_반환한다(GameResult gameResult, long expectedProfit) {
        // given
        StandardDividendPolicy dividendPolicy = new StandardDividendPolicy();
        BettingAmount bettingAmount = BettingAmount.initial();
        BettingAmount registeredBettingAmount = bettingAmount.register(10000);
        // when
        long profit = dividendPolicy.calculate(registeredBettingAmount, gameResult);
        // then
        assertThat(profit).isEqualTo(expectedProfit);
    }
}
