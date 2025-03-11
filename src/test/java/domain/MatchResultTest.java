package domain;

import domain.stats.MatchResult;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class MatchResultTest {

    @Test
    void 두_플레이어의_카드_합을_받아_승패_결과를_반환한다() {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(MatchResult.compareBySum(20, 10))
                    .isEqualTo(MatchResult.WIN);
            softAssertions.assertThat(MatchResult.compareBySum(10, 20))
                    .isEqualTo(MatchResult.LOSE);
            softAssertions.assertThat(MatchResult.compareBySum(15, 15))
                    .isEqualTo(MatchResult.DRAW);
        });
    }

    @Test
    void 승패_결과를_받아_반전한_승패_결과를_반환한다() {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(MatchResult.inverse(MatchResult.WIN))
                    .isEqualTo(MatchResult.LOSE);
            softAssertions.assertThat(MatchResult.inverse(MatchResult.LOSE))
                    .isEqualTo(MatchResult.WIN);
            softAssertions.assertThat(MatchResult.inverse(MatchResult.DRAW))
                    .isEqualTo(MatchResult.DRAW);
        });
    }
}