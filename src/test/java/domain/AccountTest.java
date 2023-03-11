package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    @DisplayName("버스트가 되면 잔액이 0원이 된다.")
    void account_will_be_zero_if_bust() {
        // given
        int givenAccount = 10000;
        Account account = new Account(givenAccount);

        // when
        account.bust(givenAccount);

        // then
        assertThat(account.getAccount()).isEqualTo(givenAccount * -1);
    }

    @Test
    @DisplayName("우승하면 금액이 증가한다.")
    void account_increase() {
        // given
        int givenAccount = 10000;
        int givenWinningMoney = 5000;
        Account account = new Account(givenAccount);

        // when
        account.addAccount(givenWinningMoney);

        // then
        assertThat(account.getAccount()).isEqualTo(givenAccount + givenWinningMoney);
    }

    @Test
    @DisplayName("패배하면 금액이 감소한다.")
    void account_decrease() {
        // given
        int givenAccount = 10000;
        int givenLosingMoney = 5000;
        Account account = new Account(givenAccount);

        // when
        account.subAccount(givenLosingMoney);

        // then
        assertThat(account.getAccount()).isEqualTo(givenAccount - givenLosingMoney);
    }
}
