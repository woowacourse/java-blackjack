package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("스코어가 21점이면 통과")
    void isMaxScoreTest() {
        // given
        Score score = new Score(21);
        // when & then
        assertThat(score.isMaxScore()).isTrue();
    }

    @Test
    @DisplayName("스코어가 21점이 아니면 실패")
    void isNotMaxScoreTest() {
        // given
        Score score1 = new Score(22);
        Score score2 = new Score(20);
        // when & then
        assertThat(score1.isMaxScore()).isFalse();
        assertThat(score2.isMaxScore()).isFalse();
    }

    @Test
    @DisplayName("딜러의 점수가 16점 이하이면 히트할 수 있다.")
    void isDealerHitScoreTest() {
        // give & when & then
        assertThat(new Score(16).isDealerHitScore()).isTrue();
        assertThat(new Score(17).isDealerHitScore()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 점수가 21점 이하이면 히트할 수 있다.")
    void isPlayerHitScoreTest() {
        // give & when & then
        assertThat(new Score(21).isPlayerHitScore()).isTrue();
        assertThat(new Score(22).isPlayerHitScore()).isFalse();
    }

    @Test
    @DisplayName("두 스코어의 값이 같으면 True를 반환한다.")
    void isSameTest() {
        // give
        Score score1 = new Score(20);
        Score score2 = new Score(20);
        // when & then
        assertThat(score1.isSame(score2)).isTrue();
    }

    @Test
    @DisplayName("내 스코어가 상대보다 작으면 True를 반환한다.")
    void isLessTest() {
        // given
        Score smallScore = new Score(19);
        Score largeScore = new Score(20);
        Score sameScore = new Score(20);
        // when & then
        assertThat(smallScore.isLess(largeScore)).isTrue();
        assertThat(largeScore.isLess(sameScore)).isFalse();
    }

    @Test
    @DisplayName("점수 값이 같으면 두 객체는 동등하다.")
    void equalsTest() {
        // given
        Score score1 = new Score(21);
        Score score2 = new Score(21);
        // when & then
        assertThat(score1).isEqualTo(score2);
        assertThat(score1.hashCode()).isEqualTo(score2.hashCode());
    }

    @Test
    @DisplayName("점수 값이 다르면 두 객체는 동등하지 않다.")
    void notEqualsTest() {
        // given
        Score score1 = new Score(21);
        Score score2 = new Score(10);
        // when & then
        assertThat(score1).isNotEqualTo(score2);
    }
}
