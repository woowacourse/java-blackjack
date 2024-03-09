package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {
    @ParameterizedTest
    @DisplayName("플레이어가 버스트 되지 않고 점수가 딜러보다 높으면 플레이어가 승리한다.")
    @CsvSource({
            "20, 19",
            "15, 22"
    })
    void playerWinTest(int playerScoreValue, int dealerScoreValue) {
        Score playerScore = new Score(playerScoreValue);
        Score dealerScore = new Score(dealerScoreValue);

        GameResult winResult = GameResult.calculatePlayerResult(playerScore, dealerScore);
        GameResult expected = GameResult.WIN;
        assertThat(winResult).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("플레이어가 버스트되거나 플레이어의 점수가 딜러보다 낮으면 플레이어가 패배한다.")
    @CsvSource({
            "22, 18",
            "18, 20",
            "22, 22"
    })
    void playerLoseTest(int playerScoreValue, int dealerScoreValue) {
        Score playerScore = new Score(playerScoreValue);
        Score dealerScore = new Score(dealerScoreValue);

        GameResult loseResult = GameResult.calculatePlayerResult(playerScore, dealerScore);
        GameResult expected = GameResult.LOSE;
        assertThat(loseResult).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어와 딜러의 점수가 같은 경우 무승부로 판단한다.")
    void drawTest() {
        int sameScore = 21;
        Score playerScore = new Score(sameScore);
        Score dealerScore = new Score(sameScore);

        GameResult drawResult = GameResult.calculatePlayerResult(playerScore, dealerScore);
        GameResult expected = GameResult.DRAW;
        assertThat(drawResult).isEqualTo(expected);
    }
}