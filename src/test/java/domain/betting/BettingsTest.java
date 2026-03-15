package domain.betting;

import domain.betting.exception.BettingException;
import domain.gamer.Player;
import domain.gamer.PlayerName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BettingsTest {

    @Test
    void 플레이어의_베팅금액을_저장한다() {
        int tenThousand = 1000;
        BettingMoney bettingMoney = BettingMoney.bet(tenThousand);
        Player testPlayer = Player.from(new PlayerName("test"));
        Bettings bettings = new Bettings();

        bettings.bet(testPlayer.getGamerName(), bettingMoney);

        BettingMoney actualBettingMoney = bettings.getPlayerBettingMoney(testPlayer.getGamerName());
        BettingMoney expectedBettingMoney = BettingMoney.bet(tenThousand);

        Assertions.assertThat(actualBettingMoney)
                .isEqualTo(expectedBettingMoney);
    }

    @Test
    void 플레이어가_베팅을_두번이상할_수_없다() {
        int tenThousand = 1000;
        BettingMoney bettingMoney = BettingMoney.bet(tenThousand);
        Player testPlayer = Player.from(new PlayerName("test"));
        Bettings bettings = new Bettings();
        bettings.bet(testPlayer.getGamerName(), bettingMoney);

        Assertions.assertThatThrownBy(() -> {
            bettings.bet(testPlayer.getGamerName(), bettingMoney);
        }).isInstanceOf(BettingException.class);
    }

}
