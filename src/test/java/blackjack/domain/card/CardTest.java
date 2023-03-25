package blackjack.domain.card;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
class CardTest {

    private final Shape shape = Shape.CLOVER;
    private final Denomination symbol = Denomination.ACE;
    private final Card card = Card.from(shape, symbol);

    @Test
    void 카드의_Symbol을_반환한다() {
        assertThat(card.getDenomination())
                .isEqualTo(symbol);
    }

    @Test
    void 카드의_Shape를_반환한다() {
        assertThat(card.getShape())
                .isEqualTo(shape);
    }
}
