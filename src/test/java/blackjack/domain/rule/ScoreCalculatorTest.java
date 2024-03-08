package blackjack.domain.rule;

import blackjack.domain.player.Hand;
import fixture.HandFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("점수 계산 전략 테스트")
class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator;

    @BeforeEach
    void setUp() {
        scoreCalculator = new ScoreCalculator();
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드 없음")
    @Test
    void testCalculateScoreWithNoAce() {
        Hand hand = HandFixture.of(2, 3, 4, 5);
        assertThat(scoreCalculator.calculate(hand).getValue()).isEqualTo(14);
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드가 11로 이용됨")
    @Test
    void testCalculateScoreWithBigAce() {
        Hand hand = HandFixture.of(1, 10);
        assertThat(scoreCalculator.calculate(hand).getValue()).isEqualTo(21);
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드가 1로 이용됨")
    @Test
    void testCalculateScoreWithLowAce() {
        Hand hand = HandFixture.of(1, 10, 2);
        assertThat(scoreCalculator.calculate(hand).getValue()).isEqualTo(13);
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드 두개 이상")
    @Test
    void testCalculateScoreWithMultipleAce() {
        Hand hand = HandFixture.of(1, 1, 1);
        assertThat(scoreCalculator.calculate(hand).getValue()).isEqualTo(13);
    }
}