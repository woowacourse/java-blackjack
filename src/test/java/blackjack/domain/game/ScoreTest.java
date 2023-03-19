package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("현재 점수에서 특정 점수를 뺄 때, 정확한 값을 가지는지 테스트")
    void minusTest() {
        // given
        final Score score = Score.from(21);
        final Score expected = Score.from(15);

        // when
        final Score actual = score.minus(6);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("현재 점수가 히트인지 테스트")
    void isHitTest() {
        final Score score = Score.from(20);

        assertThat(score.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러의 경우 점수가 16 이하일 때를 히트로 함")
    void isHitForDealerTest() {
        final Score score = Score.from(16);

        assertThat(score.canHit(16)).isTrue();
    }

    @Test
    @DisplayName("현재 점수가 버스트인지 확인하는 테스트")
    void isBustTest() {
        final Score score = Score.from(22);

        assertThat(score.isBust()).isTrue();
    }

    @Test
    @DisplayName("점수 비교시 초과가 나오는지 테스트")
    void isGreaterThanTest() {
        final Score originalScore = Score.from(22);
        final Score otherScore = Score.from(21);

        assertThat(originalScore.isGreaterThan(otherScore)).isTrue();
    }

    @Test
    @DisplayName("점수 비교시 같은 점수인지 판단하는 테스트")
    void isEqualsToTest() {
        final Score originalScore = Score.from(21);
        final Score otherScore = Score.from(21);

        assertThat(originalScore.isEqualTo(otherScore)).isTrue();
    }

    @Test
    @DisplayName("점수 값을 가져오는 테스트")
    void getValueTest() {
        // given
        final int value = 20;

        // when
        final Score actual = Score.from(value);

        // then
        assertThat(actual.getValue()).isEqualTo(value);
    }
}
