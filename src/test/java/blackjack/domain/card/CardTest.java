package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CardTest {

    @ParameterizedTest(name = "에이스인지 확인한다. 입력: {0}, 결과: {1}")
    @CsvSource({"ACE, true", "THREE, false"})
    void 에이스인지_확인하다(final Rank rank, final boolean result) {
        final Card card = new Card(rank, Shape.DIAMOND);

        assertThat(card.isAce()).isEqualTo(result);
    }

    @Test
    void 카드의_점수를_반환한다() {
        final Card card = new Card(Rank.THREE, Shape.DIAMOND);

        assertThat(card.score()).isEqualTo(3);
    }

    @Test
    void 카드의_심볼을_반환한다() {
        final Card card = new Card(Rank.THREE, Shape.DIAMOND);

        assertThat(card.getSymbol()).isEqualTo("3다이아몬드");
    }
}
