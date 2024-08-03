import model.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    @Test
    void testCardValue() {
        Card card1 = new Card("하트", "2");
        Card card2 = new Card("스페이드", "K");
        Card card3 = new Card("다이아몬드", "A");

        assertEquals(2, card1.getValue());
        assertEquals(10, card2.getValue());
        assertEquals(11, card3.getValue());
    }
}
