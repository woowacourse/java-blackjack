package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
    @DisplayName("from 메소드에 int 값을 전달하면, Money 인스턴스를 생성해서 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000})
    void from(int input) {
        // given & when
        Money money = Money.from(input);

        // then
        assertThat(money).isNotNull();
    }

    @DisplayName("createAsNegative 메소드에 Money 를 전달하면, 전달된 Money 의 음수 값을 갖는 Money 인스턴스를 생성해서 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000})
    void createAsNegative_returnsNegativeMoney(int input) {
        // given
        Money money = Money.from(input);

        // when
        Money actual = Money.createAsNegative(money);
        Money expected = Money.from(input * -1);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("add 메소드는 현재 값에서 인자로 전달된 Money 의 값을 더한 새 Money 인스턴스를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2000,1000,3000", "3000,3000,6000", "0,1000,1000", "0,0,0"})
    void add_returnsAddedMoney(int original, int operand, int added) {
        // given
        Money originalMoney = Money.from(original);
        Money operandMoney = Money.from(operand);

        // when
        Money actual = originalMoney.add(operandMoney);
        Money expected = Money.from(added);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("subtract 메소드는 현재 값에서 인자로 전달된 Money 의 값을 뺀 새 Money 인스턴스를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2000,1000,1000", "3000,3000,0", "0,1000,-1000", "500,2000,-1500"})
    void subtract_returnsSubtractedMoney(int original, int operand, int subtracted) {
        // given
        Money originalMoney = Money.from(original);
        Money operandMoney = Money.from(operand);

        // when
        Money actual = originalMoney.subtract(operandMoney);
        Money expected = Money.from(subtracted);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("같은 value 를 가지고 있는 Money 끼리는 동등하다.")
    @ParameterizedTest
    @ValueSource(ints = {-1000, 0, 1000})
    void bothMoneyHavingSameValueShouldHaveEquality(int input) {
        // given & when
        Money money1 = Money.from(input);
        Money money2 = Money.from(input);

        // then
        assertThat(money1).isEqualTo(money2);
    }

    @DisplayName("createBlackjackProfit 을 호출하면 현재 value 에 1.5를 곱한 값을 갖는 새 Money 인스턴스를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,0", "1000,1500", "3000,4500"})
    void createProfitOfBlackjack_returnsNewMoneyMultipliedByOnePointFive(int betted, int profit) {
        // given
        Money money = Money.from(betted);

        // when
        Money actual = Money.createBlackjackProfit(money);
        Money expected = Money.from(profit);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("createBlackjackProfit 의 계산결과가 소수라면 반올림한다.")
    @ParameterizedTest
    @CsvSource(value = {"1555,2333", "2333,3500"})
    void createProfitOfBlackjack_returnsRoundedMoney(int betted, int profit) {
        // given
        Money money = Money.from(betted);

        // when
        Money actual = Money.createBlackjackProfit(money);
        Money expected = Money.from(profit);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("isNegative 메소드는 value 가 음수일 때 true 를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {-10000, -1000, -1})
    void isNegative_returnsTrueOnNegativeValue(int input) {
        // given
        Money money = Money.from(input);

        // when
        boolean actual = money.isNegative();

        // then
        assertThat(actual).isTrue();
    }
}
