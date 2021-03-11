package blackjack.domain.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
	@Test
	@DisplayName("에이스 존재 테스트")
	void containAce() {
		Card card = new Card(CardPattern.CLOVER, CardNumber.ACE);
		assertTrue(card.isAce());
	}
}
