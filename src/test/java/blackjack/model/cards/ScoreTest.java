package blackjack.model.cards;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.blackjackgame.PlayerProfitCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {
    @DisplayName("점수를 더한다.")
    @Test
    void add() {
        Score score = new Score(1);
        Score otherScore = new Score(3);
        Score result = score.add(otherScore);

        assertThat(result).isEqualTo(new Score(4));
    }

    @DisplayName("블랙잭 여부를 판단한다.")
    @ParameterizedTest
    @CsvSource(value = {"19,false", "20,false", "21,true", "22,false", "23,false"})
    void isBlackJack(int given, boolean expected) {
        Score score = new Score(given);
        boolean result = score.isBlackJack();

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("버스트 여부를 판단한다.")
    @ParameterizedTest
    @CsvSource(value = {"20,false", "21,false", "22,true"})
    void isBusted(int given, boolean expected) {
        Score score = new Score(given);
        boolean result = score.isBusted();

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("점수가 큰지 판단한다.")
    @Test
    void isGreaterThan() {
        Score score = new Score(1);
        Score otherScore = new Score(3);
        boolean result = score.isGreaterThan(otherScore);

        assertThat(result).isEqualTo(false);
    }

    @DisplayName("추가 점수를 합한 값이 블랙잭 점수보다 같거나 낮으면 추가 점수를 얻는다.")
    @ParameterizedTest
    @CsvSource(value = {"10,20", "11,21", "12,12", "13,13"})
    void getScoreWhenHasAce(int given, int expected) {
        Score score = new Score(given);
        Score result = score.getScoreWhenHasAce();

        assertThat(result).isEqualTo(new Score(expected));
    }

    @DisplayName("비교 대상이 버스트가 나면 플레이어는 진다.")
    @ParameterizedTest
    @CsvSource(value = {"22,20", "23,3", "24,24"})
    void getStatusBusted(int given, int other) {
        Score score = new Score(given);
        Score otherScore = new Score(other);

        PlayerProfitCalculator result = score.getPlayerStatus(otherScore);
        assertThat(result).isEqualTo(PlayerProfitCalculator.LOSE);
    }

    @DisplayName("비교하려는 대상이 버스트면 플레이어는 이긴다.")
    @ParameterizedTest
    @CsvSource(value = {"3,22", "5,25", "21,33"})
    void getStatusOtherBusted(int given, int other) {
        Score score = new Score(given);
        Score otherScore = new Score(other);

        PlayerProfitCalculator result = score.getPlayerStatus(otherScore);
        assertThat(result).isEqualTo(PlayerProfitCalculator.WIN);
    }

    @DisplayName("두 점수 모두 버스트가 아니라면 대소 관계로 상태를 결정한다.")
    @ParameterizedTest
    @CsvSource(value = {"3,4,LOSE", "10,20,LOSE", "5,2,WIN", "10,5,WIN", "10,10,PUSH", "15,15,PUSH"})
    void getStatusCompare(int given, int other, PlayerProfitCalculator expected) {
        Score score = new Score(given);
        Score otherScore = new Score(other);

        PlayerProfitCalculator result = score.getPlayerStatus(otherScore);
        assertThat(result).isEqualTo(expected);
    }
}
