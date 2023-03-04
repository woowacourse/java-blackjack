package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @ParameterizedTest
    @CsvSource(value = {"22:true", "30:true", "21:false"}, delimiter = ':')
    @DisplayName("21보다 높은 값을 가지면 isBusted가 true가 된다.")
    void scoreBustedTest(int value, boolean isBusted) {
        //given
        Score score = Score.from(value);

        //then
        assertThat(score.isBusted()).isEqualTo(isBusted);
    }

}
