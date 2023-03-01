package domain.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.type.Letter;
import domain.type.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("문자 일치 테스트")
    public void testIsMatch() {
        //given
        Card card = new Card(Suit.DIAMOND, Letter.ACE);

        //when
        boolean result = card.isMatch(Letter.ACE);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("동등성 검사 테스트")
    public void testEquals() {
        //given
        Card card1 = new Card(Suit.DIAMOND, Letter.ACE);
        Card card2 = new Card(Suit.DIAMOND, Letter.ACE);

        //when
        boolean result = card1.equals(card2);

        //then
        assertTrue(result);
    }
}