package profit;

import domain.bet.BetTable;
import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.Dealer;
import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.profit.ProfitCalculator;
import domain.profit.ProfitTable;
import domain.result.Result;
import domain.result.ResultInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitCalculatorTest {
    Player player;
    Dealer dealer;
    BetTable betTable;
    ProfitTable profitTable;

    @BeforeEach
    void init() {
        player = new Player(new PlayerName("user1"), new Hand());
        dealer = new Dealer(new Hand());

        //18
        player.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        player.keepCard(new Card(Rank.SEVEN, Pattern.CLOVER));

        //10
        dealer.keepCard(new Card(Rank.EIGHT, Pattern.HEART));
        dealer.keepCard(new Card(Rank.TWO, Pattern.HEART));

        betTable = new BetTable();
        betTable.recordAmount(player.getName(), 10000);

        profitTable = new ProfitTable();
    }

    @Test
    @DisplayName("플레이어가 게임을 이겼을 때 수익(베팅한 금액만큼)을 계산한다.")
    void playerCalculateProfit_returnsBetProfit_whenPlayerIsWin() {
        Result result = new Result();
        result.setPlayerResult(player.getName(), ResultInfo.WIN);
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        int expectedProfit = 10000;

        int profit = calculator.playerCalculateProfit(result, player);

        assertThat(profit).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어가 게임을 졌을 때 수익(베팅한 금액의 마이너스만큼)을 계산한다.")
    void playerCalculateProfit_returnsBetLoss_whenPlayerIsDefeat() {
        Result result = new Result();
        result.setPlayerResult(player.getName(), ResultInfo.DEFEAT);
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        int expectedProfit = -10000;

        int profit = calculator.playerCalculateProfit(result, player);

        assertThat(profit).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어가 게임을 비겼을 때 수익(0원)을 계산한다.")
    void playerCalculateProfit_returnsZero_whenPlayerAndDealerIsDraw() {
        Result result = new Result();
        result.setPlayerResult(player.getName(), ResultInfo.DRAW);
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        int expectedProfit = 0;

        int profit = calculator.playerCalculateProfit(result, player);

        assertThat(profit).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이겼을 때 수익(베팅한 금액의 1.5배)을 계산한다.")
    void playerCalculateProfit_returnsOneAndHalfBet_whenPlayerIsBlackjack() {
        // given
        Player player2 = new Player(new PlayerName("user2"), new Hand());
        int betAmount = 15000;

        betTable.recordAmount(player2.getName(), betAmount);

        player2.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        player2.keepCard(new Card(Rank.JACK, Pattern.HEART));

        Result result = new Result();
        result.setPlayerResult(player2.getName(), ResultInfo.BLACKJACK_WIN);

        int expectedProfit = (int)(betAmount * 1.5);

        // when
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        int profit = calculator.playerCalculateProfit(result, player2);

        // then
        assertThat(profit).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어와 딜러가 둘 다 블랙잭이면 플레이어의 수익(0원)을 계산한다.")
    void playerCalculateProfit_returnsZero_whenPlayerAndDealerIsBlackjack() {
        // given
        Dealer dealer2 = new Dealer(new Hand());
        Player player2 = new Player(new PlayerName("user2"), new Hand());

        betTable.recordAmount(player2.getName(), 15000);

        dealer2.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        dealer2.keepCard(new Card(Rank.KING, Pattern.CLOVER));
        player2.keepCard(new Card(Rank.ACE, Pattern.HEART));
        player2.keepCard(new Card(Rank.JACK, Pattern.HEART));

        Result result = new Result();
        result.setPlayerResult(player2.getName(), ResultInfo.DRAW);

        int expectedProfit = 0;

        // when
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        int profit = calculator.playerCalculateProfit(result, player2);

        // then
        assertThat(profit).isEqualTo(expectedProfit);
    }
}
