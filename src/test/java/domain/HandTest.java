package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {
    @DisplayName("카드가 패에 처음 추가되면 패의 크기가 1이다.")
    @Test
    void addCardTest() {
        Hand hand = new Hand();
        final Card card = new Card(Suit.DIAMOND, Rank.ACE);

        assertEquals(0, hand.getCards().size());
        hand.addCard(card);
        assertEquals(1, hand.getCards().size());
    }

}
