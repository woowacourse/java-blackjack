import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.result.Result;
import domain.result.ResultInfo;
import domain.participant.Dealer;
import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.result.ResultJudge;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ResultJudgeTest {

    Dealer dealer;
    Player winPlayer;
    Player defeatPlayer;
    Player drawPlayer;
    Players players;

    @BeforeEach
    void init() {
        dealer = new Dealer(new Hand());
        winPlayer = new Player("이김", new Hand());
        defeatPlayer = new Player("짐", new Hand());
        drawPlayer = new Player("비김", new Hand());
        dealer.keepCard(new Card(Rank.FIVE, Pattern.CLOVER));
        winPlayer.keepCard(new Card(Rank.SIX, Pattern.CLOVER));
        defeatPlayer.keepCard(new Card(Rank.FOUR, Pattern.CLOVER));
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
}
