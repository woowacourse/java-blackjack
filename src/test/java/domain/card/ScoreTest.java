package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("Score 생성")
    void create() {
        assertThat(new Score(0)).isEqualTo(Score.ZERO);
    }

    @Test
    @DisplayName("Score 점수 덧셈")
    void add() {
        assertThat(Score.ZERO.add(10)).isEqualTo(new Score(10));
        assertThat(new Score(2).add(3)).isEqualTo(new Score(5));
        assertThat(new Score(2).add(3).add(5)).isEqualTo(new Score(10));
    }

    @Test
    @DisplayName("Ace를 1,11로 판별")
    void addAceBonusIfNotBust() {
        assertThat(new Score(11).addAceBonusIfNotBust()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("해당 스코어가 버스트가 아닌지 확인")
    void isNotBust() {
        assertThat(new Score(21).isNotBust()).isTrue();
    }
}
