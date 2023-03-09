package domain.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.type.Letter;
import domain.type.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 생성 테스트")
    public void testCreate() {
        //given
        //when
        //then
        assertDoesNotThrow(() -> new Card(Suit.DIAMOND, Letter.TEN));
    }

    @Test
    @DisplayName("문자 일치 테스트")
    public void testLetterIsMatch() {
        //given
        final Card card = new Card(Suit.DIAMOND, Letter.ACE);

        //when
        final boolean result = card.isMatch(Letter.ACE);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("동등성 검사 테스트")
    public void testEquals() {
        //given
        final Card card1 = new Card(Suit.DIAMOND, Letter.ACE);
        final Card card2 = new Card(Suit.DIAMOND, Letter.ACE);

        //when
        final boolean result = card1.equals(card2);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("해쉬코드 생성 테스트")
    public void testHashCode() {
        //given
        final Card card1 = new Card(Suit.DIAMOND, Letter.ACE);
        final Card card2 = new Card(Suit.DIAMOND, Letter.ACE);

        //when
        final int hashCode1 = card1.hashCode();
        final int hashCode2 = card2.hashCode();

        //then
        assertEquals(hashCode1, hashCode2);
    }
}
