package domain.deck;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @DisplayName("랭크가 에이스면 true 아니면 false를 반환한다")
    @ParameterizedTest
    @CsvSource(value = {
            "ACE, true", "THREE, false", "JACK, false"
    })
    void 랭크가_예이스면_true_아니면_false를_반환한다(final Rank rank, final boolean expected) {
        // when & then
        assertThat(rank.isAce()).isEqualTo(expected);
    }
}
