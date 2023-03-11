package domain.card;

import domain.player.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("스코어에 더할 값을 입력해주면 총합의 스코어를 반환한다")
    void addScore_returnNewScore() {
        //given
        final Score score = Score.from(0);

        //when
        final Score newScore = score.add(11);

        //then
        assertThat(newScore).isEqualTo(Score.from(0 + 11));
    }

    @Test
    @DisplayName("스코어가 22 미만이면 true를 반환한다")
    void givenOverBustNumber_thenReturnFalse() {
        //given
        final Score score = Score.from(22);

        //when
        final boolean result = score.isUnderMaxScore();
        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("스코어가 22 이상이면 false를 반환한다")
    void givenUnderBustNumber_thenReturnTrue() {
        //given
        final Score score = Score.from(21);

        //when
        final boolean result = score.isUnderMaxScore();
        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("스코어가 12 이상이면 false를 반환한다")
    void givenOverTwelve_thenReturnFalse() {
        //given
        final Score score = Score.from(12);

        //when
        final boolean result = score.canAddBonusScore();

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("스코어 객체를 입력하면 두 스코어의 차를 구해준다.")
    void givenScore_thenReturnScoreDiffInt() {
        //given
        final Score score = Score.from(21);
        final Score diffScore = Score.from(10);

        //when
        final int scoreGap = score.scoreGap(diffScore);

        //then
        assertThat(scoreGap).isEqualTo(21 - 10);
    }

    @Test
    @DisplayName("스코어가 11 이하면 true를 반환한다")
    void givenUnderEleven_thenReturnTrue() {
        //given
        final Score score = Score.from(11);

        //when
        final boolean result = score.canAddBonusScore();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("getScore를 호출하면 점수를 반환한다.")
    void getScoreTest() {
        //given
        final Score score = Score.from(21);

        //when
        final int newScore = score.getScore();

        //then
        assertThat(newScore).isEqualTo(21);
    }

    @Test
    @DisplayName("getScoreWithBonusScore를 호출하면 10을 더해준 점수를 반환한다.")
    void getScoreWithBonusScoreTest() {
        //given
        final Score score = Score.from(11);

        //when
        final int scoreWithBonusScore = score.getScoreWithBonusScore();

        //then
        assertThat(scoreWithBonusScore).isEqualTo(11 + 10);
    }
}
