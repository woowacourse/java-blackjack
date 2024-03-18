package blackjack.domain.handrank;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.money.BetAmount;
import blackjack.domain.money.Profit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SingleMatchResultTest {

    @DisplayName("승패 결과에 따라, 플레이어의 이익을 반환할 수 있다.")
    @ParameterizedTest
    @CsvSource({
            "1000, PLAYER_BLACKJACK, 1500",
            "1000, PLAYER_WIN, 1000",
            "1000, DRAW, 0",
            "1000, DEALER_WIN, -1000"
    })
    void calculatePlayerProfitTest(int amount, SingleMatchResult result, int expected) {
        BetAmount betAmount = new BetAmount(amount);

        Profit actual = result.calculatePlayerProfit(betAmount);

        assertThat(actual.toInt()).isEqualTo(expected);
    }
}
