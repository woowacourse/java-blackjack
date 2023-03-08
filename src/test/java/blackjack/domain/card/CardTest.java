package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardTest {

    @Test
    void 카드는_숫자를_가진다() {
        // given
        final CardNumber number = CardNumber.SIX;
        Card card = new Card(number, CardShape.DIAMOND);

        // then
        assertThat(card.getNumber()).isEqualTo(number);
    }

    @Test
    void 카드는_모양을_가진다() {
        // given
        Card card = new Card(CardNumber.SIX, CardShape.DIAMOND);

        // when
        CardShape shape = card.getShape();

        // then
        assertThat(shape).isEqualTo(CardShape.DIAMOND);
    }
}
