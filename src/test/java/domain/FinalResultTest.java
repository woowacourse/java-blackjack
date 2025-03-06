package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FinalResultTest {

    @DisplayName("카드 합에 따른 승패 결과 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "15, 14, WIN", "15, 17, LOSE", "15, 15, DRAW"
    })
    void 카드_합에_따른_승패_결과_반환한다(int sumOfRank, int otherSumOfRank, FinalResult expected) {

        // given
        final FinalResult finalResult = FinalResult.findBySumOfRank(sumOfRank, otherSumOfRank);

        // when & then
        assertThat(finalResult).isEqualTo(expected);
    }
}
