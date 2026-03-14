package domain.card;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    void 카드가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> Card.of(Suit.SPADE, Rank.ACE));
    }

    @Test
    void 카드가_에이스인_경우_참을_반환한다() {
        Card card = Card.of(Suit.DIAMOND, Rank.ACE);

        Assertions.assertEquals(card.isAce(), true);
    }

    @Test
    void 카드가_에이스가_아닌_경우_거짓을_반환한다() {
        Card card = Card.of(Suit.DIAMOND, Rank.FOUR);

        Assertions.assertEquals(card.isAce(), false);
    }

}
