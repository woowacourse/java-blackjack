package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;

import org.junit.jupiter.api.Test;

import blackjack.model.BettingMoney;
import blackjack.model.player.Player;
import blackjack.model.player.User;

class PlayersBettingTest {

    @Test
    void 플레이어가_배팅_머니를_입금할_수_있다() {
        Player player = new User("pobi", 1_000);
        BettingMoney bettingMoney = new BettingMoney(1_000);
        PlayersBetting playersBetting = new PlayersBetting();

        playersBetting.depositBettingMoney(player, bettingMoney);

        assertThat(playersBetting.getPlayersBettingMoney())
                .containsExactlyInAnyOrderEntriesOf(Map.of(new User("pobi", 1_000), new BettingMoney(1_000)));
    }

    @Test
    void 플레이어의_베팅_머니를_출금한다() {
        PlayersBetting playersBetting = new PlayersBetting();
        Player pobi = new User("pobi", 1_000);
        playersBetting.depositBettingMoney(pobi, new BettingMoney(1_000));

        assertThat(playersBetting.withdrawMoney(pobi)).isEqualTo(new BettingMoney(1_000));
    }

    @Test
    void 존재하지_않는_플레이어의_베팅_머니를_출금할_수_없다() {
        PlayersBetting playersBetting = new PlayersBetting();
        Player pobi = new User("pobi", 1_000);
        playersBetting.depositBettingMoney(pobi, new BettingMoney(1_000));

        assertThatThrownBy(() -> playersBetting.withdrawMoney(new User("jason", 1_000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 플레이어입니다.");
    }

}
