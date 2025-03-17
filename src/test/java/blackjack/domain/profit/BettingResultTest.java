package blackjack.domain.profit;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import blackjack.domain.result.BetAmount;
import blackjack.domain.result.GameResultType;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.PlayerResults;
import blackjack.domain.result.Score;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
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

        PlayerResults playerResults = new PlayerResults();
        playerResults.add(new PlayerResult(player, GameResultType.TIE, new Score(player)));

        PlayerProfits playerProfits = bettingResult.getPlayerProfits();

        // when
        bettingResult.calculateAllResults(playerResults);

        // then
        PlayerProfit playerProfit = playerProfits.findByPlayer(player);
        assertThat(playerProfit.getProfit()).isEqualTo(0);
    }

    @Test
    void 플레이어가_블랙잭으로_이긴_경우_플레이어는_수익을_얻고_딜러는_잃는다() {
        // given
        Hand playerHand = new Hand();
        Player player = new Player("히로", playerHand, new BetAmount(1_000));
        playerHand.takeCard(new Card(CardSuit.HEART, CardRank.ACE));
        playerHand.takeCard(new Card(CardSuit.HEART, CardRank.TEN));

        PlayerResults playerResults = new PlayerResults();
        playerResults.add(new PlayerResult(player, GameResultType.WIN, new Score(player)));// 블랙잭 조건 충족

        PlayerProfits playerProfits = bettingResult.getPlayerProfits();
        DealerProfits dealerProfits = bettingResult.getDealerProfits();

        // when
        bettingResult.calculateAllResults(playerResults);

        // then
        PlayerProfit playerProfit = playerProfits.findByPlayer(player);
        DealerProfit dealerProfit = dealerProfits.findByPlayer(player);

        assertAll(
                () -> assertThat(playerProfit.getProfit()).isEqualTo(1500),
                () -> assertThat(dealerProfit.getProfit()).isEqualTo(-1500)
        );
    }

    @Test
    void 플레이어가_블랙잭은_아니지만_이긴_경우_1배의_수익을_얻는다() {
        // given
        Hand playerHand = new Hand();
        Player player = new Player("히로", playerHand, new BetAmount(1_000));

        PlayerResults playerResults = new PlayerResults();
        playerResults.add(new PlayerResult(player, GameResultType.WIN, new Score(player)));

        PlayerProfits playerProfits = bettingResult.getPlayerProfits();
        DealerProfits dealerProfits = bettingResult.getDealerProfits();

        // when
        bettingResult.calculateAllResults(playerResults);

        // then
        PlayerProfit playerProfit = playerProfits.findByPlayer(player);
        DealerProfit dealerProfit = dealerProfits.findByPlayer(player);

        assertAll(
                () -> assertThat(playerProfit.getProfit()).isEqualTo(1_000),
                () -> assertThat(dealerProfit.getProfit()).isEqualTo(-1_000)
        );
    }

    @Test
    void 플레이어가_진_경우_배팅_금액만큼_잃고_딜러는_얻는다() {
        // given
        Hand playerHand = new Hand();
        Player player = new Player("히로", playerHand, new BetAmount(1_000));

        Dealer dealer = new Dealer(new Hand());

        PlayerResults playerResults = new PlayerResults();
        playerResults.add(new PlayerResult(player, GameResultType.LOSE, new Score(player)));

        PlayerProfits playerProfits = bettingResult.getPlayerProfits();
        DealerProfits dealerProfits = bettingResult.getDealerProfits();

        // when
        bettingResult.calculateAllResults(playerResults);

        // then
        PlayerProfit playerProfit = playerProfits.findByPlayer(player);
        DealerProfit dealerProfit = dealerProfits.findByPlayer(player);

        assertAll(
                () -> assertThat(playerProfit.getProfit()).isEqualTo(-1_000),
                () -> assertThat(dealerProfit.getProfit()).isEqualTo(1_000)
        );
    }
}
