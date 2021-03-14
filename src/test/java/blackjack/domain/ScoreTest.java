package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @DisplayName("동일한 점수면 동일 스코어로 본다.")
    @Test
    void create() {
        Score score = new Score(3);
        assertThat(score).isEqualTo(new Score(3));
    }

    @DisplayName("점수가 21점 미만이면 버스트하지 않는다.")
    @Test
    void bust() {
        Score score = new Score(3);
        assertThat(score.isBust()).isFalse();
        assertThat(score.isNotBust()).isTrue();
    }

    @DisplayName("점수가 21점이면 블랙잭이다.")
    @Test
    void blackjack() {
        Score score = new Score(21);
        assertThat(score.isBlackJack()).isTrue();
    }

    @DisplayName("점수에 10점을 더한다.")
    @Test
    void plusTen() {
        Score score = new Score(8);
        assertThat(score.plusTenIfNotBust()).isEqualTo(new Score(18));
    }

    @DisplayName("특정 점수보다 낮은지 확인한다.")
    @Test
    void lower() {
        Score score = new Score(8);
        assertThat(score.isLowerThan(3)).isFalse();
        assertThat(score.isLowerThan(12)).isTrue();
    }

    @DisplayName("점수를 숫자로 반환해준다.")
    @Test
    void scoreToInt() {
        assertThat(new Score(13).toInt()).isEqualTo(13);
    }

}