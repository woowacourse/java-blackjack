package blackjack.model.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.blackjackgame.PlayerResultStatus;
import blackjack.model.blackjackgame.Profit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BettingTest {

    @DisplayName("돈의 금액이 최소 금액보다 작으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-200000, -2, -1})
    void validateMoney(int given) {
        assertThatThrownBy(() -> new Betting(given))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어 결과를 받아 이익을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACKJACK,15", "WIN,10", "LOSE,-10", "PUSH,0"})
    void getProfit(PlayerResultStatus given, int expected) {
        Betting betting = new Betting(10);
        Profit profit = betting.getProfit(given);

        assertThat(profit).isEqualTo(new Profit(expected));
    }
}
