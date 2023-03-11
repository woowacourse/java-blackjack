package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
