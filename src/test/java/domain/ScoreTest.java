package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
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

    @Test
    @DisplayName("Score가 더 높으면 WIN을 반환한다")
    void compareScoreReturnWinTest() {
        //given
        Score score = Score.from(20);
        //when
        Score opponent = Score.from(19);
        Status status = score.compareScore(opponent);
        //then
        assertThat(status).isEqualTo(Status.WIN);
    }

    @Test
    @DisplayName("Score가 더 낮으면 Lose을 반환한다")
    void compareScoreReturnLoseTest() {
        //given
        Score score = Score.from(10);
        //when
        Score opponent = Score.from(19);
        Status status = score.compareScore(opponent);
        //then
        assertThat(status).isEqualTo(Status.LOSE);
    }

}
