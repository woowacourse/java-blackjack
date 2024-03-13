package domain.betting;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.game.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitTest {
    @Test
    @DisplayName("베팅액과 수익률을 통해 최종 수익을 생성 한다.")
    void create() {
        int betMoney = 20000;
        Bet bet = new Bet(betMoney);

        Profit PlayerWinProfit = Profit.of(bet, ProfitRate.from(Result.PLAYER_WIN));
        Profit DealerWinProfit = Profit.of(bet, ProfitRate.from(Result.DEALER_WIN));
        Profit PushProfit = Profit.of(bet, ProfitRate.from(Result.PUSH));
        Profit BLACK_JACK_Profit = Profit.of(bet, ProfitRate.from(Result.PLAYER_BLACK_JACK));

        assertAll(
                () -> assertEquals(betMoney * 1, PlayerWinProfit.getMoney()),
                () -> assertEquals(betMoney * -1, DealerWinProfit.getMoney()),
                () -> assertEquals(betMoney * 0, PushProfit.getMoney()),
                () -> assertEquals(betMoney * 1.5, BLACK_JACK_Profit.getMoney())
        );
    }
}
