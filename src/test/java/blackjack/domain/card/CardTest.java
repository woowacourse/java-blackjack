package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {
	@Test
	void Card_SymbolAndType_GenerateInstance() {
		assertThat(Card.of(Symbol.ACE, Type.CLUB)).isInstanceOf(Card.class);
	}
}
