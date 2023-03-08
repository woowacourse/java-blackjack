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
}