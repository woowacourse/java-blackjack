package blackjack.domain.result;

import blackjack.domain.player.BetMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {

    @ParameterizedTest
    @CsvSource(value = {"WIN:100:100", "LOSE:50:-50", "WIN_BLACKJACK:100:150", "LOSE_BLACKJACK:100:-150", "DRAW:100:0"},
            delimiter = ':')
    @DisplayName("플레이어 수익 계산 확인")
    void checkGuestProfit(Match match, double betMoney, int expected) {
        Profit profit = new Profit(match.getRatio(), new BetMoney(betMoney));

        assertThat(profit.getValue()).isEqualTo(expected);
    }
}