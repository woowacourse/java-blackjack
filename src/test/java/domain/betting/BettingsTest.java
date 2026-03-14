package domain.betting;

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
    void 딜러의_수익금을_구할때는_플레이어_수익금_누적합에_마이너스1을_곱한다() {
        int tenThousand = 1000;
        BettingMoney bettingMoney = BettingMoney.bet(tenThousand);
        Player testPlayer = Player.from(new PlayerName("test"));
        Profits profits = new Profits();

        Profit profit = bettingMoneyToProfit(bettingMoney, BettingRate.PLAYER_WIN.getBettingRate());
        profits.settleProfit(testPlayer.getGamerName(), profit);

        Profit actualDealerProfit = profits.calculateDealerProfit();
        Profit expectedDealerProfit = new Profit(tenThousand).reverseProfit();

        Assertions.assertThat(actualDealerProfit)
                .isEqualTo(expectedDealerProfit);
    }

    public Profit bettingMoneyToProfit(BettingMoney bettingMoney, double rate) {
        return new Profit(bettingMoney.withRate(rate));
    }


}
