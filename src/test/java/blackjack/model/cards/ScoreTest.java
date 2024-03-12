package blackjack.model.cards;

import static org.assertj.core.api.Assertions.assertThat;

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
}
