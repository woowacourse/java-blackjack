package Bet;

import domain.bet.BetTable;
import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.Dealer;
import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.Players;
import domain.result.ProfitCalculator;
import domain.result.Result;
import domain.result.ResultInfo;
import domain.result.ResultJudge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BetTableTest {
    @Test
    @DisplayName("플레이어는 베팅 금액을 설정할 수 있다.")
    void placeBet_setsBetAmount() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        BetTable betTable = new BetTable();
        int amount = 10000;

        betTable.placeBet(player.getName(), amount);

        assertThat(betTable.getAmountByName(player.getName())).isEqualTo(amount);
    }

    @Test
    @DisplayName("게임승리 후 플레이어의 수익금을 출력할 수 있다.")
    void getAmountByName_printsPlayerProfitAfterGameWin(){
        Player player = new Player(new PlayerName("user1"), new Hand());
        Dealer dealer = new Dealer(new Hand());
        BetTable betTable = new BetTable();

        player.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        player.keepCard(new Card(Rank.THREE, Pattern.CLOVER));
        dealer.keepCard(new Card(Rank.ACE, Pattern.SPADE));
        dealer.keepCard(new Card(Rank.ACE, Pattern.HEART));

        int beforeBetAmount = 10000;
        betTable.placeBet(player.getName(), beforeBetAmount);

        ResultJudge resultJudge = new ResultJudge();
        Result result = resultJudge.calculateResult(dealer, new Players(List.of(player)));
        ResultInfo resultInfo = result.getGameResult().get(player.getName());
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        calculator.playerCalculateProfit(resultInfo, player);

        int afterBetAmount = betTable.getAmountByName(player.getName());

        assertThat(afterBetAmount).isEqualTo(beforeBetAmount);
    }

    @Test
    @DisplayName("게임패배 후 플레이어의 수익금을 출력할 수 있다.")
    void getAmountByName_printsPlayerProfitAfterGameDefeat(){
        Player player = new Player(new PlayerName("user1"), new Hand());
        Dealer dealer = new Dealer(new Hand());
        BetTable betTable = new BetTable();

        player.keepCard(new Card(Rank.TWO, Pattern.CLOVER));
        player.keepCard(new Card(Rank.THREE, Pattern.CLOVER));
        dealer.keepCard(new Card(Rank.JACK, Pattern.SPADE));
        dealer.keepCard(new Card(Rank.ACE, Pattern.HEART));

        int beforeBetAmount = 10000;
        betTable.placeBet(player.getName(), beforeBetAmount);

        ResultJudge resultJudge = new ResultJudge();
        Result result = resultJudge.calculateResult(dealer, new Players(List.of(player)));
        ResultInfo resultInfo = result.getGameResult().get(player.getName());
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        calculator.playerCalculateProfit(resultInfo, player);

        int expectedProfit = -10000;
        int afterBetAmount = betTable.getAmountByName(player.getName());

        assertThat(afterBetAmount).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("게임무승부 후 플레이어의 수익금을 출력할 수 있다.")
    void getAmountByName_printsPlayerProfitAfterGameDraw(){
        Player player = new Player(new PlayerName("user1"), new Hand());
        Dealer dealer = new Dealer(new Hand());
        BetTable betTable = new BetTable();

        player.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        player.keepCard(new Card(Rank.THREE, Pattern.CLOVER));
        dealer.keepCard(new Card(Rank.ACE, Pattern.SPADE));
        dealer.keepCard(new Card(Rank.THREE, Pattern.HEART));

        int beforeBetAmount = 10000;
        betTable.placeBet(player.getName(), beforeBetAmount);

        ResultJudge resultJudge = new ResultJudge();
        Result result = resultJudge.calculateResult(dealer, new Players(List.of(player)));
        ResultInfo resultInfo = result.getGameResult().get(player.getName());
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        calculator.playerCalculateProfit(resultInfo, player);

        int expectedAmount = 0;
        int afterBetAmount = betTable.getAmountByName(player.getName());

        assertThat(afterBetAmount).isEqualTo(expectedAmount);
    }

    @Test
    @DisplayName("블랙잭으로 게임 승리 후 플레이어의 수익금을 출력할 수 있다.")
    void getAmountByName_printsPlayerProfitAfterGameBlackJackWin(){
        Player player = new Player(new PlayerName("user1"), new Hand());
        Dealer dealer = new Dealer(new Hand());
        BetTable betTable = new BetTable();

        player.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        player.keepCard(new Card(Rank.JACK, Pattern.CLOVER));
        dealer.keepCard(new Card(Rank.ACE, Pattern.SPADE));
        dealer.keepCard(new Card(Rank.ACE, Pattern.HEART));

        int beforeBetAmount = 10000;
        betTable.placeBet(player.getName(), beforeBetAmount);

        ResultJudge resultJudge = new ResultJudge();
        Result result = resultJudge.calculateResult(dealer, new Players(List.of(player)));
        ResultInfo resultInfo = result.getGameResult().get(player.getName());
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        calculator.playerCalculateProfit(resultInfo, player);

        int expectedProfit = 15000;
        int afterBetAmount = betTable.getAmountByName(player.getName());

        assertThat(afterBetAmount).isEqualTo(expectedProfit);
    }

}
