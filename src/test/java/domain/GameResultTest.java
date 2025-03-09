package domain;

import static domain.participant.GameResult.DRAW;
import static domain.participant.GameResult.LOSE;
import static domain.participant.GameResult.WIN;
import static domain.participant.GameResult.calculateDealerResult;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.BlackJackRule;
import domain.participant.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {

    @ParameterizedTest
    @DisplayName("버스트로 비기는 경우")
    @CsvSource(value = {"22, 22", "23, 22", "22, 23"})
    void should_return_DRAW_when_each_burst(int dealerValue, int playerValue) {
        // when
        GameResult dealerResult = calculateDealerResult(dealerValue, playerValue);

        // then
        assertThat(dealerResult).isEqualTo(DRAW);
    }

    @ParameterizedTest
    @DisplayName("버스트로 딜러가 승리하는 경우")
    @CsvSource(value = {"10, 22", "21, 22"})
    void should_return_WIN_when_player_burst(int dealerValue, int playerValue) {
        // when
        GameResult dealerResult = calculateDealerResult(dealerValue, playerValue);

        // then
        assertThat(dealerResult).isEqualTo(WIN);
    }

    @ParameterizedTest
    @DisplayName("버스트로 딜러가 패배하는 경우")
    @CsvSource(value = {"22, 10", "22, 21"})
    void should_return_LOSE_when_dealer_burst(int dealerValue, int playerValue) {
        // when
        GameResult dealerResult = calculateDealerResult(dealerValue, playerValue);

        // then
        assertThat(dealerResult).isEqualTo(LOSE);
    }

    @ParameterizedTest
    @DisplayName("점수가 동일하여 비기는 경우")
    @CsvSource(value = {"11, 11", "15, 15", "21, 21"})
    void should_return_DRAW_when_same_value(int dealerValue, int playerValue) {
        // when
        GameResult dealerResult = calculateDealerResult(dealerValue, playerValue);

        // then
        assertThat(dealerResult).isEqualTo(DRAW);
    }

    @ParameterizedTest
    @DisplayName("딜러의 점수가 더 높아 이기는 경우")
    @CsvSource(value = {"21, 20", "10, 9"})
    void should_return_WIN_when_dealer_value_high(int dealerValue, int playerValue) {
        // when
        GameResult dealerResult = calculateDealerResult(dealerValue, playerValue);

        // then
        assertThat(dealerResult).isEqualTo(WIN);
    }

    @ParameterizedTest
    @DisplayName("딜러의 점수가 더 낮아 지는 경우")
    @CsvSource(value = {"20, 21", "9, 10"})
    void should_return_LOSE_when_player_value_high(int dealerValue, int playerValue) {
        // when
        GameResult dealerResult = calculateDealerResult(dealerValue, playerValue);

        // then
        assertThat(dealerResult).isEqualTo(LOSE);
    }

    @ParameterizedTest
    @DisplayName("반대의 결과를 계산한다")
    @CsvSource(value = {"WIN, LOSE", "DRAW, DRAW", "LOSE, WIN"})
    void should_return_reverse_result(GameResult given, GameResult expected) {
        // when
        GameResult result = given.reverse();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("버스트 값이면 true를 반환한다")
    @CsvSource(value = {"21, false", "22,true"})
    void should_return_true_when_burst(int value, boolean expected) {
        // when
        boolean result = BlackJackRule.isBurstBy(value);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
