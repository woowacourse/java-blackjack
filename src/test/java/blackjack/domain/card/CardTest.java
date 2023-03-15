package blackjack.domain.card;

import static blackjack.domain.card.CardFixture.CLOVER_ACE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class CardTest {

    private final Shape shape = Shape.CLOVER;
    private final Symbol symbol = Symbol.ACE;
    private final Card card = CLOVER_ACE;

    @Test
    void 카드의_Symbol을_반환한다() {
        assertThat(card.getSymbol())
                .isEqualTo(symbol);
    }

    @Test
    void 카드의_Shape를_반환한다() {
        assertThat(card.getShape())
                .isEqualTo(shape);
    }

    @Test
    void 카드의_점수를_반환한다() {
        assertThat(card.getScore())
                .isEqualTo(symbol.getScore());
    }

    @Test
    void 카드가_Ace인지_확인한다() {
        assertThat(card.isAce())
                .isEqualTo(symbol.isAce());
    }
}
