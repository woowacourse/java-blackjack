package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    @Test
    @DisplayName("딜러와 플레이어 승패 결과 테스트")
    void 딜러_카드합계_VS_플레이어_카드합계() {
        int dealerScore = 20;
        int playerScore = 19;
        GameResult playerResult = GameResult.DRAW;

        if (playerScore > dealerScore) {
            playerResult = GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            playerResult = GameResult.LOSE;
        }

        GameResult expect = GameResult.LOSE;
        assertThat(playerResult).isEqualTo(expect);
    }
}