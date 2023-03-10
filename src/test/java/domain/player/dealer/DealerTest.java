package domain.player.dealer;

import domain.card.Card;
import domain.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.card.CardValue.SEVEN;
import static domain.card.CardValue.SIX;
import static domain.card.CardValue.TEN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Dealer 은")
class DealerTest {

    @Test
    void 딜러는_16이하면_카드를_더_받을_수_있다() {
        // given
        final Dealer dealer = new Dealer();

        dealer.hit(new Card(CardShape.CLOVER, SIX));
        dealer.hit(new Card(CardShape.SPADE, TEN));

        // when & then
        assertTrue(dealer.canHit());
    }

    @Test
    void 딜러는_16초과면_카드를_더_받을_수_없다() {
        // given
        final Dealer dealer = new Dealer();

        dealer.hit(new Card(CardShape.CLOVER, SEVEN));
        dealer.hit(new Card(CardShape.SPADE, TEN));

        // when & then
        assertFalse(dealer.canHit());
    }

    @Test
    @DisplayName("faceUpFirstDeal() : 딜러는 처음 받은 카드 중 첫 번재 카드만 보여줘야한다.")
    void test_faceUpFirstDeal() throws Exception {
        // given
        final Dealer dealer = new Dealer();
        dealer.hit(new Card(CardShape.CLOVER, TEN));
        dealer.hit(new Card(CardShape.CLOVER, SEVEN));

        // when
        final List<Card> cards = dealer.faceUpFirstDeal();

        // then
        Assertions.assertThat(cards).containsExactly(new Card(CardShape.CLOVER, TEN));
    }
}
