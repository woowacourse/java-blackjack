package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("count 객체 생성 후 값 테스트")
    void create() {
        Score score = new Score(1);

        int scoreValue = score.getValue();

        assertThat(scoreValue).isEqualTo(1);
    }

    @Test
    @DisplayName("count가 bust 값인지 확인하여 10을 더해주는지 확인한다.")
    void decideScoreByStatus() {
        Score score = new Score(20);

        Score scoreResult = score.decideScoreByStatus();
        int value = scoreResult.getValue();

        assertThat(value).isEqualTo(20);
    }
}
