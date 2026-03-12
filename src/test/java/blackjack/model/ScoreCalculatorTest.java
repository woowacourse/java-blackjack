package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {

    private static final BustPolicy NEVER_BUST_POLICY = score -> false;
    private static final BustPolicy ALWAYS_BUST_POLICY = score -> true;
    private static final int ACE_ADJUST_AMOUNT = 10;

    @Test
    void ace가_있고_버스트가_아닌_경우_ace를_11로_조정한_총_점수를_반환한다() {
        // given
        ScoreCalculator scoreCalculator = new ScoreCalculator(NEVER_BUST_POLICY);
        int sum = 10;
        boolean hasAce = true;
        // when
        Score adjustedScore = scoreCalculator.calculateOptimalTotalOf(sum, hasAce);
        // then
        assertThat(adjustedScore.value()).isEqualTo(sum + ACE_ADJUST_AMOUNT);
    }

    @Test
    void ace가_있고_버스트인_경우_ace를_1로_조정한_총_점수를_반환한다() {
        // given
        ScoreCalculator scoreCalculator = new ScoreCalculator(ALWAYS_BUST_POLICY);
        int sum = 10;
        boolean hasAce = true;
        // when
        Score adjustedScore = scoreCalculator.calculateOptimalTotalOf(sum, hasAce);
        // then
        assertThat(adjustedScore.value()).isEqualTo(sum);
    }

    @Test
    void ace가_없는_경우_점수를_그대로_반환한다() {
        // given
        ScoreCalculator scoreCalculator = new ScoreCalculator(NEVER_BUST_POLICY);
        int sum = 10;
        boolean hasAce = false;
        // when
        Score adjustedScore = scoreCalculator.calculateOptimalTotalOf(sum, hasAce);
        // then
        assertThat(adjustedScore.value()).isEqualTo(sum);
    }
}
