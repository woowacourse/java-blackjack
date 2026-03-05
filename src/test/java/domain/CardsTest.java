package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    void 카드_더미가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> new Cards());
    }

    @Test
    void 카드가_정상적으로_추가되어야_한다() {
        //given
        Card card = new Card(Suit.SPADE, Rank.ACE);
        Cards cards = new Cards();

        //when
        cards.add(card);

        //then
        Assertions.assertEquals(cards.size(), 1);
    }
}
