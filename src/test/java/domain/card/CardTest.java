package domain.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	@DisplayName("문자 일치 테스트")
	public void testIsMatch() {
		//given
		Card card = new Card(Suit.DIAMOND, Denomination.ACE);

		//when
		boolean result = card.isMatch(Denomination.ACE);

		//then
		assertTrue(result);
	}

	@Test
	@DisplayName("동등성 검사 테스트")
	public void testEquals() {
		//given
		Card card1 = new Card(Suit.DIAMOND, Denomination.ACE);
		Card card2 = new Card(Suit.DIAMOND, Denomination.ACE);

		//when
		boolean result = card1.equals(card2);

		//then
		assertTrue(result);
	}
}
