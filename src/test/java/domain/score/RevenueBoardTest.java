package domain.score;

import domain.player.Bet;
import domain.player.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class RevenueBoardTest {

    @Test
    void calculateDealerRevenue() {
        Name tenny = new Name("tenny");
        ScoreBoard scoreBoard = new ScoreBoard(Map.of(
                tenny, new Bet(20000)
        ));
        scoreBoard.updatePlayerScore(tenny, Status.LOSE);

        scoreBoard.calculateDealerRevenue();

        Assertions.assertThat(scoreBoard.getDealerScore().getAmount()).isEqualTo(20000);
    }
}