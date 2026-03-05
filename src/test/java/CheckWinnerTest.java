import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Dealer;
import domain.Outcome;
import domain.Player;
import domain.Players;
import domain.Score;
import dto.DealerPlayersDTO;
import java.lang.reflect.Field;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.CheckWinner;
import util.NameParser;

public class CheckWinnerTest {
    @Test
    @DisplayName("점수가 같으면 무승부다")
    void drawResult() throws Exception {
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList("pobi");
        Player player = players.getPlayer(0);
        setScore(dealer.getScore(), 20);
        setScore(player.getScore(), 20);

        CheckWinner.decideWinner(new DealerPlayersDTO(dealer, players));

        assertEquals(Outcome.무, player.getOutcome());
    }

    @Test
    @DisplayName("딜러 점수가 더 크면 플레이어는 패배한다")
    void loseResult() throws Exception {
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList("pobi");
        Player player = players.getPlayer(0);
        setScore(dealer.getScore(), 20);
        setScore(player.getScore(), 18);

        CheckWinner.decideWinner(new DealerPlayersDTO(dealer, players));

        assertEquals(Outcome.패, player.getOutcome());
    }

    @Test
    @DisplayName("플레이어 점수가 더 크면 플레이어는 승리한다")
    void winResult() throws Exception {
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList("pobi");
        Player player = players.getPlayer(0);
        setScore(dealer.getScore(), 18);
        setScore(player.getScore(), 20);

        CheckWinner.decideWinner(new DealerPlayersDTO(dealer, players));

        assertEquals(Outcome.승, player.getOutcome());
    }

    private void setScore(Score score, int value) throws Exception {
        Field scoreField = Score.class.getDeclaredField("score");
        scoreField.setAccessible(true);
        scoreField.set(score, value);
    }
}
