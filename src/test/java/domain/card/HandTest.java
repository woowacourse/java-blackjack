package domain.card;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandTest {
    @Test
    void 카드_더미가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> Hand.createEmpty());
    }

    @Test
    void 카드가_정상적으로_추가되어야_한다() {
        //given
        Card card = Card.of(Suit.SPADE, Rank.ACE);
        Hand hand = Hand.createEmpty();

        //when
        hand.add(card);

        //then
        Assertions.assertEquals(hand.size(), 1);
    }
}
