package blackJack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("카드 점수 동일 여부 테스트")
    void add() {
        Score score = new Score(11);
        assertThat(new Score(11)).isEqualTo(new Score(11));
    }

    @Test
    @DisplayName("블랙잭 판단 확인 테스트")
    void isBlackJack() {
        Score score = new Score(21);

        assertThat(score.isBlackJack(2)).isTrue();
    }

    @Test
    @DisplayName("버스트 판단 확인 테스트")
    void isBust() {
        Score score = new Score(23);

        assertThat(score.isBust()).isTrue();
    }
}
