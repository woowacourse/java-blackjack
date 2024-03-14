package domain.betting;

import domain.participant.Players;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountsTest {

    @Test
    @DisplayName("플레이어 수에 맞게 계좌를 생성한다")
    void initialize() {
        Players players = Players.withNames(List.of("a", "b", "c"));
        Accounts accounts = Accounts.from(players);
        Assertions.assertThat(accounts.getAccounts()).hasSize(3);
    }
}
