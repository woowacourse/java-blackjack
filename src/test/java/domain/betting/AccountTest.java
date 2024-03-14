package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    @DisplayName("계좌에서 출금할 수 있다.")
    void withdraw() {
        Participant Participant = new Player(new Name("name"));
        Account account = Account.from(Participant);

        account.withdraw(Money.betValueOf(1000));

        assertThat(account.balance().toInt()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("계좌에 입금할 수 있다.")
    void deposit() {
        Participant Participant = new Player(new Name("name"));
        Account account = Account.from(Participant);

        account.deposit(Money.betValueOf(1000));

        assertThat(account.balance().toInt()).isEqualTo(1000);
    }
}
