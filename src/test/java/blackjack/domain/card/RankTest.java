package blackjack.domain.card;

import static blackjack.domain.card.Rank.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class RankTest {

    @ParameterizedTest(name = "에이스인지 확인한다. 입력: {0}, 결과: {1}")
    @CsvSource({"ACE, true", "JACK, false"})
    void 에이스인지_확인한다(final Rank rank, final boolean result) {
        assertThat(rank.isAce()).isEqualTo(result);
    }

    @Test
    void 심볼을_반환한다() {
        assertThat(Rank.ACE.getSymbol()).isEqualTo("A");
    }

    @Test
    void 점수를_반환한다() {
        assertThat(FOUR.score()).isEqualTo(4);
    }
}
