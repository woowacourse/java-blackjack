package blackjack.domain.rule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @DisplayName("점수끼리 더한다.")
    @Test
    void testAdd() {
        // given
        Score score = new Score(1);

        // when
        Score actual = score.add(new Score(2));

        // then
        assertThat(actual).extracting("value").isEqualTo(3);
    }

    @DisplayName("주어진 한계값 이하이면 히트가 가능하다.")
    @Test
    void testCanHit() {
        // given
        Score score = new Score(2);

        // when
        boolean actual = score.canHit(new Score(2));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("주어진 한계값 초과이면 히트가 불가능하다.")
    @Test
    void testCanNotHit() {
        // given
        Score score = new Score(3);

        // when
        boolean actual = score.canHit(new Score(2));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("블랙잭을 초과하면 버스트다.")
    @Test
    void testIsBust() {
        // given
        Score score = new Score(22);

        // when
        boolean actual = score.isBust();

        // when & then
        assertThat(actual).isTrue();
    }

    @DisplayName("블랙잭을 초과하지 않으면 버스트가 아니다.")
    @Test
    void testIsNotBust() {
        // given
        Score score = new Score(21);

        // when
        boolean actual = score.isBust();

        // when & then
        assertThat(actual).isFalse();
    }

    @DisplayName("주어진 점수보다 큼을 확인한다.")
    @Test
    void testisBiggerThan() {
        // given
        Score score = new Score(2);

        // when
        boolean actual = score.isBiggerThan(new Score(1));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("주어진 점수보다 작음을 확인한다.")
    @Test
    void testIsSmallerThan() {
        // given
        Score score = new Score(2);

        // when
        boolean actual = score.isBiggerThan(new Score(3));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("점수가 21을 초과하지 않으면 Ace는 11로 계산한다.")
    @Test
    void testAdjustAceScore() {
        // given
        Score score = new Score(11);

        // when
        Score actual = score.adjustAceScore();

        // then
        assertThat(actual).extracting("value").isEqualTo(21);
    }

    @DisplayName("점수가 21을 초과하면 Ace를 11로 계산하지 않는다.")
    @Test
    void testNotAdjustAceScore() {
        // given
        Score score = new Score(12);

        // when
        Score actual = score.adjustAceScore();

        // then
        assertThat(actual).extracting("value").isEqualTo(12);
    }
}
