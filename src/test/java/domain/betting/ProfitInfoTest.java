package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitInfoTest {

    @Test
    @DisplayName("수익금 정보를 추가할 수 있다.")
    void add() {
        ProfitInfo profitInfo = ProfitInfo.withNoEntry();
        Player player = Player.withName("user1");
        Money profit = Money.valueOf(1000);

        profitInfo.add(player, profit);

        assertThat(profitInfo.findProfitBy(player)).isEqualTo(profit);
    }
}
