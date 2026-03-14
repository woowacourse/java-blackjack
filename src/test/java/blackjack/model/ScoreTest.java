package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("21이 넘으면 버스트 처리한다.")
    void isBustTest() {
        // given
        Score score = new Score(22);
        // when & then
        assertThat(score.isBust()).isTrue();
    }

    @Test
    @DisplayName("21이 넘지 않으면 버스트 처리하지 않는다.")
    void isBustTest2() {
        // given
        Score score = new Score(21);
        // when & then
        assertThat(score.isBust()).isFalse();
    }

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
}
