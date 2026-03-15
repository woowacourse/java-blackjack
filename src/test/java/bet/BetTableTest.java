package bet;

import domain.bet.BetTable;
import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.PlayerName;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BetTableTest {

    @Test
    @DisplayName("플레이어는 베팅 금액을 설정할 수 있다.")
    void recordAmount_setsBetAmount() {
        BetTable betTable = new BetTable();
        int amount = 10000;

        betTable.recordAmount("user1", amount);

        assertThat(betTable)
                .extracting("bettingTable", InstanceOfAssertFactories.MAP)
                .containsEntry("user1", amount);
    }

    @Test
    @DisplayName("플레이어이름으로 배팅금액을 조회할 수 있다.")
    void getAmountByName_returnsBetAmount() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        BetTable betTable = new BetTable();
        int amount = 10000;
        betTable.recordAmount(player.getName(), amount);

        int betAmount = betTable.findBetAmount(player);
        assertThat(betAmount).isEqualTo(amount);
    }
}
