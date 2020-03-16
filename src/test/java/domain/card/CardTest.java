package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardTest {
	@Test
	void score_is10_CardSymbolIsKing() {
		assertThat(new Card(Type.HEART, Symbol.KING).score()).isEqualTo(10);
	}

	@Test
	void isAce_True_SymbolIsACE() {
		Card card1 = new Card(Type.HEART, Symbol.ACE);
		assertThat(card1.isAce()).isTrue();

		Card card2 = new Card(Type.HEART, Symbol.TWO);
		assertThat(card2.isAce()).isFalse();
	}
}
