package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitInfoTest {

    @Test
    @DisplayName("수익금 정보를 추가할 수 있다.")
    void add() {
        ProfitInfo profitInfo = ProfitInfo.createEmpty();
        Player player = Player.withName("user1");
        Money profit = Money.valueOf(1000);

        profitInfo.add(player, profit);

        assertThat(profitInfo.findProfitBy(player)).isEqualTo(profit);
    }

    @Test
    @DisplayName("딜러의 수익금을 계산할 수 있다.")
    void calculateDealerProfit() {
        ProfitInfo profitInfo = ProfitInfo.createEmpty();
        Player player1 = Player.withName("user1");
        Player player2 = Player.withName("user2");
        Money profit1 = Money.valueOf(1000);
        Money profit2 = Money.valueOf(2000);

        profitInfo.add(player1, profit1);
        profitInfo.add(player2, profit2);

        assertThat(profitInfo.calculateDealerProfit().toInt()).isEqualTo(-3000);
    }
}
