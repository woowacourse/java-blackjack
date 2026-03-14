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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ResultJudgeTest {

    Dealer dealer;
    Dealer blackJackDealer;
    Dealer bustDealer;

    Player winPlayer;
    Player defeatPlayer;
    Player drawPlayer;
    Player blackJackPlayer;
    Player bustPlayer;

    Players players;

    @BeforeEach
    void init() {
        // 딜러
        dealer = new Dealer(new Hand());
        dealer.keepCard(new Card(Rank.FIVE, Pattern.CLOVER));

        blackJackDealer = new Dealer(new Hand());
        blackJackDealer.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        blackJackDealer.keepCard(new Card(Rank.JACK, Pattern.CLOVER));

        bustDealer = new Dealer(new Hand());
        bustDealer.keepCard(new Card(Rank.NINE, Pattern.HEART));
        bustDealer.keepCard(new Card(Rank.JACK, Pattern.HEART));
        bustDealer.keepCard(new Card(Rank.QUEEN, Pattern.HEART));

        // 플레이어
        winPlayer = new Player(new PlayerName("이김"), new Hand());
        winPlayer.keepCard(new Card(Rank.SIX, Pattern.CLOVER));

        defeatPlayer = new Player(new PlayerName("짐"), new Hand());
        defeatPlayer.keepCard(new Card(Rank.FOUR, Pattern.CLOVER));

        drawPlayer = new Player(new PlayerName("비김"), new Hand());
        drawPlayer.keepCard(new Card(Rank.FIVE, Pattern.SPADE));

        blackJackPlayer = new Player(new PlayerName("블랙잭"), new Hand());
        blackJackPlayer.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        blackJackPlayer.keepCard(new Card(Rank.JACK, Pattern.CLOVER));

        bustPlayer = new Player(new PlayerName("버스트"), new Hand());
        bustPlayer.keepCard(new Card(Rank.NINE, Pattern.DIAMOND));
        bustPlayer.keepCard(new Card(Rank.JACK, Pattern.DIAMOND));
        bustPlayer.keepCard(new Card(Rank.QUEEN, Pattern.DIAMOND));

        // 플레이어들
        players = new Players(List.of(winPlayer, defeatPlayer, drawPlayer, blackJackPlayer, bustPlayer));
    }

    @Test
    void 딜러의_총합이_플레이어의_총합보다_낮으면_플레이어가_승리한다() {
        ResultJudge resultJudge = new ResultJudge();
        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(winPlayer.getName());
        assertThat(info).isEqualTo(ResultInfo.WIN);
    }

    @Test
    void 딜러의_총합이_플레이어의_총합보다_높으면_플레이어가_패배한다() {
        ResultJudge resultJudge = new ResultJudge();
        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(defeatPlayer.getName());
        assertThat(info).isEqualTo(ResultInfo.DEFEAT);
    }

    @Test
    void 딜러의_총합과_플레이어의_총합이_같으면_비긴다() {
        ResultJudge resultJudge = new ResultJudge();
        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(drawPlayer.getName());
        assertThat(info).isEqualTo(ResultInfo.DRAW);
    }

    @Test
    @DisplayName("딜러와 플레이어가 둘 다 버스트되면 플레이어 패배를 우선으로 처리한다.")
    void calculateResult_ReturnsPlayerDefeat_WhenPlayerAndDealerAreBothBust() {
        ResultJudge resultJudge = new ResultJudge();

        Result result = resultJudge.calculateResult(bustDealer, players);
        ResultInfo info = result.getGameResult().get(bustPlayer.getName());

        assertThat(info).isEqualTo(ResultInfo.DEFEAT);
    }

    @Test
    @DisplayName("딜러가 버스트되면 플레이어가 승리한다.")
    void calculateResult_ReturnsWin_WhenDealerIsBust() {
        ResultJudge resultJudge = new ResultJudge();

        Result result = resultJudge.calculateResult(bustDealer, players);
        ResultInfo info = result.getGameResult().get(winPlayer.getName());

        assertThat(info).isEqualTo(ResultInfo.WIN);
    }

    @Test
    @DisplayName("플레이어가 버스트 되면 플레이어가 패배한다.")
    void calculateResult_ReturnsDefeat_WhenPlayerIsBust() {
        ResultJudge resultJudge = new ResultJudge();

        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(bustPlayer.getName());

        assertThat(info).isEqualTo(ResultInfo.DEFEAT);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 되면 승리한다.")
    void calculateResult_ReturnsBlackJackWin_WhenPlayerIsBlackJack() {
        ResultJudge resultJudge = new ResultJudge();
        Result result = resultJudge.calculateResult(dealer, players);

        ResultInfo resultInfo = result.getGameResult().get(blackJackPlayer.getName());
        assertThat(resultInfo).isEqualTo(ResultInfo.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러가 둘 다 블랙잭이면 무승부이다.")
    void calculateResult_ReturnDraws_WhenPlayerAndDealerIsBlackJack() {
        ResultJudge resultJudge = new ResultJudge();
        Result result = resultJudge.calculateResult(blackJackDealer, players);

        ResultInfo resultInfo = result.getGameResult().get(blackJackPlayer.getName());
        assertThat(resultInfo).isEqualTo(ResultInfo.DRAW);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 버스트이면 블랙잭 승리 처리한다.")
    void calculateResult_ReturnBlackJackWins_WhenPlayerIsBlackJackAndDealerIsBust() {
        ResultJudge resultJudge = new ResultJudge();
        Result result = resultJudge.calculateResult(bustDealer, players);

        ResultInfo resultInfo = result.getGameResult().get(blackJackPlayer.getName());
        assertThat(resultInfo).isEqualTo(ResultInfo.BLACKJACK_WIN);
    }
}
