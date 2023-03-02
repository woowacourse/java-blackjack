package domain.player;

import domain.area.CardArea;
import domain.card.Card;
import domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static domain.card.CardValue.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Dealer 은")
class DealerTest {

    private final CardArea cardArea = new CardArea(
            new Card(CardShape.CLOVER, TEN),
            new Card(CardShape.CLOVER, SEVEN)
    );

    @Test
    void 딜러는_16이하면_카드를_더_받을_수_있다() {
        // given
        final CardArea cardArea = new CardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SIX)
        );

        final Player dealer = new Dealer(cardArea);

        // when & then
        assertTrue(dealer.canHit());
    }

    @Test
    void 딜러는_16초과면_카드를_더_받을_수_없다() {
        // given
        final CardArea cardArea = new CardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SEVEN)
        );

        final Player dealer = new Dealer(cardArea);

        // when & then
        assertFalse(dealer.canHit());
    }

    @Test
    void 딜러는_첫_장만_보여줄_수_있다() {
        // given
        final CardArea cardArea = new CardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SEVEN)
        );

        final Dealer dealer = new Dealer(cardArea);

        // then
        assertEquals(dealer.firstCard(), new Card(CardShape.CLOVER, TEN));
    }

    @Test
    void 딜러의_이름은_항상_딜러이다() {
        // given
        final Dealer dealer1 = new Dealer(cardArea);
        final Dealer dealer2 = new Dealer(cardArea);

        // when
        assertAll(
                () -> assertThat(dealer1.name()).isEqualTo(dealer2.name()),
                () -> assertThat(dealer1.name().value()).isEqualTo("딜러")
        );
    }
}