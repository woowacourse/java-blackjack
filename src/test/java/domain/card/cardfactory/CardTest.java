package domain.card.cardfactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardTest {
	@DisplayName("Ace 인지 테스트")
	@Test
	void isAce() {
		Card cardAce = new Card(Symbol.ACE, Shape.CLOVER);
		Card cardNotAce = new Card(Symbol.EIGHT, Shape.DIAMOND);

		assertThat(cardAce.isAce()).isTrue();
		assertThat(cardNotAce.isAce()).isFalse();
	}
}
