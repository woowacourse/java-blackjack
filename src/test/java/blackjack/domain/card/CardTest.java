package blackjack.domain.card;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static blackjack.domain.fixture.FixtureCard.다이아몬드_6;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardTest {

    @Test
    void 카드는_숫자를_가진다() {
        // given
        Card card = 다이아몬드_6;

        // then
        assertThat(card.getNumber()).isEqualTo(CardNumber.SIX);
    }

    @Test
    void 카드는_모양을_가진다() {
        // given
        Card card = 다이아몬드_6;


        // when
        CardShape shape = card.getShape();

        // then
        assertThat(shape).isEqualTo(CardShape.DIAMOND);
    }
}
