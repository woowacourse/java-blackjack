package domain;

import domain.player.Status;
import domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @ParameterizedTest
    @CsvSource(value = {"1:2:true", "2:2:true", "3:2:false"}, delimiter = ':')
    @DisplayName("더 큰 Score와 비교하면 false를 반환한다")
    void compareValueTest(int origin, int compared, boolean expected) {
        //given
        Score score = Score.from(origin);
        //when
        boolean smallerOrEqual = score.isSmallerOrEqual(compared);
        //then
        assertThat(smallerOrEqual).isEqualTo(expected);
    }

    @Test
    @DisplayName("score는 같은 value를 다시 호출하면 같은 객체를 리턴한다")
    void cacheScoreTest() {
        Score from = Score.from(20);
        Score from1 = Score.from(20);

        assertThat(from).isSameAs(from1);
    }

    @Test
    @DisplayName("value가 같으면 비긴다.")
    void drawTest() {
        //given
        Score score1 = Score.from(10);
        Score score2 = Score.from(10);
        //when
        Status status = score1.compareScore(score2);
        //then
        assertThat(status).isEqualTo(Status.DRAW);
    }

}
