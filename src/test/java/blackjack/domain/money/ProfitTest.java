package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ProfitTest {

    @DisplayName("주어진 배팅 금액 만큼 얻는 이익을 계산할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 1000})
    void createTest_winMoney(int amount) {
        BetAmount betAmount = new BetAmount(amount);

        Profit profit = Profit.win(betAmount);

        assertThat(profit.toInt()).isEqualTo(amount);
    }

    @DisplayName("주어진 배팅 금액에 특정 배수만큼 얻는 이익을 계산할 수 있다.")
    @ParameterizedTest
    @CsvSource({"1000, 2, 2000", "20, 1.5, 30", "1111, 1.5, 1666"})
    void createTest_winMoneyWithMultiplier(int amount, double multiplier, int expected) {
        BetAmount betAmount = new BetAmount(amount);

        Profit profit = Profit.win(betAmount, multiplier);

        assertThat(profit.toInt()).isEqualTo(expected);
    }

    @DisplayName("주어진 배팅 금액 만큼 잃는 이익을 계산할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 1000})
    void createTest_loseMoney(int amount) {
        BetAmount betAmount = new BetAmount(amount);

        Profit profit = Profit.lose(betAmount);

        assertThat(profit.toInt()).isEqualTo(-1 * amount);
    }

    @DisplayName("두 이익을 더하여 계산할 수 있다.")
    @ParameterizedTest
    @CsvSource({"20, -10", "10, -20", "-20, -10", "20, 10"})
    void addTest(int value, int addedValue) {
        Profit profit = new Profit(value);
        Profit addedProfit = new Profit(addedValue);

        Profit actual = profit.add(addedProfit);

        assertThat(actual.toInt()).isEqualTo(value + addedValue);
    }

    @DisplayName("얻은 만큼 잃은 이익을, 잃은 만큼 얻은 이익을 구할 수 있다.")
    @ParameterizedTest
    @CsvSource({"20, -20", "-10, 10", "-1, 1", "0, 0"})
    void reverseTest(int value, int expected) {
        Profit profit = new Profit(value);

        Profit actual = profit.reverse();

        assertThat(actual.toInt()).isEqualTo(expected);
    }
}
