package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BankTest extends AbstractTestFixture{

    @Test
    @DisplayName("배팅을 할 수 있다")
    void test_bet() {
        var bank = new Bank();
        var user = new User("ㅎㅇ");
        bank.bet(user, Money.of(123));

        assertThat(bank.withdrawDepositOf(user)).isEqualTo(Money.of(123));
    }

    @Test
    @DisplayName("100원이상 배팅해야 한다")
    void test_bet_under_100_throws() {
        var bank = new Bank();
        var user = new User("gd");

        assertThatThrownBy(() -> bank.bet(user, Money.of(99)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("인출할 수 있다")
    void test_draw_deposit() {
        var bank = new Bank();
        var user = new User("gd");
        bank.bet(user, Money.of(500));

        assertThat(bank.withdrawDepositOf(user)).isEqualTo(Money.of(500));
    }

    @DisplayName("결과에 따라 배당을 준다")
    @ParameterizedTest(name = "{0}이면 500원이 {1}원이 된다")
    @CsvSource({"BLACKJACK,1250", "WIN,1000", "LOSE,0", "DRAW,500"})
    void test_pay_by_result(Result result, int expectedValue) {
        var bank = new Bank();
        var user = new User("gd");
        bank.bet(user, Money.of(500));
        bank.evaluate(user, result);

        assertThat(bank.withdrawDepositOf(user)).isEqualTo(Money.of(expectedValue));
    }

    @DisplayName("뱅크 수익을 알 수 있다")
    @ParameterizedTest(name = "{0}이면 {1}원이 수익이다")
    @CsvSource({"BLACKJACK,-750", "WIN,-500", "LOSE,500", "DRAW,0"})
    void test_bank_profit(Result result, int expectedValue) {
        var bank = new Bank();
        var user = new User("gd");
        bank.bet(user, Money.of(500));
        bank.evaluate(user, result);

        assertThat(bank.getProfit()).isEqualTo(createMoney(expectedValue));
    }
}
