package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BetsTest {

    @Test
    void 배팅은_결과를_받아_각_플레이어의_수익을_계산한다() {
        //given
        final Player player = new Player("dummy");
        final GameResult gameResult = GameResult.LOSE;
        final Result playerResult = new Result().updateResult(gameResult);
        final Money money = Money.betting(1000);

        final Bets bets = new Bets();
        bets.betting(player, money);

        final Map<User, Result> result = new HashMap<>();
        result.put(player, playerResult);

        //when
        bets.updatePrizes(result);

        //then
        assertThat(bets.getBets().get(player).getValue()).isEqualTo(money.calculatePrize(gameResult).getValue());
    }

    @Test
    void 배팅은_결과를_토대로_딜러의_수익을_계산한다() {
        //given
        final Player dummy = new Player("dummy");
        final Player dummy2 = new Player("dummy2");
        final GameResult dummyGameResult1 = GameResult.LOSE;
        final GameResult dummyGameResult2 = GameResult.WIN;
        final Result dummyPlayerResult1 = new Result().updateResult(dummyGameResult1);
        final Result dummyPlayerResult2 = new Result().updateResult(dummyGameResult2);
        final Money dummyMoney1 = Money.betting(1000);
        final Money dummyMoney2 = Money.betting(1500);

        final Bets bets = new Bets();
        bets.betting(dummy, dummyMoney1);
        bets.betting(dummy2, dummyMoney2);

        final Map<User, Result> result = new HashMap<>();
        result.put(dummy, dummyPlayerResult1);
        result.put(dummy2, dummyPlayerResult2);

        //when
        bets.updatePrizes(result);

        //then
        assertThat(bets.getDealerProfit().getValue()).isEqualTo(-500);
    }
}
