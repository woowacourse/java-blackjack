package domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

	@DisplayName("Ace카드인 경우")
	@Test
	void isAceTest() {
		Card card = new Card(Symbol.CLOVER, Type.ACE);
		assertThat(card.isAce()).isTrue();
	}

	@DisplayName("Ace카드가 아닌 경우")
	@Test
	void isAceTest2() {
		Card card = new Card(Symbol.CLOVER, Type.TWO);
		assertThat(card.isAce()).isFalse();
	}
}
