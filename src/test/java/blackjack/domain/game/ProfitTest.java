package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProfitTest {

    @ParameterizedTest
    @ValueSource(ints = {100, 200, 10})
    @DisplayName("성공: 값이 반대인 Profit 생성")
    void toNegative(int amount) {
        BettingMoney bettingMoney = new BettingMoney(amount);

        Profit profit = Profit.of(bettingMoney, PlayerState.WIN);
        Profit negativeProfit = profit.toNegative();

        assertThat(negativeProfit.getAmount()).isEqualTo(-amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 200, 10})
    @DisplayName("성공: 값이 1.5배인 BlackjackMoney 생성")
    void applyBlackjackMultiple(int amount) {
        BettingMoney money = new BettingMoney(amount);
        Profit profit = Profit.of(money, PlayerState.BLACKJACK);

        assertThat(profit.getAmount()).isEqualTo((int) (amount * 1.5));
    }

    @Test
    @DisplayName("성공: 합 연산")
    void add() {
        BettingMoney money1 = new BettingMoney(100);
        BettingMoney money2 = new BettingMoney(500);

        Profit profit1 = Profit.of(money1, PlayerState.WIN);
        Profit profit2 = Profit.of(money2, PlayerState.WIN);

        Profit totalProfit = profit1.sum(profit2);

        assertThat(totalProfit.getAmount()).isEqualTo(600);
    }
}