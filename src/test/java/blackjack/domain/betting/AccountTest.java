package blackjack.domain.betting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
    @Test
    void 입금할_수_있다() {
        Account account = new Account();
        Cash cash = new Cash(1000);

        Account accountA = account.deposit(cash);

        assertThat(accountA.getBalance()).isEqualTo(cash);
    }

    @Test
    void 입금하면_기존_금액에_더해진다() {
        Account account = new Account();
        Cash cashA = new Cash(1000);
        Cash cashB = new Cash(2000);

        Account accountA = account.deposit(cashA);
        Account accountB = accountA.deposit(cashB);

        assertThat(accountB.getBalance()).isEqualTo(new Cash(3000));
    }

    @Test
    void 출금할_수_있다() {
        Account account = new Account();
        Cash cash = new Cash(1000);

        Account accountA = account.withdraw(cash);

        assertThat(accountA.getBalance()).isEqualTo(new Cash(-1000));
    }

    @Test
    void 출금하면_기존_금액에_빼진다() {
        Account account = new Account();
        Cash cashA = new Cash(1000);
        Cash cashB = new Cash(2000);

        Account accountA = account.withdraw(cashA);
        Account accountB = accountA.withdraw(cashB);

        assertThat(accountB.getBalance()).isEqualTo(new Cash(-3000));
    }

    @Test
    void 입금과_출금을_반복할_수_있다() {
        Cash cashA = new Cash(1000);
        Cash cashB = new Cash(2000);
        Cash cashC = new Cash(3000);
        Cash cashD = new Cash(4000);
        Account account = new Account();

        Account accountA = account.withdraw(cashA);
        Account accountB = accountA.deposit(cashB);
        Account accountC = accountB.withdraw(cashC);
        Account accountD = accountC.deposit(cashD);

        assertThat(accountD.getBalance()).isEqualTo(new Cash(2000));
    }
}
