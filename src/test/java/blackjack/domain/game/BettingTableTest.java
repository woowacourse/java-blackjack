package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTableTest {

    @Test
    @DisplayName("베팅 정보를 추가한다")
    void createBettingTable() {
        BettingTable bettingTable = new BettingTable();

        bettingTable.put(new Player("박스터"), new BettingMoney(10000));

        assertThat(bettingTable.getBets()).hasSize(1);
    }

}
