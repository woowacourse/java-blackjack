package domain.score;

import domain.player.Bet;
import domain.player.Name;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RevenueBoardTest {

    @Test
    void calculateDealerRevenue() {
        Name tenny = new Name("tenny");
        ScoreBoard scoreBoard = new ScoreBoard(Map.of(
                tenny, new Bet(20000)
        ));
        scoreBoard.updatePlayerScore(tenny, Status.LOSE);

        scoreBoard.calculateDealerRevenue();

        assertThat(scoreBoard.calculateDealerRevenue()).isEqualTo(new Revenue(20000));
    }
}
