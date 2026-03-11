package domain.betting;

import domain.gamer.Player;
import domain.gamer.PlayerName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BettingsTest {

    @Test
    void 플레이어의_베팅금액을_저장한다() {
        double tenThousand = 1000.0;
        Money bettingMoney = Money.from(tenThousand);
        Player testPlayer = Player.from(new PlayerName("test"));
        Bettings bettings = new Bettings();

        bettings.bet(testPlayer, bettingMoney);

        Money actualBettingMoney = bettings.getPlayerBettingMoney(testPlayer);
        Money expectedBettingMoney = Money.from(tenThousand);

        Assertions.assertThat(actualBettingMoney)
                .isEqualTo(expectedBettingMoney);
    }

    @Test
    void 플레이어의_베팅금액에_베팅률을_반영한다() {
        double tenThousand = 1000.0;
        double testTimes = 2.0;
        Money bettingMoney = Money.from(tenThousand);
        Player testPlayer = Player.from(new PlayerName("test"));
        Bettings bettings = new Bettings();

        bettings.bet(testPlayer, bettingMoney);
        bettings.calculateBettingMoney(testPlayer, testTimes);

        Money actualBettingMoney = bettings.getPlayerBettingMoney(testPlayer);
        Money expectedBettingMoney = Money.from(tenThousand * testTimes);

        Assertions.assertThat(actualBettingMoney)
                .isEqualTo(expectedBettingMoney);
    }


}
