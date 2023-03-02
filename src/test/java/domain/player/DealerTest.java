package domain.player;

import domain.area.CardArea;
import domain.card.Card;
import domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static domain.card.CardValue.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Dealer 은")
class DealerTest {

    @Test
    void 딜러는_16이하면_카드를_더_받을_수_있다() {
        // given
        final CardArea cardArea = new CardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SIX)
        );

        final Player dealer = new Dealer(new Name("player1"), cardArea);

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

        final Player dealer = new Dealer(new Name("player1"), cardArea);

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

        final Dealer dealer = new Dealer(new Name("player1"), cardArea);

        // then
        assertEquals(dealer.firstCard(), new Card(CardShape.CLOVER, TEN));
    }
}