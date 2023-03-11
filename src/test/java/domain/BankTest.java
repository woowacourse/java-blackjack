package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BankTest {

    @Test
    @DisplayName("배팅을 할 수 있다")
    void test_bet() {
        var bank = new Bank();
        var user = new User("ㅎㅇ");

        bank.bet(user, Money.of(123));

        assertThat(bank.pay(user, Result.WIN)).isEqualTo(Money.of(246));
    }

    @Test
    @DisplayName("100원이상 배팅해야 한다")
    void test_bet_under_100_throws() {
        var bank = new Bank();
        var user = new User("gd");

        assertThatThrownBy(() -> bank.bet(user, Money.of(99)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("결과에 따라 배당을 주어 반환한다")
    @ParameterizedTest(name = "{0}이면 총 {1}원이 반환된다")
    @CsvSource({"BLACKJACK,1250", "WIN,1000", "LOSE,0", "DRAW,500"})
    void test_pay_by_result(Result result, int expectedValue) {
        var bank = new Bank();
        var user = new User("gd");

        bank.bet(user, Money.of(500));

        assertThat(bank.pay(user, result)).isEqualTo(Money.of(expectedValue));
    }
}
