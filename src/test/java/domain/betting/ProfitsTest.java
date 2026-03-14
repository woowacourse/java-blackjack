package domain.betting;

import domain.betting.exception.BettingException;
import domain.gamer.Player;
import domain.gamer.PlayerName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProfitsTest {

    @Test
    void 하나의_베팅금액에_두번이상의_수익을_정산할_수_없다() {
        int tenThousand = 1000;
        BettingMoney bettingMoney = BettingMoney.bet(tenThousand);
        Player testPlayer = Player.from(new PlayerName("test"));
        Profits profits = new Profits();

        Profit profit = bettingMoneyToProfit(bettingMoney, BettingRate.PLAYER_WIN.getBettingRate());
        profits.settleProfit(testPlayer.getGamerName(), profit);

        Assertions.assertThatThrownBy(() -> {
            profits.settleProfit(testPlayer.getGamerName(), profit);
        }).isInstanceOf(BettingException.class);
    }

    private Profit bettingMoneyToProfit(BettingMoney bettingMoney, double rate) {
        return new Profit(bettingMoney.withRate(rate));
    }

}
