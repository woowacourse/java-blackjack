package domain.player.dealer;

import domain.area.CardArea;
import domain.card.Card;
import domain.card.CardShape;
import domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static domain.card.CardValue.SEVEN;
import static domain.card.CardValue.SIX;
import static domain.card.CardValue.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @DisplayName("faceUpFirstCard() : 딜러는 첫 번째 카드만을 보여줍니다.")
    void test_faceUpFirstCard() throws Exception {
        //given
        final CardArea cardArea = new CardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SEVEN)
        );

        //when
        final Dealer dealer = new Dealer(cardArea);

        //then
        assertEquals(dealer.faceUpFirstCard(), new Card(CardShape.CLOVER, TEN));
    }
}
