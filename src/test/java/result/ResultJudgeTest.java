package result;

import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.Dealer;
import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.Players;
import domain.result.Result;
import domain.result.ResultInfo;
import domain.result.ResultJudge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ResultJudgeTest {

    @Test
    @DisplayName("플레이어의 총합이 딜러의 총합보다 높으면 플레이어가 승리한다.")
    void calculateResult_ReturnsWin_WhenPlayerTotalScoreIsHigherThanDealerTotalScore() {
        // given
        Dealer dealer = new Dealer(new Hand());
        Player winPlayer = new Player(new PlayerName("user1"), new Hand());
        Players players = new Players(List.of(winPlayer));

        dealer.keepCard(new Card(Rank.ACE, Pattern.HEART));
        dealer.keepCard(new Card(Rank.SEVEN, Pattern.HEART));
        winPlayer.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        winPlayer.keepCard(new Card(Rank.EIGHT, Pattern.CLOVER));

        ResultJudge resultJudge = new ResultJudge();

        // when
        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(winPlayer.getName());

        //then
        assertThat(info).isEqualTo(ResultInfo.WIN);
    }

    @Test
    @DisplayName("플레이어의 총합이 딜러의 총합보다 낮으면 플레이어가 패배한다.")
    void calculateResult_ReturnsDefeat_WhenPlayerTotalScoreIsLowerThanDealerTotalScore() {
        // given
        Dealer dealer = new Dealer(new Hand());
        Player defeatPlayer = new Player(new PlayerName("user1"), new Hand());
        Players players = new Players(List.of(defeatPlayer));

        dealer.keepCard(new Card(Rank.ACE, Pattern.HEART));
        dealer.keepCard(new Card(Rank.SEVEN, Pattern.HEART));
        defeatPlayer.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        defeatPlayer.keepCard(new Card(Rank.TWO, Pattern.CLOVER));

        ResultJudge resultJudge = new ResultJudge();

        // when
        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(defeatPlayer.getName());

        //then
        assertThat(info).isEqualTo(ResultInfo.DEFEAT);
    }

    @Test
    @DisplayName("플레이어의 총합과 딜러의 총합이 같으면 비긴다.")
    void calculateResult_ReturnsDraw_WhenPlayerTotalScoreAndDealerTotalScoreAreSame() {
        // given
        Dealer dealer = new Dealer(new Hand());
        Player drawPlayer = new Player(new PlayerName("user1"), new Hand());
        Players players = new Players(List.of(drawPlayer));

        dealer.keepCard(new Card(Rank.ACE, Pattern.HEART));
        dealer.keepCard(new Card(Rank.EIGHT, Pattern.HEART));
        drawPlayer.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        drawPlayer.keepCard(new Card(Rank.EIGHT, Pattern.CLOVER));

        ResultJudge resultJudge = new ResultJudge();

        // when
        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(drawPlayer.getName());

        //then
        assertThat(info).isEqualTo(ResultInfo.DRAW);
    }

    @Test
    @DisplayName("딜러와 플레이어가 둘 다 버스트되면 플레이어 패배를 우선으로 처리한다.")
    void calculateResult_ReturnsPlayerDefeat_WhenPlayerAndDealerAreBothBust() {
        // given
        Dealer bustDealer = new Dealer(new Hand());
        Player bustPlayer = new Player(new PlayerName("user1"), new Hand());
        Players players = new Players(List.of(bustPlayer));

        bustDealer.keepCard(new Card(Rank.JACK, Pattern.HEART));
        bustDealer.keepCard(new Card(Rank.QUEEN, Pattern.HEART));
        bustDealer.keepCard(new Card(Rank.KING, Pattern.HEART));

        bustPlayer.keepCard(new Card(Rank.JACK, Pattern.CLOVER));
        bustPlayer.keepCard(new Card(Rank.QUEEN, Pattern.CLOVER));
        bustPlayer.keepCard(new Card(Rank.KING, Pattern.CLOVER));

        ResultJudge resultJudge = new ResultJudge();

        // when
        Result result = resultJudge.calculateResult(bustDealer, players);
        ResultInfo info = result.getGameResult().get(bustPlayer.getName());

        // then
        assertThat(info).isEqualTo(ResultInfo.DEFEAT);
    }

    @Test
    @DisplayName("딜러가 버스트되면 플레이어가 승리한다.")
    void calculateResult_ReturnsWin_WhenDealerIsBust() {
        // given
        Dealer bustDealer = new Dealer(new Hand());
        Player winPlayer = new Player(new PlayerName("user1"), new Hand());
        Players players = new Players(List.of(winPlayer));

        bustDealer.keepCard(new Card(Rank.JACK, Pattern.HEART));
        bustDealer.keepCard(new Card(Rank.QUEEN, Pattern.HEART));
        bustDealer.keepCard(new Card(Rank.KING, Pattern.HEART));

        winPlayer.keepCard(new Card(Rank.JACK, Pattern.CLOVER));
        winPlayer.keepCard(new Card(Rank.QUEEN, Pattern.CLOVER));

        ResultJudge resultJudge = new ResultJudge();

        // when
        Result result = resultJudge.calculateResult(bustDealer, players);
        ResultInfo info = result.getGameResult().get(winPlayer.getName());

        // then
        assertThat(info).isEqualTo(ResultInfo.WIN);
    }

    @Test
    @DisplayName("플레이어가 버스트 되면 플레이어가 패배한다.")
    void calculateResult_ReturnsDefeat_WhenPlayerIsBust() {
        // given
        Dealer dealer = new Dealer(new Hand());
        Player bustPlayer = new Player(new PlayerName("user1"), new Hand());
        Players players = new Players(List.of(bustPlayer));

        dealer.keepCard(new Card(Rank.JACK, Pattern.HEART));
        dealer.keepCard(new Card(Rank.QUEEN, Pattern.HEART));

        bustPlayer.keepCard(new Card(Rank.JACK, Pattern.CLOVER));
        bustPlayer.keepCard(new Card(Rank.QUEEN, Pattern.CLOVER));
        bustPlayer.keepCard(new Card(Rank.KING, Pattern.CLOVER));

        ResultJudge resultJudge = new ResultJudge();

        // when
        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(bustPlayer.getName());

        // then
        assertThat(info).isEqualTo(ResultInfo.DEFEAT);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 되면 승리한다.")
    void calculateResult_ReturnsBlackJackWin_WhenPlayerIsBlackJack() {
        // given
        Dealer dealer = new Dealer(new Hand());
        Player blackJackPlayer = new Player(new PlayerName("user1"), new Hand());
        Players players = new Players(List.of(blackJackPlayer));

        dealer.keepCard(new Card(Rank.JACK, Pattern.HEART));
        dealer.keepCard(new Card(Rank.QUEEN, Pattern.HEART));

        blackJackPlayer.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        blackJackPlayer.keepCard(new Card(Rank.QUEEN, Pattern.CLOVER));

        ResultJudge resultJudge = new ResultJudge();

        // when
        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(blackJackPlayer.getName());

        // then
        assertThat(info).isEqualTo(ResultInfo.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러가 둘 다 블랙잭이면 무승부이다.")
    void calculateResult_ReturnDraws_WhenPlayerAndDealerIsBlackJack() {
        // given
        Dealer blackJackdealer = new Dealer(new Hand());
        Player blackJackPlayer = new Player(new PlayerName("user1"), new Hand());
        Players players = new Players(List.of(blackJackPlayer));

        blackJackdealer.keepCard(new Card(Rank.ACE, Pattern.HEART));
        blackJackdealer.keepCard(new Card(Rank.QUEEN, Pattern.HEART));

        blackJackPlayer.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        blackJackPlayer.keepCard(new Card(Rank.QUEEN, Pattern.CLOVER));

        ResultJudge resultJudge = new ResultJudge();

        // when
        Result result = resultJudge.calculateResult(blackJackdealer, players);
        ResultInfo info = result.getGameResult().get(blackJackPlayer.getName());

        // then
        assertThat(info).isEqualTo(ResultInfo.DRAW);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 버스트이면 블랙잭 승리 처리한다.")
    void calculateResult_ReturnBlackJackWins_WhenPlayerIsBlackJackAndDealerIsBust() {
        // given
        Dealer bustDealer = new Dealer(new Hand());
        Player blackJackPlayer = new Player(new PlayerName("user1"), new Hand());
        Players players = new Players(List.of(blackJackPlayer));

        bustDealer.keepCard(new Card(Rank.JACK, Pattern.HEART));
        bustDealer.keepCard(new Card(Rank.QUEEN, Pattern.HEART));
        bustDealer.keepCard(new Card(Rank.KING, Pattern.HEART));

        blackJackPlayer.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        blackJackPlayer.keepCard(new Card(Rank.QUEEN, Pattern.CLOVER));

        ResultJudge resultJudge = new ResultJudge();

        // when
        Result result = resultJudge.calculateResult(bustDealer, players);
        ResultInfo info = result.getGameResult().get(blackJackPlayer.getName());

        // then
        assertThat(info).isEqualTo(ResultInfo.BLACKJACK_WIN);
    }
}
