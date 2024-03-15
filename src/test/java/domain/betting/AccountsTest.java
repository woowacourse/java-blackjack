package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountsTest {

    @Test
    @DisplayName("플레이어 수에 맞게 계좌를 생성한다")
    void initialize() {
        Player player1 = Player.withName("user1");
        Player player2 = Player.withName("user2");
        Accounts accounts = Accounts.withNoAccount();
        accounts.add(player1, Money.valueOf(0));
        accounts.add(player2, Money.valueOf(0));

        assertThat(accounts.getAccounts()).hasSize(2);
    }

    @Test
    @DisplayName("특정 플레이어의 계좌를 탐색할 수 있다")
    void name() {
        Player player1 = Player.withName("user1");
        Player player2 = Player.withName("user2");
        Accounts accounts = Accounts.withNoAccount();
        accounts.add(player1, Money.valueOf(0));
        accounts.add(player2, Money.valueOf(0));

        assertThat(accounts.findByPlayer(player1)).isEqualTo(accounts.getAccounts().get(0));
    }
}
