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
        Account account = new Account();
        Balance balanceA = new Balance(1000);
        Balance balanceB = new Balance(2000);

        account.deposit(balanceA);

        assertThat(account.deposit(balanceB)).isEqualTo(new Balance(3000));
    }

    @Test
    void 출금할_수_있다() {
        Account account = new Account();
        Balance balance = new Balance(1000);

        assertThat(account.withdraw(balance)).isEqualTo(new Balance(-1000));
    }

    @Test
    void 출금하면_기존_금액에_빼진다() {
        Account account = new Account();
        Balance balanceA = new Balance(1000);
        Balance balanceB = new Balance(2000);

        account.withdraw(balanceA);

        assertThat(account.withdraw(balanceB)).isEqualTo(new Balance(-3000));
    }

    @Test
    void 입금과_출금을_반복할_수_있다() {
        Balance balanceA = new Balance(1000);
        Balance balanceB = new Balance(2000);
        Balance balanceC = new Balance(3000);
        Balance balanceD = new Balance(4000);
        Account account = new Account();

        account.withdraw(balanceA);
        account.deposit(balanceB);
        account.withdraw(balanceC);

        assertThat(account.deposit(balanceD)).isEqualTo(new Balance(2000));
    }
}
