package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Nested
    @DisplayName("점수를 계산하는 기능 테스트들")
    class calculateScore {

        @Test
        @DisplayName("총 숫자합계와 에이스의 개수와 카드의 개수를 넣으면 넣으면 점수를 가진 Score객체를 반환하는 기능 테스트")
        void calculateScoreTest() {
            final Score score = Score.calculateScore(13, 1, 2);

            assertThat(score.getValue()).isEqualTo(13);
        }

        @Test
        @DisplayName("Ace를 포함하고, 21이 넘는 경우, Ace는 1로 계산되는 기능 테스트")
        void calculateScoreIfContainAceAndOver21Test() {

            final Score score = Score.calculateScore(24, 1, 2);

            assertThat(score.getValue()).isEqualTo(14);
        }
    }

    @Test
    @DisplayName("21을 초과하는지 확인하는 기능 테스트")
    void isBustTest() {
        final Score score = Score.calculateScore(22, 0, 2);

        assertThat(score.isBust()).isTrue();
    }
}
