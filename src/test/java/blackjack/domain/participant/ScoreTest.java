package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {
    @DisplayName("생성테스트")
    @Test
    void create() {
        assertThatCode(() -> new Score(0))
                .doesNotThrowAnyException();
    }

    @DisplayName("값으로 음수를 가질 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -100, -21})
    void cannotCreateByNegative() {
        assertThatThrownBy(()-> new Score(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("점수를 반환할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 10})
    void getValue(int value) {
        //given
        Score score = new Score(value);
        //when
        int actual = score.getValue();
        //then
        assertThat(actual).isEqualTo(value);
    }

    @DisplayName("다른 객체와 대소비교 하는 기능")
    @Nested
    class IsGreaterThan {
        @DisplayName("값이 더 크면 true를 반환한다.")
        @Test
        void returnTrue() {
            Score one = new Score(1);
            Score two = new Score(2);
            assertThat(two.isHigherThan(one)).isTrue();
        }

        @DisplayName("값이 더 작으면 false를 반환한다.")
        @Test
        void returnFalseWhenValueIsLessThan() {
            Score three = new Score(3);
            Score two = new Score(2);
            assertThat(two.isHigherThan(three)).isFalse();
        }

        @DisplayName("값이 같으면 false를 반환한다.")
        @Test
        void returnFalseWhenValueEquals() {
            Score one = new Score(2);
            Score two = new Score(2);
            assertThat(two.isHigherThan(one)).isFalse();
        }
    }

    @DisplayName("같은 값을 가진 객체는 같다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 21})
    void equals() {
        //given
        Score score1 = new Score(1);
        Score score2 = new Score(1);
        //when
        //then
        assertThat(score2).isEqualTo(score1);
    }

    @DisplayName("정수 인자들로 생성될 수 있다.")
    @Test
    void createByIntList() {
        //given
        //when
        //then
        assertDoesNotThrow(() -> Score.of(1, 2, 3, 4, 5));
    }

    @DisplayName("합이 21이면 블랙잭이다")
    @Test
    void isBlackjack() {
        Score score = Score.of(10, 11);
        assertThat(score.isBlackjack()).isTrue();
    }

    @DisplayName("합이 21이 아니면 블랙잭이 아니다.")
    @Test
    void isNotBlackjack() {
        Score score = Score.of(10, 10);
        assertThat(score.isBlackjack()).isFalse();
    }

    @DisplayName("합이 21이 넘을 경우 버스트이다.")
    @Test
    void isBust() {
        Score score = Score.of(22);
        assertThat(score.isBust()).isTrue();
    }

    @DisplayName("합이 21이 넘지 않으면 버스트가 아니다.")
    @Test
    void isNotBust() {
        Score score = Score.of(20);
        assertThat(score.isBust()).isFalse();
    }

    @DisplayName("에이스를 가졌을 때")
    @Nested
    class GetValueWithBonus {
        @DisplayName("합한 점수가 21점 이하일 경우 빼지 않는다.")
        @Test
        void addTen() {
            Score score = Score.of(11,4, 5);
            assertThat(score.getValueIncludingAce().getValue()).isEqualTo(20);
        }

        @DisplayName("합한 점수가 21점 초과일 경우 10을 뺀다.")
        @Test
        void dontAdd() {
            Score score = Score.of(11, 10, 2);
            assertThat(score.getValueIncludingAce().getValue()).isEqualTo(13);
        }
    }
}