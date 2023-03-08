package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ScoreTest {

    private Score score;

    @BeforeEach
    void init() {
        score = Score.create(10);
    }

    @Test
    @DisplayName("create()는 호출하면, Score를 생성한다")
    void create_whenCall_thenSuccess() {
        final Score score = assertDoesNotThrow(() -> Score.create(10));
        assertThat(score)
                .isExactlyInstanceOf(Score.class);
    }

    @Test
    @DisplayName("add()는 호출하면, 인자로 넘긴 점수와 더한 값을 가진 Score를 반환한다.")
    void add_givenScore_thenReturnAddedScore() {
        // given
        Score otherScore = Score.create(5);
        Score expected = Score.create(15);

        // when
        Score actual = score.add(otherScore);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("subtract()는 호출하면, 인자로 넘긴 점수와 뺀 값을 가진 Score를 반환한다.")
    void subtract_givenScore_thenReturnSubtractedScore() {
        // given
        Score otherScore = Score.create(3);
        Score expected = Score.create(7);

        // when
        Score actual = score.subtract(otherScore);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "isGreaterThan()는 호출하면, 인자로 넘긴 점수와 비교해 더 큰지 판단한다.")
    @CsvSource(value = {"3:true", "10:false", "12:false"}, delimiter = ':')
    void isGreaterThan_givenScore_thenReturnCompareResult(final int scoreValue, final boolean expected) {
        // given
        Score otherScore = Score.create(scoreValue);

        // when
        boolean actual = score.isGreaterThan(otherScore);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "isGreaterThanAndEqual()는 호출하면, 인자로 넘긴 점수와 비교해 크거나 같은지 판단한다.")
    @CsvSource(value = {"3:true", "10:true", "12:false"}, delimiter = ':')
    void isGreaterThanAndEqual_givenScore_thenReturnCompareResult(final int scoreValue, final boolean expected) {
        // given
        Score otherScore = Score.create(scoreValue);

        // when
        boolean actual = score.isGreaterThanAndEqual(otherScore);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }
}
