package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import blackjack.model.BettingMoney;
import blackjack.model.player.Player;
import blackjack.model.player.User;

class PlayersBettingTest {

    @Test
    void 플레이어가_배팅_머니를_입금할_수_있다() {
        Player player = new User("pobi");
        BettingMoney bettingMoney = new BettingMoney(1_000);
        PlayersBetting playersBetting = new PlayersBetting();

        playersBetting.depositBettingMoney(player, bettingMoney);

        assertThat(playersBetting.getPlayersBettingMoney())
                .containsExactlyInAnyOrderEntriesOf(Map.of(new User("pobi"), new BettingMoney(1_000)));
    }

}
