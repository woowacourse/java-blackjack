package domain.card;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    void 카드가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> new Card(Suit.SPADE, Rank.ACE));
    }

    @Test
    void 카드가_에이스인_경우_1을_반환한다() {
        Card card = new Card(Suit.DIAMOND, Rank.ACE);

        Assertions.assertEquals(card.getOneIfAce(), 1);
    }

    @Test
    void 카드가_에이스가_아닌_경우_0을_반환한다() {
        Card card = new Card(Suit.DIAMOND, Rank.FOUR);

        Assertions.assertEquals(card.getOneIfAce(), 0);
    }

    @Test
    void 카드가_에이스이면_0을_반환한다() {

        Card card = new Card(Suit.DIAMOND, Rank.ACE);

        Assertions.assertEquals(card.getRankValueIfNotAce(), 0);
    }

    @Test
    void 카드가_에이스가_아니면_값을_반환한다() {

        Card card = new Card(Suit.DIAMOND, Rank.SEVEN);

        Assertions.assertEquals(card.getRankValueIfNotAce(), 7);
    }
}
