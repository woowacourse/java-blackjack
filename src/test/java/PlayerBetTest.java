import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.Player;
import blackjack.domain.PlayerBetting;
import blackjack.domain.ScoreCompareResult;
import org.junit.jupiter.api.Test;

public class PlayerBetTest {
    @Test
    void calculate_profit_when_player_win_except_blackjack() {
        Player player = new Player("player");

        PlayerBetting playerBetting = new PlayerBetting(player, 1000);

        assertThat(playerBetting.calculateProfit(ScoreCompareResult.PLAYER_WIN)).isEqualTo(1000);
    }
}
