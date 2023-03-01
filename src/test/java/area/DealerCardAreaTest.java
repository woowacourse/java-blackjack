package area;

import card.Card;
import card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static card.CardValue.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("DealerCardArea 은")
class DealerCardAreaTest {

    @Test
    void 딜러_카드_영억은_현재_점수가_17점_이하일_경우에는_카드를_무조건_받아야_한다() {
        // given
        final CardArea cardArea = new DealerCardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SEVEN)
        );

        // when & then
        assertTrue(cardArea.wantHit());
    }

    @Test
    void 딜러_카드_영억은_현재_점수가_17점_초과일_경우에는_카드를_무조건_받지_않는다() {
        // given
        final CardArea cardArea = new DealerCardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, EIGHT)
        );

        // when & then
        assertFalse(cardArea.wantHit());
    }
}