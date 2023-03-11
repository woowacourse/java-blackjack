package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RefereeTest {
    @DisplayName("플레이어가 딜러에게 이길 경우 플레이어의 결과는 WIN이 반환된다")
    @ParameterizedTest
    @CsvSource(value = {"10:22", "21:20", "19:17", "22:22"}, delimiter = ':')
    void judgePlayerResult_WhenPlayerWin(int playerScoreValue, int dealerScoreValue) {
        Referee referee = new Referee();
        Score playerScore = new Score(playerScoreValue);
        Score dealerScore = new Score(dealerScoreValue);
        assertThat(Referee.judgePlayerResult(playerScore, dealerScore)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어가 딜러에게 질 경우 플레이어의 결과는 LOSE가 반환된다")
    @ParameterizedTest
    @CsvSource(value = {"22:21", "22:17", "16:17", "15:20"}, delimiter = ':')
    void judgePlayerResult_WhenPlayerLose(int playerScoreValue, int dealerScoreValue) {
        Referee referee = new Referee();
        Score playerScore = new Score(playerScoreValue);
        Score dealerScore = new Score(dealerScoreValue);
        assertThat(Referee.judgePlayerResult(playerScore, dealerScore)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어가 딜러와 비길 경우 플레이어의 결과는 PUSH가 반환된다")
    @ParameterizedTest
    @CsvSource(value = {"21:21", "17:17"}, delimiter = ':')
    void judgePlayerResult_WhenPlayerPush(int playerScoreValue, int dealerScoreValue) {
        Referee referee = new Referee();
        Score playerScore = new Score(playerScoreValue);
        Score dealerScore = new Score(dealerScoreValue);
        assertThat(Referee.judgePlayerResult(playerScore, dealerScore)).isEqualTo(Result.PUSH);
    }
}
