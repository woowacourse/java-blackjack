package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class CardTest {
	@Test
	void 점수_계산() {
		assertThat(new Card(Type.HEART, Symbol.ACE).score()).isEqualTo(11);
		assertThat(new Card(Type.HEART, Symbol.KING).score()).isEqualTo(10);
	}

	@Test
	void Ace_유무() {
		Card card1 = new Card(Type.HEART, Symbol.ACE);
		assertThat(card1.isAce()).isTrue();

		Card card2 = new Card(Type.HEART, Symbol.TWO);
		assertThat(card2.isAce()).isFalse();
	}
}
