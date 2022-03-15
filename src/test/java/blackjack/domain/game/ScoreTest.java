package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @DisplayName("정적 팩토리 메소드 valueOf 로 새로운 점수 인스턴스를 생성한다.")
    @Test
    void valueOf_createNewScore() {
        Score score = Score.valueOf(1);

        assertThat(score).isNotNull();
    }

    @DisplayName("정적 팩토리 메소드 valueOf 는 캐싱된 점수 인스턴스를 가져온다.")
    @Test
    void valueOf_getCache() {
        Score score = Score.valueOf(10);
        Score sameScore = Score.valueOf(10);

        assertThat(score).isEqualTo(sameScore);
    }

    @DisplayName("인스턴스 메서드 add 는 다른 Score 인스턴스를 받아 더한 값의 Score 인스턴스를 반환한다.")
    @Test
    void add() {
        Score score = Score.valueOf(10);
        Score anotherScore = Score.valueOf(15);

        Score newScore = score.add(anotherScore);

        assertThat(newScore.getValue()).isEqualTo(25);
    }

    @DisplayName("compareTo 는 각 점수 인스턴스의 크기를 비교한다.")
    @Nested
    class CompareToTest {
        @DisplayName("기준 인스턴스의 value 값이 비교 대상보다 더 클 경우 양수를 반환한다.")
        @Test
        void compareTo_returnPositiveIfBiggerThanTarget() {
            Score score = Score.valueOf(15);
            Score smallerScore = Score.valueOf(10);

            int actual = score.compareTo(smallerScore);

            assertThat(actual).isPositive();
        }

        @DisplayName("기준 인스턴스의 value 값이 비교 대상과 동일한 경우 0을 반환한다.")
        @Test
        void compareTo_returnZeroIfSameAsTarget() {
            Score score = Score.valueOf(10);
            Score sameScore = Score.valueOf(10);

            int actual = score.compareTo(sameScore);

            assertThat(actual).isZero();
        }

        @DisplayName("기준 인스턴스의 value 값이 비교 대상보다 더 작을 경우 음수를 반환한다.")
        @Test
        void compareTo_returnNegativeIfSmallerThanTarget() {
            Score score = Score.valueOf(15);
            Score biggerScore = Score.valueOf(20);

            int actual = score.compareTo(biggerScore);

            assertThat(actual).isNegative();
        }
    }
}
