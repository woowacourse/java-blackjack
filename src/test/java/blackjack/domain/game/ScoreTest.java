package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScoreTest {

    @Test
    void 점수는_값을_받아_생성된다() {
        //given
        final int score = 20;

        //then
        assertDoesNotThrow(() -> {
            new Score(score);
        });
    }

    @Nested
    class 점수는_다른_점수를_받아 {
        @Test
        void 뺄_수_있다() {
            //given
            Score score1 = new Score(10);
            Score score2 = new Score(20);

            //when
            Score sum = score1.minus(score2);

            //then
            assertThat(sum).isEqualTo(new Score(-10));
        }
    }

    @Test
    void 점수는_BUST_이면_10을_뺼_수_있다() {
        //given
        Score score = new Score(22);

        //when
        Score result = score.minusIfBust();

        //then
        assertThat(result).isEqualTo(new Score(12));
    }

    @Test
    void 점수는_다른_점수보다_큰지_검사할_수_있다() {
        //given
        Score score1 = new Score(22);
        Score score2 = new Score(10);

        //when
        final boolean result = score1.isMoreThen(score2);
        //then
        assertThat(result).isEqualTo(true);
    }
}
