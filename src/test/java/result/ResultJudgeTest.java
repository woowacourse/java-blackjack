package result;

import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.*;
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
    Player winPlayer;
    Player defeatPlayer;
    Player drawPlayer;
    Players players;

    @BeforeEach
    void init() {
        dealer = new Dealer(new Hand());
        dealer.keepCard(new Card(Rank.FIVE, Pattern.CLOVER));

        winPlayer = new Player(new PlayerName("이김"), new Hand());
        winPlayer.keepCard(new Card(Rank.SIX, Pattern.CLOVER));

        defeatPlayer = new Player(new PlayerName("짐"), new Hand());
        defeatPlayer.keepCard(new Card(Rank.FOUR, Pattern.CLOVER));

        drawPlayer = new Player(new PlayerName("비김"), new Hand());
        drawPlayer.keepCard(new Card(Rank.FIVE, Pattern.SPADE));

        players = new Players(List.of(winPlayer, defeatPlayer, drawPlayer));
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
    void ResultJudge_ReturnsDrawWhenPlayerAndDealerAreBothBust(){
        ResultJudge resultJudge = new ResultJudge();
        dealer.keepCard(new Card(Rank.NINE, Pattern.CLOVER));
        dealer.keepCard(new Card(Rank.JACK, Pattern.CLOVER));
        drawPlayer.keepCard(new Card(Rank.SEVEN, Pattern.SPADE));
        drawPlayer.keepCard(new Card(Rank.QUEEN, Pattern.SPADE));

        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(drawPlayer.getName());

        assertThat(info).isEqualTo(ResultInfo.DEFEAT);
    }

    @Test
    @DisplayName("딜러가 버스트되면 플레이어가 승리한다.")
    void ResultJudge_ReturnsWinWhenDealerIsBust(){
        ResultJudge resultJudge = new ResultJudge();
        dealer.keepCard(new Card(Rank.NINE, Pattern.CLOVER));
        dealer.keepCard(new Card(Rank.JACK, Pattern.CLOVER));
        winPlayer.keepCard(new Card(Rank.SEVEN, Pattern.SPADE));

        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(drawPlayer.getName());

        assertThat(info).isEqualTo(ResultInfo.WIN);
    }

    @Test
    @DisplayName("플레이어가 버스트 되면 플레이어가 패배한다")
    void ResultJudge_ReturnsDefeatWhenPlayerIsBust(){
        ResultJudge resultJudge = new ResultJudge();
        dealer.keepCard(new Card(Rank.NINE, Pattern.CLOVER));
        defeatPlayer.keepCard(new Card(Rank.SIX, Pattern.SPADE));

        Result result = resultJudge.calculateResult(dealer, players);
        ResultInfo info = result.getGameResult().get(defeatPlayer.getName());

        assertThat(info).isEqualTo(ResultInfo.DEFEAT);
    }

}
