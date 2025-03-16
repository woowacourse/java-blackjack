package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BettingResultTest {

    private BettingResult bettingResult;

    @BeforeEach
    void setUp() {
        this.bettingResult = new BettingResult(new DealerProfits(), new PlayerProfits());
    }

    @Test
    void 무승부인_경우_수익은_0이_된다() {
        // given
        Player player = new Player("히로", new Hand(), new BetAmount(1_000));
        Dealer dealer = new Dealer(new Hand());

        PlayerResults playerResults = new PlayerResults();
        playerResults.add(new PlayerResult(player, GameResultType.TIE, new Score(player)));

        DealerResults dealerResults = new DealerResults();
        dealerResults.add(player, new DealerResult(GameResultType.TIE, new Score(dealer)));

        DealerProfits dealerProfits = bettingResult.getDealerProfits();
        PlayerProfits playerProfits = bettingResult.getPlayerProfits();

        // when
        bettingResult.calculateAllResults(playerResults, dealerResults);

        // then
        PlayerProfit playerProfit = playerProfits.findPlayerProfitByPlayer(player);
        assertThat(playerProfit.getProfit()).isEqualTo(0);
    }
}
