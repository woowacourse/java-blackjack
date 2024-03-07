package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatchResultTest {

    @ParameterizedTest
    @CsvSource(value = {"20, 22", "20, 19", "21, 20"})
    @DisplayName("플레이어가 이기는 경우를 올바르게 판단한다.")
    void playerWinningTest(int playerScore, int dealerScore) {
        // when
        MatchResult result = MatchResult.chooseWinner(playerScore, dealerScore);
        // then
        assertThat(result).isEqualTo(MatchResult.PLAYER_WIN);
    }

    @ParameterizedTest
    @CsvSource(value = {"22, 20", "22, 22", "20, 21", "14, 18"})
    @DisplayName("딜러가 이기는 경우를 올바르게 판단한다.")
    void dealerWinningTest(int playerScore, int dealerScore) {
        // when
        MatchResult result = MatchResult.chooseWinner(playerScore, dealerScore);
        // then
        assertThat(result).isEqualTo(MatchResult.DEALER_WIN);
    }

    @ParameterizedTest
    @CsvSource(value = {"21, 21", "20, 20"})
    @DisplayName("무승부인 경를 올바르게 판단한다.")
    void tieTest(int playerScore, int dealerScore) {
        // when
        MatchResult result = MatchResult.chooseWinner(playerScore, dealerScore);
        // then
        assertThat(result).isEqualTo(MatchResult.TIE);
    }
}