package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
