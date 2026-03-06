package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @Test
    @DisplayName("딜러의 카드 합보다 플레이어의 카드 합이 높으면 승 판정")
    void winTest() {
        // given
        int dealerScore = 16;
        int playerScore = 17;
        // when & then
        assertThat(GameResult.getResult(playerScore, dealerScore).getStatus())
                .isEqualTo("승");
    }

    @Test
    @DisplayName("딜러의 카드 합보다 플레이어의 카드 합이 낮으면 패 판정")
    void loseTest() {
        // given
        int dealerScore = 17;
        int playerScore = 16;
        // when & then
        assertThat(GameResult.getResult(playerScore, dealerScore).getStatus())
                .isEqualTo("패");
    }

    @Test
    @DisplayName("딜러의 카드 합보다 플레이어의 카드 합이 같으면 무 판정")
    void drawTest() {
        // given
        int dealerScore = 16;
        int playerScore = 16;
        // when & then
        assertThat(GameResult.getResult(playerScore, dealerScore).getStatus())
                .isEqualTo("무");
    }
}