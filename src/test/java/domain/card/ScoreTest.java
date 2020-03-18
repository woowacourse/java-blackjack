package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("Score 생성")
    void create() {
        assertThat(new Score(0)).isEqualTo(Score.ZERO);
    }

    @ParameterizedTest
    @DisplayName("Score 점수 덧셈")
    @CsvSource(value = {"0,10:10", "2,3:5", "2,3,5:10"}, delimiter = ':')
    void add(String input, int expected) {
        Score result = new Score(0);
        String[] numbers = input.split(",");

        for (String number : numbers) {
            result = result.add(Integer.parseInt(number));
        }
        assertThat(result.getValue()).isEqualTo(new Score(expected).getValue());
    }

    @Test
    @DisplayName("Ace를 1,11로 판별")
    void addAceBonusIfNotBust_IfPointNotOver21_AddPoint10() {
        assertThat(new Score(11).addAceBonusIfNotBust()).isEqualTo(new Score(21));
    }

    @ParameterizedTest
    @DisplayName("해당 스코어가 버스트가 아닌지 확인")
    @CsvSource(value = {"21:True", "22:False"}, delimiter = ':')
    void isNotBust_IfPointOver21_IsTrue(int input, Boolean expected) {
        assertThat(new Score(input).isNotBust()).isEqualTo(expected);
    }
}
