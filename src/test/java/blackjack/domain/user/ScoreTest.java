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

    @Test
    @DisplayName("버스트인지 확인한다.")
    void isBust() {
        Score score = new Score(22);

        boolean isBust = score.isBust();

        assertThat(isBust).isTrue();
    }

    @Test
    @DisplayName("블랙잭인지 확인한다.")
    void isBlackjack() {
        Score score = new Score(21);

        boolean isBlackjack = score.isBlackjack();

        assertThat(isBlackjack).isTrue();
    }

    @Test
    @DisplayName("딜러가 hit을 해야하는 값인지 확인한다.")
    void isDealerMustToHitScore() {
        Score score = new Score(16);

        boolean isDealerMustToHitScore = score.isDealerMustToHitScore();

        assertThat(isDealerMustToHitScore).isTrue();
    }

    @Test
    @DisplayName("현 score가 더 큰지 확인한다.")
    void isGreater() {
        Score score1 = new Score(18);
        Score score2 = new Score(16);

        boolean isGreater = score1.isGreater(score2);

        assertThat(isGreater).isTrue();
    }

    @Test
    void isSame() {
        Score score1 = new Score(16);
        Score score2 = new Score(16);

        boolean isSame = score1.isSame(score2);

        assertThat(isSame).isTrue();
    }
}
