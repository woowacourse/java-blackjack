package blackjack.domain.betting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
    @Test
    void 입금할_수_있다() {
        Account account = new Account();
        Balance balance = new Balance(1000);

        assertThat(account.deposit(balance)).isEqualTo(balance);
    }

    @Test
    void 입금하면_기존_금액에_더해진다() {
        Balance balanceA = new Balance(1000);
        Balance balanceB = new Balance(2000);

        Account account = new Account();
        account.deposit(balanceA);

        assertThat(account.deposit(balanceB)).isEqualTo(new Balance(3000));
    }
}
