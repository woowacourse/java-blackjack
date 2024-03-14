package domain.betting;

import domain.participant.Player;
import org.assertj.core.api.Assertions;
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
        Assertions.assertThat(accounts.getAccounts()).hasSize(2);
    }
}
