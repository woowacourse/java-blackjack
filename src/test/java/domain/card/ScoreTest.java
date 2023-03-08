package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

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
    @DisplayName("getScore를 호출하면 자기 자신을 반환한다.")
    void getScoreTest() {
        //given
        final Score score = Score.from(21);

        //when
        final Score newScore = score.getScore();

        //then
        assertThat(score).isEqualTo(newScore);
    }

    @Test
    @DisplayName("getScoreWithBonusScore를 호출하면 10을 더해준 스코어를 반환한다.")
    void getScoreWithBonusScoreTest() {
        //given
        final Score score = Score.from(11);
        final Score expectResult = Score.from(21);

        //when
        final Score scoreWithBonusScore = score.getScoreWithBonusScore();

        //then
        assertThat(scoreWithBonusScore).isEqualTo(expectResult);
    }
}
