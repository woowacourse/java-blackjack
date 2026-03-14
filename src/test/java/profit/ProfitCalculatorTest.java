package result;

import domain.bet.BetTable;
import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.Dealer;
import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.result.ProfitCalculator;
import domain.result.ResultInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitCalculatorTest {
    Player player;
    Dealer dealer;
    BetTable betTable;

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
        betTable.recodeAmount(player.getName(), 10000);
    }

    @Test
    @DisplayName("플레이어가 게임을 이겼을 때 수익(베팅한 금액만큼)을 베팅테이블에 기록한다.")
    void calculateProfit_returnsBetProfit_whenPlayerIsWin() {
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        int expectedProfit = 10000;

        calculator.playerCalculateProfit(ResultInfo.WIN, player);

        assertThat(betTable.getAmountByName(player.getName())).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어가 게임을 졌을 때 수익(베팅한 금액의 마이너스만큼)을 베팅테이블에 기록한다.")
    void calculateProfit_returnsBetLoss_whenPlayerIsDefeat() {
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        int expectedProfit = -10000;

        calculator.playerCalculateProfit(ResultInfo.DEFEAT, player);

        assertThat(betTable.getAmountByName(player.getName())).isEqualTo(expectedProfit);
    }


    @Test
    @DisplayName("플레이어가 게임을 비겼을 때 수익(0원)을 베팅테이블에 기록한다.")
    void calculateProfit_returnsZero_whenPlayerAndDealerIsDraw() {
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        int expectedProfit = 0;

        calculator.playerCalculateProfit(ResultInfo.DRAW, player);

        assertThat(betTable.getAmountByName(player.getName())).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이겼을 때 수익(베팅한 금액의 1.5배)을 베팅테이블에 기록한다.")
    void calculateProfit_returnsOneAndHalfBet_whenPlayerIsBlackjack() {
        Player player2 = new Player(new PlayerName("user2"), new Hand());
        betTable.recodeAmount(player2.getName(), 15000);
        player2.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        player2.keepCard(new Card(Rank.JACK, Pattern.HEART));
        int expectedProfit = 22500 - 15000;

        ProfitCalculator calculator = new ProfitCalculator(betTable);
        calculator.playerCalculateProfit(ResultInfo.BLACKJACK_WIN, player2);

        assertThat(betTable.getAmountByName(player2.getName())).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어와 딜러가 둘 다 블랙잭이면 플레이어의 수익(0원)을 베팅테이블에 기록한다.")
    void calculateProfit_returnsZero_whenPlayerAndDealerIsBlackjack() {
        // given
        Dealer dealer2 = new Dealer(new Hand());
        Player player2 = new Player(new PlayerName("user2"), new Hand());

        dealer2.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        dealer2.keepCard(new Card(Rank.KING, Pattern.CLOVER));
        player2.keepCard(new Card(Rank.ACE, Pattern.HEART));
        player2.keepCard(new Card(Rank.JACK, Pattern.HEART));

        betTable.recodeAmount(player2.getName(), 15000);

        int expectedProfit = 0;

        // when
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        calculator.playerCalculateProfit(ResultInfo.DRAW, player2);

        // then
        assertThat(betTable.getAmountByName(player2.getName())).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("딜러의 수익을 계산할 수 있다")
    void calculateProfit_returnsDealerProfit() {
        Player player2 = new Player(new PlayerName("user2"), new Hand());
        betTable.recodeAmount(player2.getName(), 15000);
        player2.keepCard(new Card(Rank.TWO, Pattern.SPADE));
        player2.keepCard(new Card(Rank.THREE, Pattern.SPADE));
        int expectedProfit = 5000;

        // 딜러 수익 작성
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        calculator.playerCalculateProfit(ResultInfo.WIN, player);
        calculator.playerCalculateProfit(ResultInfo.DEFEAT, player2);
        int dealerProfit = calculator.dealerCalculateProfit();

        assertThat(dealerProfit).isEqualTo(expectedProfit);
    }
}
