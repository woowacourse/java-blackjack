package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SymbolTest {
	@Test
	void isAce_ThisInstanceIsAce_ReturnTrue() {
		assertThat(Symbol.valueOf("ACE").isAce()).isTrue();
	}
}
