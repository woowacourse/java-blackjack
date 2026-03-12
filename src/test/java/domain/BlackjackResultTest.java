package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    @Test
    @DisplayName("딜러와 플레이어 모두 bust 되지 않은 경우의 승패를 판정한다.")
    void 딜러_플레이어_모두_bust_되지_않은_경우_승패_판정_테스트() {
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

    @Test
    @DisplayName("딜러만 bust된 경우의 승패를 판정한다.")
    void 딜러_bust_된_경우_승패_판정_테스트() {
        int dealerScore = 23;
        int playerScore = 19;
        GameResult playerResult = GameResult.DRAW;

        if (dealerScore > Policy.BLACKJACK_NUMBER){
            dealerScore = Policy.BUST_SCORE;
        }
        if (playerScore > Policy.BLACKJACK_NUMBER){
            playerScore = Policy.BUST_SCORE;
        }
        if (playerScore > dealerScore) {
            playerResult = GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            playerResult = GameResult.LOSE;
        }

        GameResult expect = GameResult.WIN;
        assertThat(playerResult).isEqualTo(expect);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 bust 된 경우의 승패를 판정한다.")
    void 딜러_플레이어_모두_bust_된_경우_승패_판정_테스트() {
        int dealerScore = 23;
        int playerScore = 22;
        GameResult playerResult = GameResult.DRAW;

        if (dealerScore > Policy.BLACKJACK_NUMBER){
            dealerScore = Policy.BUST_SCORE;
        }
        if (playerScore > Policy.BLACKJACK_NUMBER){
            playerScore = Policy.BUST_SCORE;
        }
        if (playerScore > dealerScore) {
            playerResult = GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            playerResult = GameResult.LOSE;
        }

        GameResult expect = GameResult.DRAW;
        assertThat(playerResult).isEqualTo(expect);
    }
}