package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 11})
    @DisplayName("현재 점수와 10을 더했을 때 21점을 넘지 않으면 더한 값을 반환한다.")
    void reCalculateIfSoftHand_plus10(int currentScore) {
        // given
        Score current = new Score(currentScore);

        // when
        Score after = current.plusIfSoftHand();

        // then
        assertThat(after).isEqualTo(new Score(currentScore + 10));
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 13})
    @DisplayName("현재 점수와 10을 더했을 때 21점을 넘으면 이전 값을 반환한다.")
    void reCalculateIfSoftHand(int currentScore) {
        // given
        Score current = new Score(currentScore);

        // when
        Score after = current.plusIfSoftHand();

        // then
        assertThat(after).isEqualTo(current);
    }

    @Test
    @DisplayName("현재 점수가 버스트이면 true를 반환한다.")
    void isBust_true() {
        // given
        Score current = new Score(22);

        // expect
        assertThat(current.isBust()).isTrue();
    }

    @Test
    @DisplayName("현재 점수가 버스트이면 false를 반환한다.")
    void isBust_false() {
        // given
        Score current = new Score(21);

        // expect
        assertThat(current.isBust()).isFalse();
    }

    @Test
    @DisplayName("현재 점수가 작거나 같으면 true를 반환한다.")
    void isLessOrEquals_true() {
        // given
        Score current = new Score(10);
        Score other1 = new Score(10);
        Score other2 = new Score(11);

        // expect
        Assertions.assertAll(
                () -> assertThat(current.canDraw(other1)).isTrue(),
                () -> assertThat(current.canDraw(other2)).isTrue()
        );
    }

    @Test
    @DisplayName("현재 점수가 크면 false를 반환한다.")
    void isLessOrEquals_false() {
        // given
        Score current = new Score(11);
        Score other = new Score(10);

        // expect
        assertThat(current.canDraw(other)).isFalse();
    }

    @Test
    @DisplayName("현재 점수가 작으면 true를 반환한다.")
    void isLess_true() {
        // given
        Score current = new Score(10);
        Score other = new Score(11);

        // expect
        assertThat(current.isLose(other)).isTrue();
    }

    @Test
    @DisplayName("현재 점수가 크면 false를 반환한다.")
    void isLess_false() {
        // given
        Score current = new Score(11);
        Score other = new Score(10);

        // expect
        assertThat(current.isLose(other)).isFalse();
    }

    @Test
    @DisplayName("현재 점수가 같으면 true를 반환한다.")
    void isEquals_true() {
        // given
        Score current = new Score(11);
        Score other = new Score(11);

        // expect
        assertThat(current.isDraw(other)).isTrue();
    }

    @Test
    @DisplayName("현재 점수가 같지 않으면 false를 반환한다.")
    void isEquals_false() {
        // given
        Score current = new Score(11);
        Score other = new Score(10);

        // expect
        assertThat(current.isLose(other)).isFalse();
    }
}
