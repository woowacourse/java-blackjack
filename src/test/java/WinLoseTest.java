import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WinLoseTest {

    @DisplayName("나와 상대방의 점수를 받아 이기면 win 이 1 증가한다")
    @Test
    void decideWinStateTest() {
        WinLose winLose = new WinLose();
        int myScore = 21;
        int yourScore = 10;
        winLose.decideWinningState(myScore, yourScore);
        int winCount = winLose.getWinCount();
        int loseCount = winLose.getLoseCount();

        assertThat(winCount).isEqualTo(1);
        assertThat(loseCount).isEqualTo(0);
    }

    @DisplayName("나와 상대방의 점수를 받아 지면 lose 가 1 증가한다")
    @Test
    void decideLoseStateTest() {
        WinLose winLose = new WinLose();
        int myScore = 10;
        int yourScore = 21;
        winLose.decideWinningState(myScore, yourScore);
        int winCount = winLose.getWinCount();
        int loseCount = winLose.getLoseCount();

        assertThat(winCount).isEqualTo(0);
        assertThat(loseCount).isEqualTo(1);
    }
}
