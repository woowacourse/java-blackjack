package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    @Test
    @DisplayName("플레이어 승패 테스트")
    void testPlayerWinOrLose() {
        assertThat(GameResult.judgeHand(11, 15)).isEqualTo(GameResult.WIN);
        assertThat(GameResult.judgeHand(21, 15)).isEqualTo(GameResult.LOSE);
        assertThat(GameResult.judgeHand(21, 21)).isEqualTo(GameResult.TIE);
        assertThat(GameResult.judgeHand(25, 22)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어의 승패에 따라 딜러 승패가 잘 결정되는지 테스트")
    void testDealerWinOrLoseByPlayer() {
        GameResult playerWin = GameResult.judgeHand(11, 15);
        GameResult playerLose = GameResult.judgeHand(15, 10);

        assertThat(GameResult.reverseResult(playerWin)).isEqualTo(GameResult.LOSE);
        assertThat(GameResult.reverseResult(playerLose)).isEqualTo(GameResult.WIN);
    }
}
